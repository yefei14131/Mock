package org.yefei.qa.mock.grpc.handler;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.utils.JarFileClassScanner;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipException;

@Slf4j
@Component
public class GrpcServiceProvider implements IGrpcServiceProvider {

    private ConcurrentHashMap<String, ConcurrentHashMap<String, MethodDescriptor>> servicerMap = new ConcurrentHashMap<>();

    @Autowired
    private GrpcRegistry grpcRegistry;

    public MethodDescriptor getGrpcMethodDescriptor(String methodFullName) {
        String[] arr = methodFullName.split("/");
        String serviceName = arr[0];
        return servicerMap.containsKey(serviceName) ? servicerMap.get(serviceName).get(methodFullName) : null;
    }

    @Override
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

    private void scanGrpcClassFromJars(String jars) throws IOException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        String[] jarFilesPath = jars.split(";");


        for (String jarPath : jarFilesPath) {
            log.info("开始扫描jar包：{}", jarPath);
            File jarFile = new File(jarPath);

            JarFileClassScanner classScanner = new JarFileClassScanner();
            List<Class> classes = classScanner.setJarClassFilter(new JarClassNameFilter()).loadClassByJarFile(jarFile);


            for (Class grpcClass : classes) {

                // com.hualala.app.pay.grpc.BankSuccessServiceGrpc
                // 使用java的class替代proto的class，解决proto包名未定义时的冲突
                String javaPackageName = grpcClass.getPackage().getName();

                Field serviceNameField = grpcClass.getField("SERVICE_NAME");
                String serviceName = (String) serviceNameField.get(String.class);
                serviceName = javaPackageName + "." + serviceName;

                // 注册服务
                ServerServiceDefinition serviceDefinition = getServiceDefinition(grpcClass);
                grpcRegistry.getPrivateRegistry().addService(serviceDefinition);
//
                Constructor constructor = grpcClass.getDeclaredConstructor();
                constructor.setAccessible(true);

                if (serviceName != null) {
                    log.info("扫描到grpc服务：{}", serviceName);
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


    public ServerServiceDefinition getServiceDefinition(Class grpcClass) {
        try {

            for (Class subClass : grpcClass.getDeclaredClasses()) {
                if (subClass.getName().endsWith("Base")) {

                    // 使用cglib，调用源码中的xxImplBase.bindService方法获得原始的 ServerServiceDefinition
                    Enhancer enhancer = new Enhancer();
                    enhancer.setSuperclass(subClass);
                    enhancer.setCallback(new MethodInterceptor() {
                        @Override
                        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                            return proxy.invokeSuper(obj, args);
                        }
                    });
                    Object instance = enhancer.create();
                    ServerServiceDefinition serviceDefinition = (ServerServiceDefinition) (subClass.getDeclaredMethod("bindService", null).invoke(instance));

                    // 重新构造serviceDefinition，在serviceName和fullMethodName前加上java包名，用来兼容多个jar包内的相同serviceName冲突
                    String serviceName = serviceDefinition.getServiceDescriptor().getName();
                    String javaPackageName = subClass.getPackage().getName();
                    String fullServiceName;
                    if (!serviceName.startsWith(javaPackageName)) {
                        fullServiceName = javaPackageName + "." + serviceName;
                    } else {
                        fullServiceName = serviceName;
                    }

                    ServiceDescriptor.Builder serviceBuilder = ServiceDescriptor.newBuilder(fullServiceName);
                    ServerServiceDefinition.Builder serviceDefinitionBuilder = ServerServiceDefinition.builder(fullServiceName);

                    Collection<ServerMethodDefinition<?, ?>> methods = serviceDefinition.getMethods();

                    for (ServerMethodDefinition<?, ?> orgMethodDefinition : methods) {
                        MethodDescriptor<?, ?> methodDescriptor = orgMethodDefinition.getMethodDescriptor();

                        //替换fullMethodName
                        String fullMethodName = methodDescriptor.getFullMethodName();
                        String serviceName1 = MethodDescriptor.extractFullServiceName(fullMethodName);
                        fullMethodName = fullMethodName.replace(serviceName1, fullServiceName);

                        MethodDescriptor.Marshaller<?> requestMarshaller = methodDescriptor.getRequestMarshaller();
                        MethodDescriptor.Marshaller<?> responseMarshaller = methodDescriptor.getResponseMarshaller();
                        Object schemaDescriptor = methodDescriptor.getSchemaDescriptor();
                        MethodDescriptor.MethodType type = methodDescriptor.getType();

                        MethodDescriptor<?, ?> newMethodDescriptor = MethodDescriptor.create(type, fullMethodName, requestMarshaller, responseMarshaller);
                        serviceBuilder.addMethod(newMethodDescriptor);

                        ServerCallHandler serverCallHandler = orgMethodDefinition.getServerCallHandler();
                        serviceDefinitionBuilder.addMethod(newMethodDescriptor, serverCallHandler);

                        if (schemaDescriptor != null) {
                            serviceBuilder.setSchemaDescriptor(schemaDescriptor);
                        }
                    }

                    return serviceDefinitionBuilder.build();
                }
            }
        } catch (Exception e) {
            log.error("获取grpc ServerServiceDefinition异常", e);
        }

        return null;
    }


//
//    private class MyMethodInterceptor implements MethodInterceptor {
//        /**
//         * 代理方法, 每次调用目标方法时都会进到这里
//         */
////        @Override
//        public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
//            before();
//            return methodProxy.invokeSuper(obj, args);
//            //        return method.invoke(obj, args);  这种也行
//        }
//
//        /**
//         * 前置增强
//         */
//        private void before() {
//            System.out.println("对转账人身份进行验证.");
//        }
//
//        @Override
//        public Object invoke(MethodInvocation methodInvocation) throws Throwable {
//            return methodInvocation.getMethod().invoke(null);
//        }
//    }
}
