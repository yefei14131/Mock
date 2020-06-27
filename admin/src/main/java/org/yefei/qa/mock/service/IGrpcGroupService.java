package org.yefei.qa.mock.service;

import org.yefei.qa.mock.bean.grpc.GrpcGroup;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestGroup;

import java.io.IOException;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:03
 */
public interface IGrpcGroupService {


    void importGrpcGroupData(List<GrpcGroup> grpcGroupList);

    List<GrpcGroup> makeExportGrpcGroup(String groupIDs);

    List<TblGrpcRequestGroup> queryGrpcGroupList();

    long countGrpcGroup();

    boolean saveGrpcGroup(TblGrpcRequestGroup grpcRequestGroup) throws IOException;

    boolean deleteGrpcGroup(int groupID);

    TblGrpcRequestGroup getGroup(int groupID);
}
