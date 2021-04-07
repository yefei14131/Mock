package org.apache.skywalking.apm.mock.plugin.okhttp.v3.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http.RealInterceptorChain;
import org.apache.skywalking.apm.mock.agent.core.logging.api.ILog;
import org.apache.skywalking.apm.mock.agent.core.logging.api.LogManager;
import org.apache.skywalking.apm.mock.agent.core.mock.MockManager;
import org.apache.skywalking.apm.mock.agent.core.mock.rest.bean.MockResponse;
import org.apache.skywalking.apm.mock.agent.core.plugin.interceptor.enhance.EnhancedInstance;
import org.apache.skywalking.apm.mock.agent.core.plugin.interceptor.enhance.InstanceMethodsAroundInterceptor;
import org.apache.skywalking.apm.mock.agent.core.plugin.interceptor.enhance.MethodInterceptResult;
import org.apache.skywalking.apm.mock.plugin.okhttp.v3.mock.HttpAdaptor;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;

/**
 * @author yefei
 * @date: 2021/4/6
 */
public class CallServerInterceptorInterceptor implements InstanceMethodsAroundInterceptor {
    private static final String MOCK_AGENT_CLIENT_HEADER = "mock-agent-client";
    private static final ILog logger = LogManager.getLogger(CallServerInterceptorInterceptor.class);

    @Override
    public void beforeMethod(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, MethodInterceptResult result) throws Throwable {
        Interceptor.Chain chain = (Interceptor.Chain) allArguments[0];
        RealInterceptorChain realChain = (RealInterceptorChain) chain;
        Request request = realChain.request();
        String host = request.url().host();
        URI uri = request.url().uri();

        String mockAgentClientHeaderFlag = request.headers().get(MOCK_AGENT_CLIENT_HEADER);
        if (mockAgentClientHeaderFlag != null) {
            logger.debug("mock agent 调用服务端的请求自身的请求：{} , 忽略", uri);
            return;
        }
        logger.debug("拦截到okhttp请求：{}", uri);
        String requestURI = getRequestURI(uri);

        if (!MockManager.isRestMocking(host, requestURI)) {
            logger.debug("非mock状态，请求：{}, host: {}, uri: {}", uri, host, requestURI);
            return;
        }
        logger.debug("mocking，请求：{}", uri);
        MockResponse mockResponse = HttpAdaptor.getMockResponse(host, getMockRequestURI(uri), request.headers(), request.body());
        if (mockResponse != null) {
            Response resp = HttpAdaptor.buildResponse(mockResponse, request);
            result.defineReturnValue(resp);
            logger.debug("mock响应数据, uri: {}, header: {},  body: {}", uri, mockResponse.getHeaders().toString(), new String(mockResponse.getBody()));
        }
    }

    @Override
    public Object afterMethod(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, Object ret) throws Throwable {
        return ret;
    }

    @Override
    public void handleMethodException(EnhancedInstance objInst, Method method, Object[] allArguments, Class<?>[] argumentsTypes, Throwable t) {

    }

    private String getRequestURI(URI uri) {
        String requestPath = uri.getPath();
        return requestPath != null && requestPath.length() > 0 ? requestPath : "/";
    }

    private String getMockRequestURI(URI uri) throws MalformedURLException {
        String requestPath = getRequestURI(uri);
        return uri.getQuery() == null ? requestPath : requestPath + "?" + uri.getQuery();
    }
}
