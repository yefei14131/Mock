package org.yefei.qa.mock.rabbitmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.model.bean.notice.PushAgentMsgContent;
import org.yefei.qa.mock.utils.RabbitMqMessageUtils;
import org.yefei.qa.mock.websocket.WebSocketServer;

/**
 * @author yefei
 * @date: 2020/4/10
 */
@Component
@Slf4j
public class AgentPushListener<T> {

    public void handleMessage(T message) {

        try {
            log.info("收到系agent推送消息，准备开始推");

            PushAgentMsgContent pushAgentMsgContent = RabbitMqMessageUtils.formatMesage(message, PushAgentMsgContent.class);
            if (StringUtils.isEmpty(pushAgentMsgContent.getRoutingKey())) {
                // push to all
                WebSocketServer.sendMessageToAll(pushAgentMsgContent.getMsg());
            } else {
                // push to the agent
                WebSocketServer.sendMessage(pushAgentMsgContent.getMsg(), pushAgentMsgContent.getRoutingKey());
            }

        } catch (Exception e) {
            log.error("处理agent推送消息异常", e);
        }
    }
}
