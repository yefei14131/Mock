package org.yefei.qa.mock.dao.grpc;

import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestGroup;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:03
 */
public interface IGrpcGroupDao {
    int insertGrpcRequestGroup(TblGrpcRequestGroup tblGrpcRequestGroup);

    int deleteGrpcRequestGroup(int groupID);

    int updateGrpcRequestGroup(TblGrpcRequestGroup tblGrpcRequestGroup);

    List<TblGrpcRequestGroup> queryGrpcRequestGroupList();

    long countGrpcRequestGroup();

    List<TblGrpcRequestGroup> queryGrpcGroupByGroupName(String groupName);

    long countGrpcGroupByGroupName(String groupName);

    TblGrpcRequestGroup getGroup(int groupID);

    List<TblGrpcRequestGroup> queryGrpcGroupByIDS(List<Integer> groupIDS);

    List<TblGrpcRequestGroup> queryGrpcGroupByID(int groupID);
}
