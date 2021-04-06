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

package org.apache.skywalking.apm.mock.plugin.httpClient.v4;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.skywalking.apm.mock.agent.core.logging.api.ILog;
import org.apache.skywalking.apm.mock.agent.core.logging.api.LogManager;
import org.apache.skywalking.apm.mock.agent.core.mock.MockManager;
import org.apache.skywalking.apm.mock.agent.core.mock.rest.bean.MockResponse;
import org.apache.skywalking.apm.mock.agent.core.plugin.interceptor.enhance.EnhancedInstance;
import org.apache.skywalking.apm.mock.agent.core.plugin.interceptor.enhance.InstanceMethodsAroundInterceptor;
import org.apache.skywalking.apm.mock.agent.core.plugin.interceptor.enhance.MethodInterceptResult;
import org.apache.skywalking.apm.mock.agent.core.util.HualalaHttpUtils;
import org.apache.skywalking.apm.mock.plugin.httpClient.v4.core.bean.HttpAdaptor;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpClientExecuteInterceptor implements InstanceMethodsAroundInterceptor {
    private static final ILog logger = LogManager.getLogger(HttpClientExecuteInterceptor.class);
    private static final String ENCODE = "UTF-8";

    @Override
    public void beforeMethod(EnhancedInstance objInst, Method method, Object[] allArguments,
                             Class<?>[] argumentsTypes, MethodInterceptResult result) throws Throwable {
        if (allArguments[0] == null || allArguments[1] == null) {
            // illegal args, can't trace. ignore.
            return;
        }
        try {
            final HttpHost httpHost = (HttpHost) allArguments[0];
            HttpRequest httpRequest = (HttpRequest) allArguments[1];

            String uri = httpRequest.getRequestLine().getUri();
            // 忽略hll-etcd请求
            if (HualalaHttpUtils.isHualalaEtcd(uri)) {
                return;
            }
            logger.debug("拦截到httpclient请求：{}", uri);
            String requestURI = getRequestURI(uri);

            if (!MockManager.isRestMocking(httpHost.getHostName(), requestURI)) {
                logger.debug("非mock状态，请求：{}", uri);
                return;
            }
            logger.debug("mocking，请求：{}", uri);

            // 请求header
            Header[] headers = httpRequest.getAllHeaders();
            MockResponse mockResponse = HttpAdaptor.getMockResponse(httpHost.getHostName(), getMockRequestURI(uri), headers, httpRequest);
            if (mockResponse != null) {
                CloseableHttpResponse resp = HttpAdaptor.buildResponse(mockResponse);
                result.defineReturnValue(resp);
                logger.debug("mock响应数据, uri: uri, body: {}", uri, new String(mockResponse.getBody()));
            }

        } catch (Exception e) {
            logger.error("http client before execute error ", e);
        }
    }

    @Override
    public Object afterMethod(EnhancedInstance objInst, Method method, Object[] allArguments,
                              Class<?>[] argumentsTypes, Object ret) throws Throwable {
        if (allArguments[0] == null || allArguments[1] == null) {
            return ret;
        }
        return ret;
    }

    @Override
    public void handleMethodException(EnhancedInstance objInst, Method method, Object[] allArguments,
                                      Class<?>[] argumentsTypes, Throwable t) {

    }

    private String getRequestURI(String uri) throws MalformedURLException {
        if (isUrl(uri)) {
            String requestPath = new URL(uri).getPath();
            return requestPath != null && requestPath.length() > 0 ? requestPath : "/";
        } else {
            return uri;
        }
    }

    private String getMockRequestURI(String uri) throws MalformedURLException {
        if (isUrl(uri)) {
            URL url = new URL(uri);

            String requestPath = url.getPath();
            return (requestPath != null && requestPath.length() > 0 ? requestPath : "/") + "?" + url.getQuery();
        } else {
            return uri;
        }
    }

    private boolean isUrl(String uri) {
        String lowerUrl = uri.toLowerCase();
        return lowerUrl.startsWith("http") || lowerUrl.startsWith("https");
    }

    private String buildSpanValue(HttpHost httpHost, String uri) {
        if (isUrl(uri)) {
            return uri;
        } else {
            StringBuilder buff = new StringBuilder();
            buff.append(httpHost.getSchemeName().toLowerCase());
            buff.append("://");
            buff.append(httpHost.getHostName());
            buff.append(":");
            buff.append(port(httpHost));
            buff.append(uri);
            return buff.toString();
        }
    }

    private int port(HttpHost httpHost) {
        int port = httpHost.getPort();
        return port > 0 ? port : "https".equals(httpHost.getSchemeName().toLowerCase()) ? 443 : 80;
    }

}
