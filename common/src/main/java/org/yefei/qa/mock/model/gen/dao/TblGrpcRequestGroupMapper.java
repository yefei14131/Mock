package org.yefei.qa.mock.model.gen.dao;

import org.apache.ibatis.annotations.Param;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestGroup;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestGroupExample;

import java.util.List;

public interface TblGrpcRequestGroupMapper {
    long countByExample(TblGrpcRequestGroupExample example);

    int deleteByExample(TblGrpcRequestGroupExample example);

    int deleteByPrimaryKey(Integer groupID);

    int insert(TblGrpcRequestGroup record);

    int insertSelective(TblGrpcRequestGroup record);

    List<TblGrpcRequestGroup> selectByExample(TblGrpcRequestGroupExample example);

    TblGrpcRequestGroup selectByPrimaryKey(Integer groupID);

    int updateByExampleSelective(@Param("record") TblGrpcRequestGroup record, @Param("example") TblGrpcRequestGroupExample example);

    int updateByExample(@Param("record") TblGrpcRequestGroup record, @Param("example") TblGrpcRequestGroupExample example);

    int updateByPrimaryKeySelective(TblGrpcRequestGroup record);

    int updateByPrimaryKey(TblGrpcRequestGroup record);
}
