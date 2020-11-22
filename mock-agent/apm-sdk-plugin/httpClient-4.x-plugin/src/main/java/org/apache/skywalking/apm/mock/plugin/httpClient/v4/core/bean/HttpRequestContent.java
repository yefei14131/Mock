package org.apache.skywalking.apm.mock.plugin.httpClient.v4.core.bean;

import lombok.Data;

/**
 * @author yefei
 * @date: 2020/11/22
 */
@Data
public class HttpRequestContent {
    private String content;
    private String contentType;
}
