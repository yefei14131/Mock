package org.yefei.qa.mock.model.gen.dao;

import org.apache.ibatis.annotations.Param;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestMapping;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestMappingExample;

import java.util.List;

public interface TblGrpcRequestMappingMapper {
    long countByExample(TblGrpcRequestMappingExample example);

    int deleteByExample(TblGrpcRequestMappingExample example);

    int deleteByPrimaryKey(Integer requestID);

    int insert(TblGrpcRequestMapping record);

    int insertSelective(TblGrpcRequestMapping record);

    List<TblGrpcRequestMapping> selectByExampleWithBLOBs(TblGrpcRequestMappingExample example);

    List<TblGrpcRequestMapping> selectByExample(TblGrpcRequestMappingExample example);

    TblGrpcRequestMapping selectByPrimaryKey(Integer requestID);

    int updateByExampleSelective(@Param("record") TblGrpcRequestMapping record, @Param("example") TblGrpcRequestMappingExample example);

    int updateByExampleWithBLOBs(@Param("record") TblGrpcRequestMapping record, @Param("example") TblGrpcRequestMappingExample example);

    int updateByExample(@Param("record") TblGrpcRequestMapping record, @Param("example") TblGrpcRequestMappingExample example);

    int updateByPrimaryKeySelective(TblGrpcRequestMapping record);

    int updateByPrimaryKeyWithBLOBs(TblGrpcRequestMapping record);

    int updateByPrimaryKey(TblGrpcRequestMapping record);
}
