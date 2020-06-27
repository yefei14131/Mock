package org.apache.skywalking.apm.mock.plugin.grpc.v1.core.client;

import io.grpc.Channel;
import io.grpc.Deadline;
import io.grpc.ManagedChannel;
import io.grpc.stub.AbstractStub;
import org.apache.skywalking.apm.mock.plugin.grpc.v1.core.channel.ChannelManager;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author yefei
 * @date: 2020/4/17
 */

public class GrpcClientManager {

    private static ChannelManager channelManager;


    public static Object getBlockStub(String target, Class grpcClass) throws Exception {

        ManagedChannel channel = channelManager.getChannel(target);
        try {
            Method stubMethod = grpcClass.getMethod("newBlockStub", new Class[]{Channel.class});
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
