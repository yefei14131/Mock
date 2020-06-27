package org.yefei.qa.mock.model.bean.callback.task;

import lombok.Data;

/**
 * @author: yefei
 * @date: 2018/9/25 15:48
 */
@Data
public class GrpcTaskBean extends AbstractTaskBean {

    private String target;

    private String serviceName;

    private String methodName;

    private String headers;

    private String body; //json格式，发请求前格式化

}
