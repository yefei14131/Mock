package org.yefei.qa.mock.websocket;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMapping;
import org.yefei.qa.mock.network.mapping.GrpcMappingAgentSimple;
import org.yefei.qa.mock.network.mapping.MappingFullMessage;
import org.yefei.qa.mock.network.mapping.MappingPatchMessage;
import org.yefei.qa.mock.network.mapping.RestMappingAgentSimple;
import org.yefei.qa.mock.network.websocket.message.MessageDown;
import org.yefei.qa.mock.network.websocket.message.MessageDownType;
import org.yefei.qa.mock.service.IGrpcMappingService;
import org.yefei.qa.mock.service.IRestMappingService;
import org.yefei.qa.mock.utils.AgentMappingTransfer;

import java.util.List;

/**
 * websocket 消息推送服务
 *
 * @author yefei
 * @date: 2020/5/11
 */
@Component
public class WebSocketMappingPusher {

    @Autowired
    private IRestMappingService restMappingService;

    @Autowired
    private IGrpcMappingService grpcMappingService;

    @Autowired
    private WebSocketPusher webSocketPusher;

    /**
     * websocket连接时，推送所有接口数据
     *
     * @param webSocketServer
     */
    public void pushAllMapping(WebSocketServer webSocketServer) {

        List<RestMappingAgentSimple> restMappings = restMappingService.queryAllActiveMapping();
        List<GrpcMappingAgentSimple> grpcMappings = grpcMappingService.queryAllActiveMapping();

        MappingFullMessage mappingFullMessage = new MappingFullMessage();
        mappingFullMessage.setGrpc(grpcMappings);
        mappingFullMessage.setRest(restMappings);

        MessageDown downMessage = new MessageDown();
        downMessage.setMessageType(MessageDownType.API_FULL);
        downMessage.setMessageData(mappingFullMessage);

        webSocketServer.sendMessage(SerializationUtils.serialize(downMessage));
//        webSocketServer.sendObject(downMessage);
    }


    /**
     * 向agent推送所有的rest接口
     */
    public void pushRestMapping() {
        List<RestMappingAgentSimple> restMappings = restMappingService.queryAllActiveMapping();
        MappingFullMessage mappingFullMessage = new MappingFullMessage();
        mappingFullMessage.setRest(restMappings.size() == 0 ? null : restMappings);

        webSocketPusher.push(mappingFullMessage, MessageDownType.API_FULL, null);
    }

    /**
     * 向agent推送所有的grpc接口
     */
    public void pushGrpcMapping() {
        List<GrpcMappingAgentSimple> grpcMappings = grpcMappingService.queryAllActiveMapping();
        MappingFullMessage mappingFullMessage = new MappingFullMessage();
        mappingFullMessage.setGrpc(grpcMappings.size() == 0 ? null : grpcMappings);
        webSocketPusher.push(mappingFullMessage, MessageDownType.API_FULL, null);
    }

    private void pushPatchMapping(List<GrpcMappingAgentSimple> addedGrpc, List<RestMappingAgentSimple> addedRest, List<GrpcMappingAgentSimple> removedGrpc, List<RestMappingAgentSimple> removedRest) {
        if (addedRest == null && addedGrpc == null && removedGrpc == null && removedRest == null) {
            return;
        }

        MappingPatchMessage mappingPatchMessage = new MappingPatchMessage();

        if (addedRest != null)
            mappingPatchMessage.setAddedRest(addedRest);

        if (addedGrpc != null)
            mappingPatchMessage.setAddedGrpc(addedGrpc);

        if (removedRest != null)
            mappingPatchMessage.setRemovedRest(removedRest);

        if (removedGrpc != null)
            mappingPatchMessage.setRemovedGrpc(removedGrpc);

        webSocketPusher.push(mappingPatchMessage, MessageDownType.API_UPDATE, null);
    }

    public void pushPatchRestMapping(List<TblRestRequestMapping> addList, List<TblRestRequestMapping> removedList) {

    }

    // TODO 增量精细化推送
    public void pushPatchRestMapping(TblRestRequestMapping newMapping, TblRestRequestMapping orgMapping, String host) {
        RestMappingAgentSimple addedMapping = null, removedMapping = null;
        if (!newMapping.getIsActive()) {
            removedMapping = new RestMappingAgentSimple();
            AgentMappingTransfer.restMapping2AgentMapping(orgMapping, removedMapping, host);

        } else if (!orgMapping.getIsActive()) {
            // 由不可用变为可用
            addedMapping = new RestMappingAgentSimple();
            AgentMappingTransfer.restMapping2AgentMapping(newMapping, addedMapping, host);
        } else if (!newMapping.getPath().equals(orgMapping.getPath())) {
            // uri发生变化
            addedMapping = new RestMappingAgentSimple();
            AgentMappingTransfer.restMapping2AgentMapping(newMapping, addedMapping, host);

            removedMapping = new RestMappingAgentSimple();
            AgentMappingTransfer.restMapping2AgentMapping(orgMapping, removedMapping, host);
        }

        if (addedMapping != null || removedMapping != null) {
            pushPatchMapping(
                    null,
                    addedMapping == null ? null : Lists.newArrayList(addedMapping),
                    null,
                    removedMapping == null ? null : Lists.newArrayList(removedMapping)
            );
        }
    }


    public void pushPatchGrpcMapping() {

    }


}
