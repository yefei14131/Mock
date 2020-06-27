package org.apache.skywalking.apm.mock.plugin.grpc.v1.core.client;


import com.google.protobuf.Message;
import io.grpc.ClientCall;
import io.grpc.ManagedChannel;
import io.grpc.MethodDescriptor;
import org.apache.skywalking.apm.mock.agent.core.conf.Config;
import org.apache.skywalking.apm.mock.agent.core.logging.api.ILog;
import org.apache.skywalking.apm.mock.agent.core.logging.api.LogManager;
import org.apache.skywalking.apm.mock.plugin.grpc.v1.core.channel.ChannelManager;
import org.apache.skywalking.apm.mock.plugin.grpc.v1.core.idl.QaDemoServiceGrpc;
import org.apache.skywalking.apm.mock.plugin.grpc.v1.core.idl.TPRequest;

import java.util.concurrent.CountDownLatch;

/**
 * @author yefei
 * @date: 2020/4/23
 */
public class GrpcMockServerStub {

    private static final ILog logger = LogManager.getLogger(GrpcMockServerStub.class);
    private static final String grpcMockTarget = Config.Agent.MOCKSERVER_GRPC_ADDR;

    /**
     * 异步调用grpc mock服务
     *
     * @param businessMethod       被拦截的业务方法
     * @param businessReqMessage   被拦截的业务请求消息
     * @param businessRespListener grpc client response listener
     * @param callback             mock调用成功后的回调
     * @param countDownLatch       阻塞计数器
     */
    public static void asyncCall(MethodDescriptor businessMethod, Message businessReqMessage, ClientCall.Listener<Message> businessRespListener, Runnable callback, CountDownLatch countDownLatch) {
        try {
            GrpcMockResquestHolder.set(businessMethod, businessReqMessage, businessRespListener, callback, countDownLatch);
            logger.debug("调用mock服务：{},  {}", grpcMockTarget, GrpcMockResquestHolder.getMockMethod().getFullMethodName());
            ManagedChannel channel = ChannelManager.getChannel(grpcMockTarget);
            QaDemoServiceGrpc.QaDemoServiceFutureStub stub = QaDemoServiceGrpc.newFutureStub(channel);
            stub.queryTPService(TPRequest.newBuilder().build());

        } catch (Exception e) {
            logger.error("调用grpc mock异常", e);
            countDownLatch.countDown();
        }
    }

}
