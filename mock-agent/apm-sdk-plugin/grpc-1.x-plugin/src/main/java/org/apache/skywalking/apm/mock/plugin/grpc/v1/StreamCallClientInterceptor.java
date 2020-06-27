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

import static org.apache.skywalking.apm.mock.plugin.grpc.v1.OperationNameFormatUtil.formatOperationName;

/**
 * @author zhangxin
 */
public class StreamCallClientInterceptor extends ForwardingClientCall.SimpleForwardingClientCall implements CallClientInterceptor {

    private final String serviceName;
    private final String remotePeer;
    private final String operationPrefix;
    private final Channel channel;
    private final MethodDescriptor methodDescriptor;

    protected StreamCallClientInterceptor(ClientCall delegate, MethodDescriptor method, Channel channel) {
        super(delegate);
        this.channel = channel;
        this.methodDescriptor = method;
        this.serviceName = formatOperationName(method);
        this.remotePeer = channel.authority();
        this.operationPrefix = OperationNameFormatUtil.formatOperationName(method) + Constants.CLIENT;
    }

    @Override
    public void start(Listener responseListener, Metadata headers) {

    }

    @Override
    public Channel getChannel() {
        return channel;
    }

    @Override
    public MethodDescriptor getMethodDescriptor() {
        return methodDescriptor;
    }

    private class CallListener extends ForwardingClientCallListener.SimpleForwardingClientCallListener {


        protected CallListener(Listener delegate) {
            super(delegate);
        }

        @Override
        public void onReady() {
            delegate().onReady();
        }

        @Override
        public void onHeaders(Metadata headers) {
            delegate().onHeaders(headers);
        }

        @Override
        public void onMessage(Object message) {
            try {

                delegate().onMessage(message);
            } catch (Throwable t) {
            } finally {
            }
        }

        @Override
        public void onClose(Status status, Metadata trailers) {
            try {

                delegate().onClose(status, trailers);
            } catch (Throwable t) {
            } finally {
            }
        }
    }

}
