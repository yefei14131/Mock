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

import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import io.grpc.*;
import org.apache.skywalking.apm.mock.agent.core.logging.api.ILog;
import org.apache.skywalking.apm.mock.agent.core.logging.api.LogManager;
import org.apache.skywalking.apm.mock.agent.core.mock.MockManager;
import org.apache.skywalking.apm.mock.plugin.grpc.v1.core.client.GrpcMockServerStub;

import javax.annotation.Nullable;
import java.util.concurrent.CountDownLatch;

/**
 * @author yefei
 * @date: 2019/9/21
 * <p>
 * 业务请求的拦截器
 */
public class FutureCallClientBusinessInterceptor extends ForwardingClientCall.SimpleForwardingClientCall<Message, Message> implements CallClientInterceptor {
    private static final ILog logger = LogManager.getLogger(FutureCallClientBusinessInterceptor.class);

    private final MethodDescriptor businessMethod;
    private Listener<Message> businessRespListener;
    private CallOptions callOptions;
    private final Channel channel;
    private boolean isMocking;
    private String fullMethodName;

    public FutureCallClientBusinessInterceptor(ClientCall delegate, MethodDescriptor method, Channel channel, CallOptions callOptions) {
        super(delegate);
        this.businessMethod = method;
        this.channel = channel;
        this.callOptions = callOptions;

        Class reqClass = ((MethodDescriptor.PrototypeMarshaller) method.getRequestMarshaller()).getMessagePrototype().getClass();
        String javaPackage = reqClass.getPackage().getName();

        //使用java包名加proto全名，兼容多个interface可能导致的冲突
        fullMethodName = javaPackage + "." + method.getFullMethodName();

        isMocking = MockManager.isGrpcMocking(fullMethodName);
        logger.debug("拦截到grpc请求：{}, {}mock状态", fullMethodName, isMocking ? "是" : "不是");

    }


    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public MethodDescriptor getMethodDescriptor() {
        return businessMethod;
    }


    @Override
    public void start(Listener<Message> responseListener, Metadata headers) {
        this.businessRespListener = responseListener;
        delegate().start(new ClientCallLister(responseListener), headers);
    }

    @Override
    public void sendMessage(Message businessReqMessage) {
        try {
            logger.debug("grpc 请求参数： {}, {}", fullMethodName, JsonFormat.printer().print(businessReqMessage));
            final boolean[] mockSuccess = {false};

            if (isMocking) {
                CountDownLatch countDownLatch = new CountDownLatch(1);
                Runnable callback = new Runnable() {
                    @Override
                    public void run() {
                        mockSuccess[0] = true;
                        countDownLatch.countDown();
                    }
                };

                GrpcMockServerStub.asyncCall(businessMethod, businessReqMessage, businessRespListener, callback, countDownLatch, callOptions);
                countDownLatch.await();
            }

            if (!mockSuccess[0]) {
                delegate().sendMessage(businessReqMessage);
            }

        } catch (Exception e) {
            delegate().sendMessage(businessReqMessage);
        }

    }

    @Override
    public void cancel(@Nullable String message, @Nullable Throwable cause) {
        delegate().cancel(message, cause);
    }

    private class ClientCallLister extends ForwardingClientCallListener.SimpleForwardingClientCallListener<Message> {
        public ClientCallLister(Listener delegate) {
            super(delegate);
        }

        @Override
        public void onMessage(Message message) {
            try {
                logger.debug("grpc 业务响应结果： {}, {}", fullMethodName, JsonFormat.printer().print(message));

            } catch (Exception e) {

            } finally {
                delegate().onMessage(message);
            }
        }

        @Override
        public void onClose(Status status, Metadata trailers) {
            try {

            } catch (Throwable t) {
                //ignore
            } finally {
                delegate().onClose(status, trailers);
            }
        }
    }
}
