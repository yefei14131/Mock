package org.apache.skywalking.apm.mock.agent.core.mock.rest.bean;

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
