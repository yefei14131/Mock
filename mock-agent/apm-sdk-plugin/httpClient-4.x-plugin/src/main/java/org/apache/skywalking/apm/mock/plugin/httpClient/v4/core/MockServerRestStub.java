package org.apache.skywalking.apm.mock.plugin.httpClient.v4.core;


import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.skywalking.apm.mock.agent.core.logging.api.ILog;
import org.apache.skywalking.apm.mock.agent.core.logging.api.LogManager;
import org.apache.skywalking.apm.mock.agent.core.mock.MockManager;
import org.apache.skywalking.apm.mock.plugin.httpClient.v4.core.bean.HttpRequestContent;
import org.apache.skywalking.apm.mock.plugin.httpClient.v4.core.bean.MockResponse;
import org.apache.skywalking.apm.mock.plugin.httpClient.v4.core.bean.OKHttpClientInstance;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author yefei
 * @date: 2020/4/23
 */
public class MockServerRestStub {


    private static OkHttpClient okHttpClient = OKHttpClientInstance.INSTANCE.instance();

    private static final ILog logger = LogManager.getLogger(MockServerRestStub.class);

    public static MockResponse getMockResponse(String host, String uri, Header[] headers, HttpRequestContent httpRequestContent) {
        // 准备header
        Headers okHeaders = null;
        if (headers != null && headers.length > 0) {
            HashMap<String, String> headerMap = new HashMap<>();
            for (Header header : headers) {
                headerMap.put(header.getName(), header.getValue());
            }
            okHeaders = Headers.of(headerMap);
        }

        return getMockResponse(MockManager.buildMockUrl(host, uri), okHeaders, httpRequestContent);

    }

    private static void setBody(Request.Builder requestBuilder, HttpRequestContent httpRequestContent) {
        String content = httpRequestContent.getContent();
        if (StringUtils.isNotEmpty(content)) {
            // form 表单
            if (content.startsWith("[")) {
                ArrayList<LinkedTreeMap> list = new Gson().fromJson(content, ArrayList.class);
                HashMap body = new HashMap();
                list.forEach(item -> {
                    body.putAll(item);
                });

                requestBuilder.post(RequestBody.create(new Gson().toJson(body).getBytes()));
                requestBuilder.header("Content-Type", "application/json");
            } else if (content.startsWith("{")) {
                // content-type: json
                requestBuilder.post(RequestBody.create(content.getBytes()));
                requestBuilder.header("Content-Type", "application/json");
            } else {
                requestBuilder.post(RequestBody.create(content.getBytes()));
                requestBuilder.header("Content-Type", httpRequestContent.getContentType());
                return;
            }
        }
    }

    private static MockResponse getMockResponse(String url, Headers headers, HttpRequestContent httpRequestContent) {
        logger.debug("准备调用mock服务: {}", url);
        Request.Builder requestBuilder = new Request.Builder().url(url);

        if (headers != null) {
            requestBuilder.headers(headers);
        }

        setBody(requestBuilder, httpRequestContent);

        Request request = requestBuilder.build();

        Call call = okHttpClient.newCall(request);

        Response response = null;
        byte[] bytes = null;
        try {

            response = call.execute();
            bytes = response.body().bytes();

            if (response.code() != 200 || !mockRespSuccess(response)) {
                logger.debug("mock 服务返回异常：code：{}, body: {}", response.code(), response.body().string());
                return null;
            }
            logger.debug("mock服务返回: {}， 请求url: {}", new String(bytes), url);
            MockResponse mockResponse = new MockResponse();
            mockResponse.setBody(bytes);
            mockResponse.setHeaders(response.headers());

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

    private static boolean mockRespSuccess(Response response) {
        return response.header("mockserver_response_error") == null;
    }

}
