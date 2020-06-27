package org.apache.skywalking.apm.mock.plugin.grpc.v1.core.client;

import com.google.protobuf.Message;
import io.grpc.ClientCall;
import io.grpc.MethodDescriptor;

import java.util.concurrent.CountDownLatch;

/**
 * @author yefei
 * @date: 2020/4/17
 */
public class GrpcMockResquestHolder {

    private static ThreadLocal<MethodDescriptor> businessMethod = new ThreadLocal<>();
    private static ThreadLocal<MethodDescriptor> mockMethod = new ThreadLocal<>();
    private static ThreadLocal<Message> businessReqMessage = new ThreadLocal<>();
    private static ThreadLocal<Runnable> callback = new ThreadLocal<>();
    private static ThreadLocal<ClientCall.Listener<Message>> businessRespListener = new ThreadLocal<>();
    private static ThreadLocal<CountDownLatch> countDownLatch = new ThreadLocal<>();

    public static void set(MethodDescriptor businessMethod, Message businessReqMessage, ClientCall.Listener<Message> businessRespListener, Runnable callback, CountDownLatch countDownLatch) {
        GrpcMockResquestHolder.businessRespListener.set(businessRespListener);
        GrpcMockResquestHolder.businessMethod.set(businessMethod);
        GrpcMockResquestHolder.businessReqMessage.set(businessReqMessage);
        GrpcMockResquestHolder.callback.set(callback);
        GrpcMockResquestHolder.countDownLatch.set(countDownLatch);
        GrpcMockResquestHolder.mockMethod.set(genMockMethod());
    }

    private static MethodDescriptor genMockMethod() {
        MethodDescriptor businessMethodDescriptor = businessMethod.get();

        //替换fullMethodName
        String fullMethodName = businessMethodDescriptor.getFullMethodName();

        String javaPackageName = businessReqMessage.get().getClass().getPackage().getName();
        fullMethodName = javaPackageName + "." + fullMethodName;

        MethodDescriptor.Marshaller<?> requestMarshaller = businessMethodDescriptor.getRequestMarshaller();
        MethodDescriptor.Marshaller<?> responseMarshaller = businessMethodDescriptor.getResponseMarshaller();
        MethodDescriptor.MethodType type = businessMethodDescriptor.getType();

        MethodDescriptor newMethodDescriptor = MethodDescriptor.create(type, fullMethodName, requestMarshaller, responseMarshaller);

        return newMethodDescriptor;
    }

    public static MethodDescriptor getBusinessMethod() {
        return businessMethod.get();
    }

    public static MethodDescriptor getMockMethod() {
        return mockMethod.get();
    }

    public static Message getMessage() {
        return businessReqMessage.get();
    }

    public static Runnable getCallback() {
        return callback.get();
    }

    public static ClientCall.Listener<Message> getBusinessRespListener() {
        return businessRespListener.get();
    }

    public static CountDownLatch getCountDownLatch() {
        return countDownLatch.get();
    }
}
