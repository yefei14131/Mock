package org.yefei.qa.mock.model.bean.config;

import lombok.Data;

/**
 * @author: yefei
 * @date: 2018/9/26 11:24
 */

@Data
public class MockServerHostConfigBean {

    private String host;

    private int port;

    private String protocol;

    private String desc;

}
