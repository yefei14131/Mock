package org.yefei.qa.mock.model.gen.dao;

import org.apache.ibatis.annotations.Param;
import org.yefei.qa.mock.model.gen.pojo.TblMappingRulesDetail;
import org.yefei.qa.mock.model.gen.pojo.TblMappingRulesDetailExample;

import java.util.List;

public interface TblMappingRulesDetailMapper {
    long countByExample(TblMappingRulesDetailExample example);

    int deleteByExample(TblMappingRulesDetailExample example);

    int deleteByPrimaryKey(Integer rulesDetailID);

    int insert(TblMappingRulesDetail record);

    int insertSelective(TblMappingRulesDetail record);

    List<TblMappingRulesDetail> selectByExample(TblMappingRulesDetailExample example);

    TblMappingRulesDetail selectByPrimaryKey(Integer rulesDetailID);

    int updateByExampleSelective(@Param("record") TblMappingRulesDetail record, @Param("example") TblMappingRulesDetailExample example);

    int updateByExample(@Param("record") TblMappingRulesDetail record, @Param("example") TblMappingRulesDetailExample example);

    int updateByPrimaryKeySelective(TblMappingRulesDetail record);

    int updateByPrimaryKey(TblMappingRulesDetail record);
}
