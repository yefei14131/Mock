package org.yefei.qa.mock.rabbitmq.listener;

/**
 * @author: yefei
 * @date: 2018/9/12 11:10
 */
public interface RabbitmqListener {

    public <T> void handleMessage(T message);

}
