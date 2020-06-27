package org.yefei.qa.mock.websocket;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.model.gen.pojo.TblAgentInstance;
import org.yefei.qa.mock.network.websocket.message.MessageUp;
import org.yefei.qa.mock.service.IAgentManagerService;

/**
 * websocket 消息接收
 *
 * @author yefei
 * @date: 2020/5/11
 */
@Component
@Slf4j
public class WebSocketReceiver {

    @Autowired
    private IAgentManagerService agentManagerService;

    @Autowired
    private WebSocketOnlineStatePusher webSocketOnlineStatePusher;

    public void receive(WebSocketServer webSocketServer, byte[] inputMessage) {
        MessageUp message = SerializationUtils.deserialize(inputMessage);
        switch (message.getMessageType()) {
            case KEEP_ALIVE:
                // agent 续期
                agentManagerService.agentRequest(webSocketServer.getServiceName(), webSocketServer.getAgentName(), webSocketServer.getIp());
                break;
        }
    }

    public void agentRegist(WebSocketServer webSocketServer) {
        TblAgentInstance agentInstance = agentManagerService.agentRequest(webSocketServer.getServiceName(), webSocketServer.getAgentName(), webSocketServer.getIp());
        if (!agentInstance.getIsActive()) {
            webSocketOnlineStatePusher.pushAgentState(webSocketServer, false);
        }
    }
}
