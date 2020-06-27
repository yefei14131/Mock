package org.yefei.qa.mock.rabbitmq;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: yefei
 * @date: 2018/9/10 14:51
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "rabbitmq")
public class RabbitmqProps {

    // consumer数量
    private int consumer;

    //回调具体任务
    private String callbackJobExchangeName;
    private String callbackJobQueueName;
    private String callbackJobRoutingKey;

    // 系统通知消息
    private String systemNoticeExchangeName;
    private String systemNoticeQueueName;

    // agent 推送消息
    private String agentPushExchangeName;
    private String agentPushQueueName;

}
