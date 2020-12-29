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


/**
 * @author zhang xin
 */
public class CallServerInterceptor implements ServerInterceptor {
    private static final ILog logger = LogManager.getLogger(CallServerInterceptor.class);

    @Override
    public ServerCall.Listener interceptCall(ServerCall call, Metadata headers, ServerCallHandler handler) {
        return new ServerCallListener(
                handler.startCall(new ServerCallEx(call), headers),
                call.getMethodDescriptor());
    }


    public class ServerCallEx extends ForwardingServerCall.SimpleForwardingServerCall<Message, Message> {

        private final ServerCall call;

        public ServerCallEx(ServerCall delegate) {
            super(delegate);
            call = delegate;
        }

        @Override
        public void sendMessage(Message message) {
            delegate().sendMessage(message);
            try {
                logger.info("服务接口 {} 输出响应数据: {}",
                        OperationNameFormatUtil.formatOperationName(call.getMethodDescriptor()),
                        JsonFormat.printer().print(message)
                );

            } catch (Exception e) {
                //ignore
            }
        }
    }

    public class ServerCallListener extends ForwardingServerCallListener.SimpleForwardingServerCallListener<Message> {

        private final MethodDescriptor.MethodType methodType;
        private final MethodDescriptor descriptor;


        protected ServerCallListener(ServerCall.Listener delegate, MethodDescriptor descriptor) {
            super(delegate);
            this.methodType = descriptor.getType();
            this.descriptor = descriptor;
        }

        @Override
        public void onReady() {
            delegate().onReady();
        }

        @Override
        public void onMessage(Message message) {
            try {
                delegate().onMessage(message);
                logger.info("服务接口 {} 收到请求数据: {}",
                        OperationNameFormatUtil.formatOperationName(descriptor),
                        JsonFormat.printer().print(message)
                );

            } catch (Throwable t) {
                //ignore
            }
        }

        @Override
        public void onComplete() {
            delegate().onComplete();
        }

        @Override
        public void onCancel() {
            delegate().onCancel();
        }

        @Override
        public void onHalfClose() {
            delegate().onHalfClose();
        }


    }

}
