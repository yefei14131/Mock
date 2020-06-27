package org.yefei.qa.mock.grpc.client;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author yefei
 * @date: 2020/4/17
 */
@Component
public class GrpcRequestHeaderHolder {

    private ThreadLocal<HashMap<String, String>> headers = new ThreadLocal<>();

    public HashMap<String, String> getHeaders() {
        return headers.get();
    }

    public void setHeaders(HashMap<String, String> headers) {
        this.headers.set(headers);
    }

    public void cleanHeaders() {
        headers.remove();
    }
}
