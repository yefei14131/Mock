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

import kotlin.Pair;
import org.apache.http.*;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.util.EntityUtils;
import org.apache.skywalking.apm.mock.agent.core.logging.api.ILog;
import org.apache.skywalking.apm.mock.agent.core.logging.api.LogManager;
import org.apache.skywalking.apm.mock.agent.core.mock.MockManager;
import org.apache.skywalking.apm.mock.agent.core.plugin.interceptor.enhance.EnhancedInstance;
import org.apache.skywalking.apm.mock.agent.core.plugin.interceptor.enhance.InstanceMethodsAroundInterceptor;
import org.apache.skywalking.apm.mock.agent.core.plugin.interceptor.enhance.MethodInterceptResult;
import org.apache.skywalking.apm.mock.agent.core.util.HualalaHttpUtils;
import org.apache.skywalking.apm.mock.plugin.httpClient.v4.core.MockServerRestStub;
import org.apache.skywalking.apm.mock.plugin.httpClient.v4.core.bean.HttpRequestContent;
import org.apache.skywalking.apm.mock.plugin.httpClient.v4.core.bean.MockHttpResponseProxy;
import org.apache.skywalking.apm.mock.plugin.httpClient.v4.core.bean.MockResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

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
            HttpRequestContent requestContent = getRequestContent(httpRequest);
            MockResponse mockResponse = MockServerRestStub.getMockResponse(httpHost.getHostName(), getMockRequestURI(uri), headers, requestContent);
            if (mockResponse != null) {
                CloseableHttpResponse resp = buildResponse(mockResponse);
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


    private HttpRequestContent getRequestContent(HttpRequest httpRequest) {
        try {
            if (httpRequest instanceof HttpEntityEnclosingRequest) {
                HttpEntity requstEntity = ((HttpEntityEnclosingRequest) httpRequest).getEntity();
                if (requstEntity == null) {
                    return null;
                }
                byte[] contentByte = EntityUtils.toByteArray(requstEntity);

                ByteArrayInputStream inputStream = new ByteArrayInputStream(contentByte);
                BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
                basicHttpEntity.setContent(inputStream);
                basicHttpEntity.setContentEncoding(requstEntity.getContentEncoding());
                basicHttpEntity.setContentType(requstEntity.getContentType());
                basicHttpEntity.setContentLength(requstEntity.getContentLength());

                ((HttpEntityEnclosingRequest) httpRequest).setEntity(basicHttpEntity);

                HttpRequestContent httpRequestContent = new HttpRequestContent();
                httpRequestContent.setContent(new String(contentByte));
                httpRequestContent.setContentType(requstEntity.getContentType().getValue());
                return httpRequestContent;
            }
        } catch (IOException e) {
            logger.error("转换http request 流对象出错", e);
        }
        return null;
    }

    /**
     * 将mock返回数据格式化成HttpResponse
     *
     * @param mockResponse
     * @return
     */
    private CloseableHttpResponse buildResponse(MockResponse mockResponse) {

        StatusLine statusLine = new BasicStatusLine(HttpVersion.HTTP_1_1, 200, "success");

        ByteArrayInputStream inputStream = new ByteArrayInputStream(mockResponse.getBody());
        InputStreamEntity httpEntity = new InputStreamEntity(inputStream);

        BasicHttpResponse httpResponse = new BasicHttpResponse(statusLine);
        httpResponse.setEntity(httpEntity);

        Iterator<Pair<String, String>> headerIterator = mockResponse.getHeaders().iterator();
        while (headerIterator.hasNext()) {
            Pair<String, String> next = headerIterator.next();
            httpResponse.setHeader(new BasicHeader(next.getFirst(), next.getSecond()));
        }

        MockHttpResponseProxy mockHttpResponseProxy = new MockHttpResponseProxy(httpResponse);

        return mockHttpResponseProxy;

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
