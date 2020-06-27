package org.apache.skywalking.apm.mock.plugin.grpc.v1.core.channel;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.NameResolverProvider;
import io.grpc.netty.NettyChannelBuilder;
import io.grpc.util.RoundRobinLoadBalancerFactory;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author yefei
 * @date: 2020/4/17
 */
public class ChannelManager {

    private static ConcurrentHashMap<String, ManagedChannel> channelPool = new ConcurrentHashMap<>();

    public static ManagedChannel getChannel(String target) {
        if (channelPool.containsKey(target)) {
            ManagedChannel channel = channelPool.get(target);
            return checkChannel(channel, target);
        } else {
            return newChannel(target);
        }
    }

    private static synchronized ManagedChannel checkChannel(ManagedChannel channel, String target) {
        if (channel.isTerminated() || channel.isShutdown()) {
            return newChannel(target);
        }
        return channel;
    }

    private static ManagedChannel newChannel(String target) {
        ManagedChannelBuilder channelBuilder = NettyChannelBuilder.forTarget(target)
                .keepAliveTime(10, TimeUnit.SECONDS)
                .keepAliveTimeout(2, TimeUnit.SECONDS)
                .channelType(NioSocketChannel.class)
                .keepAliveWithoutCalls(true)
                .idleTimeout(1, TimeUnit.MINUTES)
                .usePlaintext(true)
                .nameResolverFactory(NameResolverProvider.asFactory())
                .loadBalancerFactory(RoundRobinLoadBalancerFactory.getInstance());

        ManagedChannel channel = channelBuilder.build();
        channelPool.putIfAbsent(target, channel);
        return channel;
    }
}
