package org.yefei.qa.mock.grpc.handler;

import io.grpc.MethodDescriptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.utils.JarFileClassScanner;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipException;

@Slf4j
@Component
public class GrpcServiceProvider implements IGrpcServiceProvider {

    private ConcurrentHashMap<String, List<String>> servicerMap = new ConcurrentHashMap<>();

    public ConcurrentHashMap<String, List<String>> getGrpcAllMethod() {
        return servicerMap;
    }

    public boolean updateItem(String localJarFilePath) {
        try {
            scanGrpcClassFromJars(localJarFilePath);
            return true;
        } catch (ZipException e) {
            new File(localJarFilePath).deleteOnExit();
            return false;
        } catch (Exception e) {
            log.error("扫描interface jar包异常", e);
            return false;
        }
    }

    @Override
    public void clear() {
        servicerMap.clear();
    }

    private void scanGrpcClassFromJars(String jars) throws IOException, NoSuchMethodException, IllegalAccessException, ClassNotFoundException {
        String[] jarFilesPath = jars.split(";");


        for (String jarPath : jarFilesPath) {
            log.info("开始扫描jar包：{}", jarPath);
            File jarFile = new File(jarPath);

            JarFileClassScanner classScanner = new JarFileClassScanner();
            List<Class> classes = classScanner.setJarClassFilter(new JarClassNameFilter()).loadClassByJarFile(jarFile);


            for (Class grpcClass : classes) {

                Constructor constructor = grpcClass.getDeclaredConstructor();
                constructor.setAccessible(true);

                String javaPackageName = grpcClass.getPackage().getName();
                String serviceName = null;

                List<String> methodList = new ArrayList<>();
                for (Field field : grpcClass.getFields()) {
                    if (field.getName().startsWith("METHOD")) {
                        MethodDescriptor methodDescriptor = (MethodDescriptor) field.get(null);

                        String fullMethodName = methodDescriptor.getFullMethodName();
                        String[] arr = fullMethodName.split("/");
                        serviceName = javaPackageName + "." + arr[0];
                        methodList.add(arr[1]);
                    }
                }

                if (serviceName != null) {
                    log.info("扫描到grpc服务：{}", serviceName);
                    servicerMap.put(serviceName, methodList);
                }
            }
        }

    }


    public class JarClassNameFilter implements JarFileClassScanner.JarClassFilter {
        @Override
        public boolean match(String className) {
            return className.endsWith("Grpc") && !className.endsWith(".Grpc");
        }
    }

}
