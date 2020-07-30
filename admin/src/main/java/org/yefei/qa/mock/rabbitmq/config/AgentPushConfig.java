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
import org.yefei.qa.mock.rabbitmq.listener.AgentPushListener;

import java.text.MessageFormat;
import java.util.UUID;

/**
 * @author yefei
 * @date: 2020/4/10
 */
@Configuration
@Slf4j
public class AgentPushConfig {

    @Autowired
    private RabbitmqProps rabbitmqProps;

    private String queueName;

    @Autowired
    private AgentPushListener agentPushListener;

    private synchronized String getQueueName() {
        if (queueName == null) {
            queueName = MessageFormat.format("{0}_[{1}]_{2}",
                    rabbitmqProps.getAgentPushQueueName(),
                    SystemUtils.getHostName(),
                    UUID.randomUUID().toString());
        }
        log.info("queueName: {}", queueName);
        return queueName;
    }

    @Bean
    FanoutExchange agentPushExchange() {
        return new FanoutExchange(rabbitmqProps.getAgentPushExchangeName());
    }

    @Bean
    Queue agentPushQueue() {
        return new Queue(getQueueName(), true, false, true);
    }

    @Bean
    Binding agentPushBinding(FanoutExchange agentPushExchange, Queue agentPushQueue) {
        return BindingBuilder.bind(agentPushQueue).to(agentPushExchange);
    }

    MessageListenerAdapter agentPushListenerAdapter() {
        return new MessageListenerAdapter(agentPushListener);
    }

    @Bean
    @DependsOn("agentPushBinding")
    SimpleMessageListenerContainer agentPushListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        listenerContainer.setConcurrentConsumers(rabbitmqProps.getConsumer());
        listenerContainer.addQueueNames(getQueueName());
        listenerContainer.setMessageListener(agentPushListenerAdapter());
        return listenerContainer;
    }

}
