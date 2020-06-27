package org.yefei.qa.mock.model.gen.dao;

import org.apache.ibatis.annotations.Param;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestScript;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestScriptExample;

import java.util.List;

public interface TblGrpcRequestScriptMapper {
    long countByExample(TblGrpcRequestScriptExample example);

    int deleteByExample(TblGrpcRequestScriptExample example);

    int deleteByPrimaryKey(Integer scriptID);

    int insert(TblGrpcRequestScript record);

    int insertSelective(TblGrpcRequestScript record);

    List<TblGrpcRequestScript> selectByExampleWithBLOBs(TblGrpcRequestScriptExample example);

    List<TblGrpcRequestScript> selectByExample(TblGrpcRequestScriptExample example);

    TblGrpcRequestScript selectByPrimaryKey(Integer scriptID);

    int updateByExampleSelective(@Param("record") TblGrpcRequestScript record, @Param("example") TblGrpcRequestScriptExample example);

    int updateByExampleWithBLOBs(@Param("record") TblGrpcRequestScript record, @Param("example") TblGrpcRequestScriptExample example);

    int updateByExample(@Param("record") TblGrpcRequestScript record, @Param("example") TblGrpcRequestScriptExample example);

    int updateByPrimaryKeySelective(TblGrpcRequestScript record);

    int updateByPrimaryKeyWithBLOBs(TblGrpcRequestScript record);

    int updateByPrimaryKey(TblGrpcRequestScript record);
}
