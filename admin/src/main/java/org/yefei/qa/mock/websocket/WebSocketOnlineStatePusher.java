package org.yefei.qa.mock.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.model.gen.pojo.TblAgentInstance;
import org.yefei.qa.mock.network.state.AgentStateMessage;
import org.yefei.qa.mock.network.websocket.message.MessageDownType;

/**
 * websocket 消息推送服务
 *
 * @author yefei
 * @date: 2020/5/11
 */
@Component
public class WebSocketOnlineStatePusher {

    @Autowired
    private WebSocketPusher webSocketPusher;


    public void pushAgentState(TblAgentInstance agentInstance) {
        AgentStateMessage agentStateMessage = new AgentStateMessage(agentInstance.getIsActive());
        webSocketPusher.push(agentStateMessage, MessageDownType.AGENT_STATE, WebSocketServer.genRoutingKey(agentInstance.getServiceName(), agentInstance.getAgentName(), agentInstance.getIp()));
    }

    public void pushAgentState(WebSocketServer webSocketServer, boolean isActive) {
        AgentStateMessage agentStateMessage = new AgentStateMessage(isActive);
        webSocketPusher.push(webSocketServer, agentStateMessage, MessageDownType.AGENT_STATE);
    }
}
