package org.yefei.qa.mock.model.bean;

import lombok.Data;

/**
 * @author yefei
 * @date: 2020/4/27
 */
@Data
public class AgentMapingResponse {
    private int code;
    private AgentMapingData data;
    private String message;
}
