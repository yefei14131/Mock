package org.yefei.qa.mock.grpc.handler;

import io.grpc.MethodDescriptor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.utils.JarFileClassScanner;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.zip.ZipException;

@Slf4j
@Component
public class GrpcServiceProvider implements IGrpcServiceProvider {

    @Autowired
    private GrpcInterfaceKeeper grpcInterfaceKeeper;

    private ReentrantLock lock = new ReentrantLock(true);

    private static ConcurrentHashMap<String, HashMap<String, MethodClassBean>> classMap = new ConcurrentHashMap<>();

    public MethodClassBean getGrpcServiceClass(String methodFullName) throws Exception {
        String serviceName = MethodDescriptor.extractFullServiceName(methodFullName);

        if (methodFullName == null) {
            throw new Exception("未扫描到grpc类");
        }

        for (String s : classMap.keySet()) {
            if (s.contains("SaasOrderService")) {
                log.info(serviceName);
            }
        }

        if (!classMap.containsKey(serviceName)) {
            return null;
        }

        String methodName = methodFullName.substring(serviceName.length() + 1);
        return classMap.get(serviceName).get(methodName);
    }


    @Override
    public boolean updateItem(String localJarFilePath) {
        lock.lock();
        try {
            classMap.putAll(scanGrpcClassFromJars(localJarFilePath));
            return true;

        } catch (ZipException e) {
            new File(localJarFilePath).deleteOnExit();
            return false;

        } catch (Exception e) {
            log.error("扫描grpc jar包异常", ExceptionUtils.getStackTrace(e));
            return false;
        } finally {
            if (lock.isLocked())
                lock.unlock();
        }
    }

    @Override
    public void clear() {
        classMap.clear();
    }

    public static class JarClassNameFilter implements JarFileClassScanner.JarClassFilter {
        @Override
        public boolean match(String className) {
            return className.endsWith("Grpc") && !className.endsWith(".Grpc");
        }
    }

    private static ConcurrentHashMap<String, HashMap<String, MethodClassBean>> scanGrpcClassFromJars(String jars) throws IOException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        String[] jarFilesPath = jars.split(";");

        ConcurrentHashMap<String, HashMap<String, MethodClassBean>> innerClassMap = new ConcurrentHashMap<>();

        for (String jarPath : jarFilesPath) {
            log.info("开始扫描jar包：{}", jarPath);
            File jarFile = new File(jarPath);

            JarFileClassScanner classScanner = new JarFileClassScanner();
            List<Class> classes = classScanner.setJarClassFilter(new JarClassNameFilter()).loadClassByJarFile(jarFile);


            for (Class grpcClass : classes) {

                Constructor constructor = grpcClass.getDeclaredConstructor();
                constructor.setAccessible(true);

                String javaPackageName = grpcClass.getPackage().getName();

                Field serviceNameField = grpcClass.getField("SERVICE_NAME");
                String serviceName = (String) serviceNameField.get(String.class);
                serviceName = javaPackageName + "." + serviceName;

                log.info("扫描到grpc服务：{}", serviceName);

                String finalServiceName = serviceName;

                HashMap<String, MethodClassBean> methodMap = new HashMap<>();
                innerClassMap.put(serviceName, methodMap);

                for (Field field : grpcClass.getFields()) {
                    if (field.getName().startsWith("METHOD_")) {
                        MethodDescriptor methodDescriptor = (MethodDescriptor) field.get(null);

                        String fullMethodName = methodDescriptor.getFullMethodName();
                        String[] arr = fullMethodName.split("/");
                        String methodName = arr[1];
                        Class requestClass = ((MethodDescriptor.PrototypeMarshaller) methodDescriptor.getRequestMarshaller()).getMessagePrototype().getClass();
                        MethodClassBean methodClassBean = new MethodClassBean(finalServiceName, methodName, grpcClass, requestClass);
                        methodMap.put(methodName, methodClassBean);
                    }
                }
            }
        }

        return innerClassMap;
    }

    @Data
    public static class MethodClassBean {
        private String serviceName;
        private String methodName;
        private Class serviceClass;
        private Class requestClass;

        MethodClassBean(String serviceName, String methodName, Class serviceClass, Class requestClass) {
            this.serviceName = serviceName;
            this.methodName = ucFirst(methodName); //真实调用时，使用方法，小写开头
            this.serviceClass = serviceClass;
            this.requestClass = requestClass;
        }
    }

    private static String ucFirst(String input) {
        if (input == null)
            return null;
        return input.substring(0, 1).toLowerCase() + input.substring(1);
    }

}
