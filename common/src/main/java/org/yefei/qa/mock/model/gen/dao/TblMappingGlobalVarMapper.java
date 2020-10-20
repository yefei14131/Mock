package org.yefei.qa.mock.model.gen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.yefei.qa.mock.model.gen.pojo.TblMappingGlobalVar;
import org.yefei.qa.mock.model.gen.pojo.TblMappingGlobalVarExample;

public interface TblMappingGlobalVarMapper {
    long countByExample(TblMappingGlobalVarExample example);

    int deleteByExample(TblMappingGlobalVarExample example);

    int deleteByPrimaryKey(Integer globalVarID);

    int insert(TblMappingGlobalVar record);

    int insertSelective(TblMappingGlobalVar record);

    List<TblMappingGlobalVar> selectByExample(TblMappingGlobalVarExample example);

    TblMappingGlobalVar selectByPrimaryKey(Integer globalVarID);

    int updateByExampleSelective(@Param("record") TblMappingGlobalVar record, @Param("example") TblMappingGlobalVarExample example);

    int updateByExample(@Param("record") TblMappingGlobalVar record, @Param("example") TblMappingGlobalVarExample example);

    int updateByPrimaryKeySelective(TblMappingGlobalVar record);

    int updateByPrimaryKey(TblMappingGlobalVar record);
}