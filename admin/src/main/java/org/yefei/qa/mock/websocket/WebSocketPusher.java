package org.yefei.qa.mock.websocket;

import org.apache.commons.lang3.SerializationUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.model.bean.notice.PushAgentMsgContent;
import org.yefei.qa.mock.network.websocket.message.AbstractMessageDownData;
import org.yefei.qa.mock.network.websocket.message.MessageDown;
import org.yefei.qa.mock.network.websocket.message.MessageDownType;
import org.yefei.qa.mock.rabbitmq.RabbitmqProps;

/**
 * websocket 消息推送服务
 *
 * @author yefei
 * @date: 2020/5/11
 */
@Component
public class WebSocketPusher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitmqProps rabbitProperties;


    /**
     * 根据webSocketServer直接推送消息
     *
     * @param webSocketServer
     * @param messageData
     * @param messageType
     */
    public void push(WebSocketServer webSocketServer, AbstractMessageDownData messageData, MessageDownType messageType) {

        MessageDown downMessage = new MessageDown();
        downMessage.setMessageType(messageType);
        downMessage.setMessageData(messageData);

//        webSocketServer.sendMessage(JSONObject.toJSONString(downMessage));
        webSocketServer.sendMessage(SerializationUtils.serialize(downMessage));
    }

    /**
     * 推送消息给agent， 支持分布式
     * @param messageData
     * @param messageType
     * @param routingKey
     */
    public void push(AbstractMessageDownData messageData, MessageDownType messageType, String routingKey) {
        MessageDown downMessage = new MessageDown();
        downMessage.setMessageData(messageData);
        downMessage.setMessageType(messageType);

        sendRabbitmqMsg(downMessage, routingKey);
    }


    private void sendRabbitmqMsg(MessageDown downMessage) {
        sendRabbitmqMsg(downMessage, null);
    }

    private void sendRabbitmqMsg(MessageDown downMessage, String routingKey) {
        PushAgentMsgContent pushAgentMsgContent = new PushAgentMsgContent();
        pushAgentMsgContent.setMsg(SerializationUtils.serialize(downMessage));
        pushAgentMsgContent.setRoutingKey(routingKey);

        rabbitTemplate.convertAndSend(rabbitProperties.getAgentPushExchangeName(), null, pushAgentMsgContent);
    }


}
