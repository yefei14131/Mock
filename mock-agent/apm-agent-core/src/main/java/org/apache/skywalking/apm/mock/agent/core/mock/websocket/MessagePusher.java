package org.apache.skywalking.apm.mock.agent.core.mock.websocket;

import org.apache.commons.lang3.SerializationUtils;
import org.yefei.qa.mock.network.websocket.message.AbstractMessageUpData;
import org.yefei.qa.mock.network.websocket.message.MessageUp;
import org.yefei.qa.mock.network.websocket.message.MessageUpType;
import org.yefei.qa.mock.network.websocket.regist.KeepAliveMessage;

/**
 * @author yefei
 * @date: 2020/5/13
 */
public class MessagePusher {

    public static void keepAlive(MockWebSocketClient webSocketClient) {
        KeepAliveMessage keepAliveMessage = new KeepAliveMessage();
        push(webSocketClient, keepAliveMessage, MessageUpType.KEEP_ALIVE);
    }


    private static void push(MockWebSocketClient webSocketClient, AbstractMessageUpData messageData, MessageUpType messageType) {
        MessageUp messageUp = new MessageUp();
        messageUp.setMessageType(messageType);
        messageUp.setMessageData(messageData);

        webSocketClient.send(SerializationUtils.serialize(messageUp));
    }

}
