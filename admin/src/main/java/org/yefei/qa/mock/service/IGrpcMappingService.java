package org.yefei.qa.mock.service;

import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestMapping;
import org.yefei.qa.mock.network.mapping.GrpcMappingAgentSimple;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:06
 */
public interface IGrpcMappingService {
    List<TblGrpcRequestMapping> queryGrpcMappingList(int groupID);

    long countGrpcMapping(int groupID);

    int saveGrpcMapping(TblGrpcRequestMapping grpcRequestMapping);

    boolean deleteGrpcMapping(int requestID);

    TblGrpcRequestMapping getMapping(int requestID);

    boolean clone(int sourceRequestID);

    List<GrpcMappingAgentSimple> queryAllActiveMapping();
}
