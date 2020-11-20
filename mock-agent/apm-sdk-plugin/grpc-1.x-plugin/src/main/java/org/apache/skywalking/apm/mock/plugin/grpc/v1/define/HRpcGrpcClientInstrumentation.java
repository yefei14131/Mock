package org.apache.skywalking.apm.mock.plugin.grpc.v1.define;

import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.matcher.ElementMatcher;
import org.apache.skywalking.apm.mock.agent.core.plugin.interceptor.ConstructorInterceptPoint;
import org.apache.skywalking.apm.mock.agent.core.plugin.interceptor.InstanceMethodsInterceptPoint;
import org.apache.skywalking.apm.mock.agent.core.plugin.interceptor.enhance.ClassInstanceMethodsEnhancePluginDefine;
import org.apache.skywalking.apm.mock.agent.core.plugin.match.ClassMatch;
import org.apache.skywalking.apm.mock.agent.core.plugin.match.NameMatch;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * @author yefei
 * @date: 2020/11/20
 */
public class HRpcGrpcClientInstrumentation extends ClassInstanceMethodsEnhancePluginDefine {

    public static final String ENHANCE_CLASS = "com.hualala.hrpc.client.HRpcGrpcClient";
    public static final String INTERCEPT_GET_CHANNEL_CLASS = "org.apache.skywalking.apm.mock.plugin.grpc.v1.interceptor.HRpcGrpcClientGetChannelInterceptor";
    public static final String ENHANCE_GET_CHANNEL_METHOD = "getChannel";

    public static final String INTERCEPT_INIT_CLASS = "org.apache.skywalking.apm.mock.plugin.grpc.v1.interceptor.HRpcGrpcClientInitInterceptor";
    public static final String ENHANCE_INIT_METHOD = "init";

    @Override
    protected ClassMatch enhanceClass() {
        return NameMatch.byName(ENHANCE_CLASS);
    }

    @Override
    public ConstructorInterceptPoint[] getConstructorsInterceptPoints() {
        return new ConstructorInterceptPoint[0];
    }

    @Override
    public InstanceMethodsInterceptPoint[] getInstanceMethodsInterceptPoints() {
        return new InstanceMethodsInterceptPoint[]{
                new InstanceMethodsInterceptPoint() {
                    @Override
                    public ElementMatcher<MethodDescription> getMethodsMatcher() {
                        return named(ENHANCE_GET_CHANNEL_METHOD);
                    }

                    @Override
                    public String getMethodsInterceptor() {
                        return INTERCEPT_GET_CHANNEL_CLASS;
                    }

                    @Override
                    public boolean isOverrideArgs() {
                        return false;
                    }
                },
                new InstanceMethodsInterceptPoint() {
                    @Override
                    public ElementMatcher<MethodDescription> getMethodsMatcher() {
                        return named(ENHANCE_INIT_METHOD);
                    }

                    @Override
                    public String getMethodsInterceptor() {
                        return INTERCEPT_INIT_CLASS;
                    }

                    @Override
                    public boolean isOverrideArgs() {
                        return false;
                    }
                }
        };
    }
}
