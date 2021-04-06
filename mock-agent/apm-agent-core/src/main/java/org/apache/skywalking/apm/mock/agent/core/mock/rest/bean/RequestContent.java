package org.apache.skywalking.apm.mock.agent.core.mock.rest.bean;

import lombok.Data;

/**
 * @author yefei
 * @date: 2020/11/22
 */
@Data
public class RequestContent {
    private String content;
    private String contentType;
}
