/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.skywalking.apm.mock.plugin.grpc.v1;

import io.grpc.*;
import org.apache.skywalking.apm.mock.plugin.grpc.v1.core.client.GrpcMockResquestHolder;

/**
 * {@link GRPCClientInterceptor} determines the returned Interceptor based on the method type. If the method type is
 * UNARY, {@link GRPCClientInterceptor} returns BlockingCallClientInterceptor, or it returns
 * StreamCallClientInterceptor.
 *
 * @author zhang xin
 */
public class GRPCClientInterceptor implements ClientInterceptor {
    private static final String MOCK_METHOD = "proto.msg.QaDemoService/QueryTPService";

    @Override
    public ClientCall interceptCall(MethodDescriptor method,
                                    CallOptions callOptions, Channel channel) {

        String fullMethodName = method.getFullMethodName();

        if (MOCK_METHOD.equals(fullMethodName)) {
            // 调用mock服务
            return new FutureCallClientMockInterceptor(
                    channel.newCall(GrpcMockResquestHolder.getMockMethod(), callOptions),
                    GrpcMockResquestHolder.getMockMethod(),
                    channel,
                    GrpcMockResquestHolder.getMessage(),
                    GrpcMockResquestHolder.getBusinessRespListener(),
                    GrpcMockResquestHolder.getCallback(),
                    GrpcMockResquestHolder.getCountDownLatch());
        }
        if (method.getType() != MethodDescriptor.MethodType.UNARY) {
            // 不能监听stream类型的请求，mock服务端暂时无法正确处理这种数据
            return channel.newCall(method, callOptions);
        }
        return new FutureCallClientBusinessInterceptor(channel.newCall(method, callOptions), method, channel, callOptions);
    }

}
