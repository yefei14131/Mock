package org.yefei.qa.mock.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yefei.qa.mock.rabbitmq.RabbitmqProps;
import org.yefei.qa.mock.rabbitmq.listener.impl.CallbackJobRabbitmqListener;

import javax.annotation.Resource;

/**
 * @author: yefei
 * @date: 2018/9/10 00:46
 */
@Configuration
public class RabbitmqConfig {


    @Resource
    private RabbitmqProps rabbitmqProps;

    @Resource
    CallbackJobRabbitmqListener callbackJobRabbitmqListener;


//
//    @Bean
//    public Queue myQueue() {
//        return new Queue("rabbit-queue");
//    }
//
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
//        cachingConnectionFactory.setHost("192.168.111.103");
//        cachingConnectionFactory.setUsername("springboot");
//        cachingConnectionFactory.setPassword("123456");
//        cachingConnectionFactory.setPort(5672);
//        return cachingConnectionFactory;
//    }
//
//    @Bean
//    public AmqpAdmin amqpAdmin() {
//        return new RabbitAdmin(connectionFactory());
//    }
//
//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//        return new RabbitTemplate(connectionFactory());
//    }


//
//    @Bean
//    Queue schedulerQueue() {
//        return new Queue(rabbitmqProps.getCallbackSchedulerQueueName(), true);
//    }

//
//    @Bean
//    FanoutExchange schedulerExchange() {
//        return new FanoutExchange(rabbitmqProps.getCallbackSchedulerExchangeName());
//    }


//    @Bean
//    Binding SchedulerBinding(@Qualifier("schedulerQueue") Queue queue, @Qualifier("schedulerExchange") FanoutExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange);
//    }

    //listener
//    @Bean
//    SimpleMessageListenerContainer SchedulerContainer(ConnectionFactory connectionFactory,
//                                                      MessageListenerAdapter schedulerListenerAdapter) {
//        return getMessageListenerContainer(connectionFactory, schedulerListenerAdapter, rabbitmqProps.getCallbackSchedulerQueueName());
//    }
//
//
//    @Bean
//    MessageListenerAdapter schedulerListenerAdapter(CallbackSchedulerRabbitmqListener callbackSchedulerRabbitmqListener) {
//        return new MessageListenerAdapter(callbackSchedulerRabbitmqListener, LISTENER_METHOD);
//    }


    private SimpleMessageListenerContainer getMessageListenerContainer(ConnectionFactory connectionFactory,
                                                                       MessageListenerAdapter listenerAdapter,
                                                                       String queueName) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        container.setErrorHandler(new ConditionalRejectingErrorHandler(
                t -> t instanceof ListenerExecutionFailedException));
        container.setConcurrentConsumers(rabbitmqProps.getConsumer());
        return container;
    }


    @Bean
    DirectExchange callbackJobExchange() {
        DirectExchange exchange = new DirectExchange(rabbitmqProps.getCallbackJobExchangeName());
        exchange.setDelayed(true);
        return exchange;
    }

    @Bean
    Queue callbackJobQueue() {
        return new Queue(rabbitmqProps.getCallbackJobQueueName());
    }

    @Bean
    Binding callbackJobBinding(@Qualifier("callbackJobQueue") Queue callbackJobQueue, @Qualifier("callbackJobExchange") DirectExchange callbackJobExchange) {
        return BindingBuilder.bind(callbackJobQueue).to(callbackJobExchange).with(rabbitmqProps.getCallbackJobRoutingKey());
    }


    @Bean
    SimpleMessageListenerContainer callbackJobListenerContainer(ConnectionFactory connectionFactory,
                                                                MessageListenerAdapter callbackJobListenerAdapter) {
        return getMessageListenerContainer(connectionFactory, callbackJobListenerAdapter, rabbitmqProps.getCallbackJobQueueName());
    }

    @Bean
    MessageListenerAdapter callbackJobListenerAdapter(CallbackJobRabbitmqListener callbackJobRabbitmqListener) {
        return new MessageListenerAdapter(callbackJobRabbitmqListener);
    }

}
