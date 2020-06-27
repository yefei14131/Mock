package org.yefei.qa.mock.model.bean.callback.task;

import lombok.Data;

/**
 * @author: yefei
 * @date: 2018/9/25 15:48
 */
@Data
public class HttpTaskBean extends AbstractTaskBean {

    private String url;

    private String headers;

    private String method;  // get post

    private String body;

}
