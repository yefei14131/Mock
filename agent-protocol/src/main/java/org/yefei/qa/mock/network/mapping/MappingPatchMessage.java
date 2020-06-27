package org.yefei.qa.mock.network.mapping;

import lombok.Data;
import org.yefei.qa.mock.network.websocket.message.AbstractMessageDownData;

import java.util.List;

/**
 * 增量更新接口
 *
 * @author yefei
 * @date: 2020/5/11
 */
@Data
public class MappingPatchMessage extends AbstractMessageDownData {

    private List<GrpcMappingAgentSimple> removedGrpc;
    private List<RestMappingAgentSimple> removedRest;

    private List<GrpcMappingAgentSimple> addedGrpc;
    private List<RestMappingAgentSimple> addedRest;


}
