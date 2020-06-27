package org.yefei.qa.mock.model.gen.dao;

import org.apache.ibatis.annotations.Param;
import org.yefei.qa.mock.model.gen.pojo.TblSystemConfig;
import org.yefei.qa.mock.model.gen.pojo.TblSystemConfigExample;

import java.util.List;

public interface TblSystemConfigMapper {
    long countByExample(TblSystemConfigExample example);

    int deleteByExample(TblSystemConfigExample example);

    int deleteByPrimaryKey(Integer configID);

    int insert(TblSystemConfig record);

    int insertSelective(TblSystemConfig record);

    List<TblSystemConfig> selectByExample(TblSystemConfigExample example);

    TblSystemConfig selectByPrimaryKey(Integer configID);

    int updateByExampleSelective(@Param("record") TblSystemConfig record, @Param("example") TblSystemConfigExample example);

    int updateByExample(@Param("record") TblSystemConfig record, @Param("example") TblSystemConfigExample example);

    int updateByPrimaryKeySelective(TblSystemConfig record);

    int updateByPrimaryKey(TblSystemConfig record);
}
