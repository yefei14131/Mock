package org.apache.skywalking.apm.mock.plugin.okhttp.v3.mock;

import okhttp3.*;
import okio.Buffer;
import okio.BufferedSink;
import org.apache.skywalking.apm.mock.agent.core.logging.api.ILog;
import org.apache.skywalking.apm.mock.agent.core.logging.api.LogManager;
import org.apache.skywalking.apm.mock.agent.core.mock.rest.MockServerRestStub;
import org.apache.skywalking.apm.mock.agent.core.mock.rest.bean.MockResponse;
import org.apache.skywalking.apm.mock.agent.core.mock.rest.bean.RequestContent;
import org.apache.skywalking.apm.mock.agent.core.mock.rest.bean.RequestHeader;

import java.util.Iterator;

/**
 * @author yefei
 * @date: 2021/4/6
 */
public class HttpAdaptor {
    private static final ILog logger = LogManager.getLogger(HttpAdaptor.class);

    public static MockResponse getMockResponse(String hostName, String uri, Headers headers, RequestBody requestBody) {
        return MockServerRestStub.getMockResponse(hostName, uri, buildRequestHeader(headers), buildRequestContent(requestBody));
    }


    private static RequestHeader buildRequestHeader(Headers headers) {
        RequestHeader requestHeader = new RequestHeader();
        Iterator<String> headerIterator = headers.names().iterator();
        while (headerIterator.hasNext()) {
            String headerName = headerIterator.next();
            requestHeader.put(headerName, headers.get(headerName));
        }

        return requestHeader;
    }

    private static RequestContent buildRequestContent(RequestBody requestBody) {
        try {
            RequestContent requestContent = new RequestContent();
            MediaType mediaType = requestBody.contentType();
            long contentLength = requestBody.contentLength();
            BufferedSink sink = new Buffer();
            requestBody.writeTo(sink);

            byte[] bytes = new byte[(int) contentLength];
            sink.write(bytes);

            requestContent.setContent(new String(bytes));
            requestContent.setContentType(mediaType.type());

            return requestContent;
        } catch (Exception e) {
            logger.error("转换http request 流对象出错", e);
        }
        return null;
    }


    public static Response buildResponse(MockResponse mockResponse, Request request) {
        Response.Builder responseBuilder = new Response.Builder();
        Response response = responseBuilder
                .request(request)
                .protocol(Protocol.HTTP_1_1)
                .code(200)
                .receivedResponseAtMillis(System.currentTimeMillis())
                .body(ResponseBody.create(MediaType.parse(mockResponse.getContentType()), mockResponse.getBody()))
                .headers(buildResponseHeaders(mockResponse.getHeaders()))
                .build();
        return response;
    }

    private static Headers buildResponseHeaders(MockResponse.Headers headers) {
        return Headers.of(headers.toSingleValueMap());
    }
}
