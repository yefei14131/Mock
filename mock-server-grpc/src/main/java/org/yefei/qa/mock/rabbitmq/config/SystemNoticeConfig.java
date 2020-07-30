package org.yefei.qa.mock.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.yefei.qa.mock.utils.SystemUtils;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.yefei.qa.mock.rabbitmq.RabbitmqProps;
import org.yefei.qa.mock.rabbitmq.listener.SystemNoticeListener;

import java.text.MessageFormat;
import java.util.UUID;

/**
 * @author yefei
 * @date: 2020/4/10
 */
@Configuration
@Slf4j
public class SystemNoticeConfig {

    @Autowired
    private RabbitmqProps rabbitmqProps;

    private String queueName;


    @Autowired
    private SystemNoticeListener systemNoticeListener;

    private synchronized String getQueueName() {
        if (queueName == null) {
            queueName = MessageFormat.format("{0}_[{1}]_{2}",
                    rabbitmqProps.getSystemNoticeQueueName(),
                    SystemUtils.getHostName(),
                    UUID.randomUUID().toString());
        }
        log.info("queueName: {}", queueName);
        return queueName;
    }

    @Bean
    FanoutExchange systemNoticeExchange() {
        return new FanoutExchange(rabbitmqProps.getSystemNoticeExchangeName());
    }

    @Bean
    Queue systemNoticeQueue() {
        return new Queue(getQueueName(), true, true, false);
    }

    @Bean
    Binding systemNoticeBinding(FanoutExchange systemNoticeExchange, Queue systemNoticeQueue) {
        return BindingBuilder.bind(systemNoticeQueue).to(systemNoticeExchange);
    }

    MessageListenerAdapter systemNoticeListenerAdapter() {
        return new MessageListenerAdapter(systemNoticeListener);
    }

    @Bean
    @DependsOn("systemNoticeBinding")
    SimpleMessageListenerContainer systemNoticeListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        listenerContainer.setConcurrentConsumers(rabbitmqProps.getConsumer());
        listenerContainer.addQueueNames(getQueueName());
        listenerContainer.setMessageListener(systemNoticeListenerAdapter());
        return listenerContainer;
    }

}
