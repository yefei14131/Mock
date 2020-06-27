package org.apache.skywalking.apm.mock.agent.core.mock.websocket;

import org.apache.commons.lang3.SerializationUtils;
import org.apache.skywalking.apm.mock.agent.core.boot.ServiceManager;
import org.apache.skywalking.apm.mock.agent.core.logging.api.ILog;
import org.apache.skywalking.apm.mock.agent.core.logging.api.LogManager;
import org.apache.skywalking.apm.mock.agent.core.mock.MockServerAdminService;
import org.yefei.qa.mock.network.mapping.MappingFullMessage;
import org.yefei.qa.mock.network.state.AgentStateMessage;
import org.yefei.qa.mock.network.websocket.message.MessageDown;

/**
 * @author yefei
 * @date: 2020/5/13
 */
public class MessageReceiver {
    private static final ILog logger = LogManager.getLogger(MockServerAdminWebSocketClient.class);

    public static void receive(byte[] inputMessage) {
        try {

            MessageDown messageDown = SerializationUtils.deserialize(inputMessage);
            switch (messageDown.getMessageType()) {
                case API_FULL:
                    ServiceManager.INSTANCE.findService(MockServerAdminService.class).updateFullApi((MappingFullMessage) messageDown.getMessageData());
                    break;
                case API_UPDATE:

                    break;
                case AGENT_STATE:
                    ServiceManager.INSTANCE.findService(MockServerAdminService.class).updateIsAcive(((AgentStateMessage) messageDown.getMessageData()).isActive());
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            logger.error("websocket接收消息异常", e);
        }
    }
}
