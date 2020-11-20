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

package org.apache.skywalking.apm.mock.plugin.grpc.v1.interceptor;

import io.grpc.Channel;
import io.grpc.ClientInterceptors;
import org.apache.skywalking.apm.mock.agent.core.logging.api.ILog;
import org.apache.skywalking.apm.mock.agent.core.logging.api.LogManager;
import org.apache.skywalking.apm.mock.agent.core.plugin.interceptor.enhance.EnhancedInstance;
import org.apache.skywalking.apm.mock.agent.core.plugin.interceptor.enhance.InstanceMethodsAroundInterceptor;
import org.apache.skywalking.apm.mock.agent.core.plugin.interceptor.enhance.MethodInterceptResult;
import org.apache.skywalking.apm.mock.plugin.grpc.v1.GRPCClientInterceptor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * {@link HRpcGrpcClientInitInterceptor} add the interceptor for every ClientCall.
 *
 * @author yefei
 */
public class HRpcGrpcClientInitInterceptor implements InstanceMethodsAroundInterceptor {
    private static final ILog logger = LogManager.getLogger(HRpcGrpcClientInitInterceptor.class);

    private static final String CHANNEL_PROPERTY_NAME = "channel";

    @Override
    public void beforeMethod(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes,
                             MethodInterceptResult result) throws Throwable {

    }

    @Override
    public Object afterMethod(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes,
                              Object ret) throws Throwable {

        // 获取属性channel，注入拦截器，写入动态字段
        Field channelField = getChannelField(objInst);
        channelField.setAccessible(true);
        Object channel = channelField.get(objInst);
        if (channel != null) {
            if (channel instanceof Channel) {
                objInst.setSkyWalkingDynamicField(ClientInterceptors.intercept((Channel) channel, new GRPCClientInterceptor()));
                logger.info("HRpcGrpcClient.channel enhance success: {}", objInst.getClass().getName());
            } else {
                logger.info("after HRpcGrpcClient.init, channel is not instanceof Channel, real class: {}", channel.getClass().getName());
            }

        } else {
            logger.info("after HRpcGrpcClient.init, channel is null");
        }

        return ret;
    }

    private Field getChannelField(EnhancedInstance objInst) {
        return getField(objInst.getClass());
    }

    private Field getField(Class clazz) {
        try {
            return clazz.getDeclaredField(CHANNEL_PROPERTY_NAME);
        } catch (NoSuchFieldException e) {
            Class superClass = clazz.getSuperclass();
            if (superClass != null) {
                return getField(superClass);
            }
        }
        return null;
    }

    @Override
    public void handleMethodException(EnhancedInstance objInst, Method method, Object[] allArguments,
                                      Class<?>[] argumentsTypes, Throwable t) {
    }
}
