package org.apache.skywalking.apm.mock.plugin.httpClient.v4.core.bean;

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
import org.apache.skywalking.apm.mock.agent.core.mock.rest.MockServerRestStub;
import org.apache.skywalking.apm.mock.agent.core.mock.rest.bean.MockResponse;
import org.apache.skywalking.apm.mock.agent.core.mock.rest.bean.RequestContent;
import org.apache.skywalking.apm.mock.agent.core.mock.rest.bean.RequestHeader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author yefei
 * @date: 2021/4/6
 */
public class HttpAdaptor {
    private static final ILog logger = LogManager.getLogger(HttpAdaptor.class);

    public static MockResponse getMockResponse(String hostName, String uri, Header[] headers, HttpRequest httpRequest) {
        return MockServerRestStub.getMockResponse(hostName, uri, buildRequestHeader(headers), buildRequestContent(httpRequest));
    }

    private static RequestHeader buildRequestHeader(Header[] headers) {
        RequestHeader requestHeader = new RequestHeader();
        if (headers != null && headers.length > 0) {
            for (Header header : headers) {
                requestHeader.put(header.getName(), header.getValue());
            }
        }
        return requestHeader;
    }

    private static RequestContent buildRequestContent(HttpRequest httpRequest) {
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

                RequestContent requestContent = new RequestContent();
                requestContent.setContent(new String(contentByte));
                requestContent.setContentType(requstEntity.getContentType().getValue());
                return requestContent;
            }
        } catch (IOException e) {
            logger.error("转换http request 流对象出错", e);
        }
        return null;
    }

    /**
     * mock response 转 http-client response
     *
     * @param mockResponse
     * @return
     */
    public static CloseableHttpResponse buildResponse(MockResponse mockResponse) {
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

}
