package org.yefei.qa.mock.model.bean.callback.task;

import lombok.Data;

/**
 * @author: yefei
 * @date: 2018/9/25 15:49
 */
@Data
public class RabbitmqTaskBean extends AbstractTaskBean {

    private int systemConfigID;

    private String exchangeName;

    private String routingKey;

    private RabbitMqMessageType messageType;

    private int delay = 0;

    private String msgData;

}
