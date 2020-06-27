package org.apache.skywalking.apm.mock.plugin.httpClient.v4.core.bean;

import lombok.Data;
import okhttp3.Headers;

/**
 * @author yefei
 * @date: 2020/4/23
 */
@Data
public class MockResponse {
    private byte[] body;
    private Headers headers;

}
