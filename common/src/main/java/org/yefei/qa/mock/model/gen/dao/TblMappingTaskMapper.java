package org.yefei.qa.mock.model.gen.dao;

import org.apache.ibatis.annotations.Param;
import org.yefei.qa.mock.model.gen.pojo.TblMappingTask;
import org.yefei.qa.mock.model.gen.pojo.TblMappingTaskExample;

import java.util.List;

public interface TblMappingTaskMapper {
    long countByExample(TblMappingTaskExample example);

    int deleteByExample(TblMappingTaskExample example);

    int deleteByPrimaryKey(Integer taskID);

    int insert(TblMappingTask record);

    int insertSelective(TblMappingTask record);

    List<TblMappingTask> selectByExampleWithBLOBs(TblMappingTaskExample example);

    List<TblMappingTask> selectByExample(TblMappingTaskExample example);

    TblMappingTask selectByPrimaryKey(Integer taskID);

    int updateByExampleSelective(@Param("record") TblMappingTask record, @Param("example") TblMappingTaskExample example);

    int updateByExampleWithBLOBs(@Param("record") TblMappingTask record, @Param("example") TblMappingTaskExample example);

    int updateByExample(@Param("record") TblMappingTask record, @Param("example") TblMappingTaskExample example);

    int updateByPrimaryKeySelective(TblMappingTask record);

    int updateByPrimaryKeyWithBLOBs(TblMappingTask record);

    int updateByPrimaryKey(TblMappingTask record);
}
