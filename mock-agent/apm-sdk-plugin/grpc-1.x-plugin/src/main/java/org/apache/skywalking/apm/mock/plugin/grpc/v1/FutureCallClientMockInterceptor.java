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

import javax.annotation.Nullable;
import java.text.MessageFormat;
import java.util.concurrent.CountDownLatch;

/**
 * @author yefei
 * @date: 2019/9/21
 * <p>
 * 请求mock服务的拦截器
 */
public class FutureCallClientMockInterceptor extends ForwardingClientCall.SimpleForwardingClientCall<Message, Message> implements CallClientInterceptor {
    private static final ILog logger = LogManager.getLogger(FutureCallClientMockInterceptor.class);

    private final MethodDescriptor method;
    private final Channel channel;
    private final Message businessReqMessage;
    private final ClientCall.Listener<Message> businessRespListener;
    private final Runnable callback;
    private final CountDownLatch countDownLatch;

    public FutureCallClientMockInterceptor(
            ClientCall delegate,
            MethodDescriptor method,
            Channel channel,
            Message businessReqMessage,
            ClientCall.Listener<Message> businessRespListener,
            Runnable callback,
            CountDownLatch countDownLatch) {

        super(delegate);
        this.method = method;
        this.channel = channel;
        this.businessReqMessage = businessReqMessage;
        this.businessRespListener = businessRespListener;
        this.callback = callback;
        this.countDownLatch = countDownLatch;

        checkNull(businessReqMessage, "业务请求 Message");
        checkNull(businessRespListener, "业务 RespListener");
        checkNull(callback, "回调Runable");
        checkNull(countDownLatch, "countDownLatch");

        logger.debug("拦截到grpc调用mock请求, {}", method.getFullMethodName());

    }

    private void checkNull(Object target, String msg) {
        if (target == null) {
            logger.debug("grpc调用mock服务，{} 为null", msg);
        }
    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public MethodDescriptor getMethodDescriptor() {
        return method;
    }


    @Override
    public void start(Listener<Message> responseListener, Metadata headers) {
        delegate().start(new ClientCallLister(responseListener), headers);
    }

    @Override
    public void sendMessage(Message message) {
        delegate().sendMessage(businessReqMessage);
    }

    @Override
    public void cancel(@Nullable String message, @Nullable Throwable cause) {
        delegate().cancel(message, cause);
        countDownLatch.countDown();
    }

    private class ClientCallLister extends ForwardingClientCallListener.SimpleForwardingClientCallListener<Message> {
        public ClientCallLister(Listener delegate) {
            super(delegate);
        }

        @Override
        public void onMessage(Message message) {
            try {

                businessRespListener.onMessage(message);
                businessRespListener.onClose(Status.OK, new Metadata());
                callback.run();
                logger.debug("grpc mock 成功返回数据，method：{}, {}", method.getFullMethodName(), JsonFormat.printer().print(message));
            } catch (Exception e) {
                logger.error("调用GrpcMock, onMessage error, method: " + method.getFullMethodName(), e);
            } finally {
                delegate().onMessage(message);
                countDownLatch.countDown();
            }
        }


        @Override
        public void onClose(Status status, Metadata trailers) {
            try {
                // grpc 返回异常
                if (!status.isOk()) {
                    logger.error(MessageFormat.format("调用grpc mock服务异常， {0}", method.getFullMethodName()), status.asException(trailers));
                    countDownLatch.countDown();
                }
            } catch (Throwable t) {
                //ignore
            } finally {
                delegate().onClose(status, trailers);
            }
        }
    }
}
