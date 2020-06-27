package org.yefei.qa.mock.grpc.starter;

import com.google.common.base.Preconditions;
import io.grpc.*;
import io.grpc.stub.StreamObserver;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.config.GrpcConfig;
import org.yefei.qa.mock.grpc.handler.GrpcRegistry;
import org.yefei.qa.mock.grpc.handler.GrpcServerInterceptor;
import org.yefei.qa.mock.grpc.handler.GrpcServiceProvider;
import org.yefei.qa.mock.grpc.idl.QaDemoServiceGrpc;
import org.yefei.qa.mock.grpc.idl.TPInfoReply;
import org.yefei.qa.mock.grpc.idl.TPRequest;
import org.yefei.qa.mock.grpc.impl.MockServerVariableServiceImpl;

import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yefei
 * @date: 2020/3/31
 */
@Slf4j
@Data
@Component
public class GrpcServerStarter {

    private Server server;

    @Autowired
    private MockServerVariableServiceImpl variableService;

    @Autowired
    private GrpcServiceProvider grpcServiceProvider;

    @Autowired
    private GrpcConfig grpcConfig;

    @Autowired
    private GrpcServerInterceptor grpcServerInterceptor;

    @Autowired
    private GrpcRegistry grpcRegistry;

    private FastLookupRegistry fastLookupRegistry = new FastLookupRegistry();

    @PostConstruct
    public void init() throws IOException {
        this.start();
//        this.blockUntilShutdown();
    }

    public void start() throws IOException {

        server = ServerBuilder.forPort(grpcConfig.getPort())
                .intercept(grpcServerInterceptor)
//                .addService(new GreeterImpl())
                .addService(variableService)
                .fallbackHandlerRegistry(new LookupHandlerRegistry())
                .build()
                .start();

        log.info("grpc server started, port {}", grpcConfig.getPort());

        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {

                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                GrpcServerStarter.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    // block 一直到退出程序
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {

//        final GrpcServerStarter server = new GrpcServerStarter();
//        server.start();
//        server.blockUntilShutdown();
    }


    // 实现 定义一个实现服务接口的类
    private class GreeterImpl extends QaDemoServiceGrpc.QaDemoServiceImplBase {

        @Override
        public void queryTPService(TPRequest request, StreamObserver<TPInfoReply> responseObserver) {
            super.queryTPService(request, responseObserver);
        }
    }


    private class HandlerRegistryImp extends HandlerRegistry {
        @Nullable
        public ServerMethodDefinition<?, ?> lookupMethod(String methodName, @Nullable String authority) {
            try {
//                grpcServiceProvider.getGrpcMethodDescriptor(methodName)
                MethodDescriptor methodDescriptor = grpcServiceProvider.getGrpcMethodDescriptor(methodName);
                if (methodDescriptor == null) {
                    log.error("grpc服务未实现，{}", methodName);
                    return null;
                }
                return ServerMethodDefinition.create(
                        (MethodDescriptor) Preconditions.checkNotNull(methodDescriptor, "method must not be null"),
                        (ServerCallHandler) Preconditions.checkNotNull(new GreeterImpl().bindService().getMethod("proto.msg.QaDemoService/QueryTPService").getServerCallHandler(), "handler must not be null"));
            } catch (Exception e) {
                log.error("grpc 注册服务异常", e);
            }
            return null;
        }
    }

    private class LookupHandlerRegistry extends HandlerRegistry {
        @Nullable
        public ServerMethodDefinition<?, ?> lookupMethod(String methodName, @Nullable String authority) {
            try {

                // 私有注册器中获取，一般由mock-agent发起的可在此找到。此处的serviceName包含javaPckageName
                ServerMethodDefinition<?, ?> methodP = grpcRegistry.getPrivateRegistry().lookupMethod(methodName, authority);
                if (methodP != null) {
                    return methodP;
                }

                // 快速搜索
                ServerMethodDefinition<?, ?> methodF = fastLookupRegistry.lookupMethod(methodName, authority);
                if (methodF != null) {
                    return methodF;
                }

                String serviceName = MethodDescriptor.extractFullServiceName(methodName);

                List<ServerServiceDefinition> services = grpcRegistry.getPrivateRegistry().getServices();
                for (ServerServiceDefinition service : services) {
                    String definedServiceName = service.getServiceDescriptor().getName();

                    if (definedServiceName.endsWith(serviceName)) {

                        //添加到快速搜索
                        fastLookupRegistry.addService(serviceName, service);

                        String fullMethod = methodName.replace(serviceName, definedServiceName);
                        ServerMethodDefinition<?, ?> method = service.getMethod(fullMethod);
                        if (method != null) {
                            return method;
                        }
                    }
                }
            } catch (Exception e) {
                log.error("grpc 注册服务异常", e);
            }
            return null;
        }
    }

    private class FastLookupRegistry extends HandlerRegistry {
        public ConcurrentHashMap<String, HashSet<ServerServiceDefinition>> cachedServiceDefine = new ConcurrentHashMap<>();

        public void addService(String simpleServiceName, ServerServiceDefinition service) {
            synchronized (this) {
                if (fastLookupRegistry.cachedServiceDefine.containsKey(simpleServiceName)) {
                    fastLookupRegistry.cachedServiceDefine.get(simpleServiceName).add(service);
                } else {
                    HashSet<ServerServiceDefinition> set = new HashSet<>();
                    set.add(service);
                    fastLookupRegistry.cachedServiceDefine.put(simpleServiceName, set);
                }
            }
        }

        @Nullable
        public ServerMethodDefinition<?, ?> lookupMethod(String methodName, @Nullable String authority) {
            try {
                String serviceName = MethodDescriptor.extractFullServiceName(methodName);

                if (cachedServiceDefine.containsKey(serviceName)) {
                    for (ServerServiceDefinition serviceDefinition : cachedServiceDefine.get(serviceName)) {
                        String fullServiceName = serviceDefinition.getServiceDescriptor().getName();
                        String fullMethod = methodName.replace(serviceName, fullServiceName);

                        ServerMethodDefinition<?, ?> method = serviceDefinition.getMethod(fullMethod);
                        if (method != null) {
                            return method;
                        }
                    }
                }
            } catch (Exception e) {
                log.error("grpc 注册服务异常", e);
            }
            return null;
        }
    }
}
