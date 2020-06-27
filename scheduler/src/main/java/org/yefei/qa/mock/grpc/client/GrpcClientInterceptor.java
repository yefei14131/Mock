package org.yefei.qa.mock.grpc.client;

import io.grpc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @author yefei
 * @date: 2020/4/17
 */
@Component
public class GrpcClientInterceptor implements ClientInterceptor {

    @Autowired
    private GrpcRequestHeaderHolder grpcRequestHeaderHolder;

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {

        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(channel.newCall(methodDescriptor, callOptions)) {

            @Override
            public void start(Listener<RespT> responseListener, Metadata headers) {

                HashMap<String, String> requestHeaders = grpcRequestHeaderHolder.getHeaders();
                if (requestHeaders != null && !requestHeaders.isEmpty()) {

                    Iterator<String> iterator = requestHeaders.keySet().iterator();
                    while (iterator.hasNext()) {
                        String key = iterator.next();
                        String value = requestHeaders.get(key);

                        headers.put(Metadata.Key.of(key, Metadata.ASCII_STRING_MARSHALLER), value);
                    }
                }
                delegate().start(responseListener, headers);
            }
        };
    }
}
