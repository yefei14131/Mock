package org.yefei.qa.mock.model.bean;

import lombok.Data;
import org.yefei.qa.mock.network.mapping.GrpcMappingAgentSimple;
import org.yefei.qa.mock.network.mapping.RestMappingAgentSimple;

import java.util.List;

/**
 * @author yefei
 * @date: 2020/4/27
 */
@Data
public class AgentMapingData {
    private List<GrpcMappingAgentSimple> grpc;
    private List<RestMappingAgentSimple> rest;
}
