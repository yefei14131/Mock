package org.yefei.qa.mock.grpc.channel;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolverProvider;
import io.grpc.netty.NettyChannelBuilder;
import io.grpc.util.RoundRobinLoadBalancerFactory;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.grpc.client.GrpcClientInterceptor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author yefei
 * @date: 2020/4/17
 */
@Component
public class ChannelManager {

    @Autowired
    private GrpcClientInterceptor grpcClientHeaderInterceptor;

    private ConcurrentHashMap<String, ManagedChannel> channelPool = new ConcurrentHashMap<>();

    public ManagedChannel getChannel(String target) {
        if (channelPool.containsKey(target)) {
            ManagedChannel channel = channelPool.get(target);
            return checkChannel(channel, target);
        } else {
            return newChannel(target);
        }
    }

    private synchronized ManagedChannel checkChannel(ManagedChannel channel, String target) {
        if (channel.isTerminated() || channel.isShutdown()) {
            return newChannel(target);
        }
        return channel;
    }

    private ManagedChannel newChannel(String target) {
        ManagedChannelBuilder channelBuilder = NettyChannelBuilder.forTarget(target)
                .keepAliveTime(10, TimeUnit.SECONDS)
                .keepAliveTimeout(2, TimeUnit.SECONDS)
                .channelType(NioSocketChannel.class)
                .keepAliveWithoutCalls(true)
                .idleTimeout(1, TimeUnit.HOURS)
                .intercept(grpcClientHeaderInterceptor)
                .usePlaintext(true)
                .nameResolverFactory(NameResolverProvider.asFactory())
                .loadBalancerFactory(RoundRobinLoadBalancerFactory.getInstance());

        ManagedChannel channel = channelBuilder.build();
        channelPool.putIfAbsent(target, channel);
        return channel;
    }
}
