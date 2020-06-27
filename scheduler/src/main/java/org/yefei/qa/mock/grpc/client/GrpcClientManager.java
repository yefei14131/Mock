package org.yefei.qa.mock.grpc.client;

import io.grpc.Channel;
import io.grpc.Deadline;
import io.grpc.ManagedChannel;
import io.grpc.stub.AbstractStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.grpc.channel.ChannelManager;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author yefei
 * @date: 2020/4/17
 */

@Component
public class GrpcClientManager {

    @Autowired
    private ChannelManager channelManager;


    public Object getFutureStub(String target, Class grpcClass) throws Exception {

        ManagedChannel channel = channelManager.getChannel(target);
        try {
            Method stubMethod = grpcClass.getMethod("newFutureStub", new Class[]{Channel.class});
            Object stub = stubMethod.invoke(null, channel);

            if (stub instanceof AbstractStub) {
                stub = ((AbstractStub) stub).withDeadline(Deadline.after(10, TimeUnit.SECONDS));
            }
            return stub;

        } catch (Exception e) {
            throw new Exception("不支持的grpcClass : " + grpcClass, e);
        }
    }

}
