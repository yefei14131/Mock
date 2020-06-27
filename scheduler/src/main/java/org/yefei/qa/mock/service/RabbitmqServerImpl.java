package org.yefei.qa.mock.service;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: yefei
 * @date: 2018/9/23 07:37
 */
public class RabbitmqServerImpl {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void sendMsg(String queueName, String msg) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("消息发送时间:"+sdf.format(new Date()));
        rabbitTemplate.convertAndSend("test_exchange", queueName, msg, new MessagePostProcessor() {

            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay",3000);
                return message;
            }
        });
    }



}
