package org.apache.skywalking.apm.mock.agent.core.mock.rest;


import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.skywalking.apm.mock.agent.core.logging.api.ILog;
import org.apache.skywalking.apm.mock.agent.core.logging.api.LogManager;
import org.apache.skywalking.apm.mock.agent.core.mock.MockManager;
import org.apache.skywalking.apm.mock.agent.core.mock.rest.bean.MockResponse;
import org.apache.skywalking.apm.mock.agent.core.mock.rest.bean.OkHttpClientInstance;
import org.apache.skywalking.apm.mock.agent.core.mock.rest.bean.RequestContent;
import org.apache.skywalking.apm.mock.agent.core.mock.rest.bean.RequestHeader;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author yefei
 * @date: 2020/4/23
 */
public class MockServerRestStub {

    private static final String MOCK_AGENT_CLIENT_HEADER = "mock-agent-client";
    private static final String MOCK_SERVER_RESPONSE_ERROR_HEADER = "mockserver_response_error";
    private static OkHttpClient okHttpClient = OkHttpClientInstance.INSTANCE.instance();

    private static final ILog logger = LogManager.getLogger(MockServerRestStub.class);

    /**
     * 调用mock服务获取mock响应结果，发生请求异常、mock规则未匹配会返回null
     *
     * @param host           请求原本的host
     * @param uri            请求原本的uri
     * @param headers        请求原本的header，map格式
     * @param requestContent 请求体
     * @returnmo
     */
    public static MockResponse getMockResponse(String host, String uri, RequestHeader headers, RequestContent requestContent) {
        // 准备header
        if (headers == null) {
            headers = new RequestHeader();
        }
        headers.put(MOCK_AGENT_CLIENT_HEADER, "true");
        Headers okHeaders = Headers.of(headers);
        return getMockResponse(MockManager.buildMockUrl(host, uri), okHeaders, requestContent);
    }

    private static void setRequestBody(Request.Builder requestBuilder, RequestContent requestContent) {
        String content = requestContent.getContent();
        if (StringUtils.isNotEmpty(content)) {
            // form 表单
            if (content.startsWith("[")) {
                ArrayList<LinkedTreeMap> list = new Gson().fromJson(content, ArrayList.class);
                HashMap body = new HashMap();
                list.forEach(item -> {
                    body.putAll(item);
                });
                requestBuilder.post(RequestBody.create(MediaType.parse("application/json"), new Gson().toJson(body).getBytes()));
                requestBuilder.header("Content-Type", "application/json");
            } else if (content.startsWith("{")) {
                // content-type: json
                requestBuilder.post(RequestBody.create(MediaType.parse("application/json"), content.getBytes()));
                requestBuilder.header("Content-Type", "application/json");
            } else {
                requestBuilder.post(RequestBody.create(MediaType.parse(requestContent.getContentType()), content.getBytes()));
                requestBuilder.header("Content-Type", requestContent.getContentType());
                return;
            }
        }
    }

    private static MockResponse getMockResponse(String url, Headers headers, RequestContent requestContent) {
        logger.debug("准备调用mock服务: {}", url);
        Request.Builder requestBuilder = new Request.Builder().url(url);

        if (headers != null) {
            requestBuilder.headers(headers);
        }

        setRequestBody(requestBuilder, requestContent);

        Request request = requestBuilder.build();

        Call call = okHttpClient.newCall(request);

        Response response = null;
        byte[] bytes;
        try {

            response = call.execute();
            bytes = response.body().bytes();

            if (response.code() != 200 || !mockRespSuccess(response)) {
                logger.debug("mock 服务返回异常：code：{}, body: {}", response.code(), response.body().string());
                return null;
            }
            logger.debug("mock服务返回, body: {}， header: {}, 请求url: {}", new String(bytes), response.headers().toString(), url);
            MockResponse mockResponse = new MockResponse();
            mockResponse.setBody(bytes);
            mockResponse.setHeaders(new MockResponse.Headers(response.headers()));

            return mockResponse;
        } catch (Exception e) {
            logger.error("请求mock服务异常", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (Exception e) {

            }
        }
        return null;
    }

    /**
     * mock server 是否响应正确的业务数据
     * mock 响应异常会在header里添加mockserver_response_error
     *
     * @param response
     * @return
     */
    private static boolean mockRespSuccess(Response response) {
        return response.header(MOCK_SERVER_RESPONSE_ERROR_HEADER) == null;
    }

}
