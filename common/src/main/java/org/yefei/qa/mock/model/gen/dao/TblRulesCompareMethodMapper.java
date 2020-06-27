package org.yefei.qa.mock.model.gen.dao;

import org.apache.ibatis.annotations.Param;
import org.yefei.qa.mock.model.gen.pojo.TblRulesCompareMethod;
import org.yefei.qa.mock.model.gen.pojo.TblRulesCompareMethodExample;

import java.util.List;

public interface TblRulesCompareMethodMapper {
    long countByExample(TblRulesCompareMethodExample example);

    int deleteByExample(TblRulesCompareMethodExample example);

    int deleteByPrimaryKey(Integer compareMethodID);

    int insert(TblRulesCompareMethod record);

    int insertSelective(TblRulesCompareMethod record);

    List<TblRulesCompareMethod> selectByExample(TblRulesCompareMethodExample example);

    TblRulesCompareMethod selectByPrimaryKey(Integer compareMethodID);

    int updateByExampleSelective(@Param("record") TblRulesCompareMethod record, @Param("example") TblRulesCompareMethodExample example);

    int updateByExample(@Param("record") TblRulesCompareMethod record, @Param("example") TblRulesCompareMethodExample example);

    int updateByPrimaryKeySelective(TblRulesCompareMethod record);

    int updateByPrimaryKey(TblRulesCompareMethod record);
}
