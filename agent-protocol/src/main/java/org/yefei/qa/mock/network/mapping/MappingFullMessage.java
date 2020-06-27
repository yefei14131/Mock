package org.yefei.qa.mock.network.mapping;

import lombok.Data;
import org.yefei.qa.mock.network.websocket.message.AbstractMessageDownData;

import java.util.List;

/**
 * 全量更新接口
 * 如果为null，则表示当前没有更新，agent不错处理，保持现状
 * 如果size == 0，则表示没有对应的接口，需要清除agent本地缓存
 *
 * @author yefei
 * @date: 2020/5/11
 */
@Data
public class MappingFullMessage extends AbstractMessageDownData {
    private List<GrpcMappingAgentSimple> grpc;
    private List<RestMappingAgentSimple> rest;
}
