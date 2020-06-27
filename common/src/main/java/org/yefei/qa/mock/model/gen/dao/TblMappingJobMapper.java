package org.yefei.qa.mock.model.gen.dao;

import org.apache.ibatis.annotations.Param;
import org.yefei.qa.mock.model.gen.pojo.TblMappingJob;
import org.yefei.qa.mock.model.gen.pojo.TblMappingJobExample;

import java.util.List;

public interface TblMappingJobMapper {
    long countByExample(TblMappingJobExample example);

    int deleteByExample(TblMappingJobExample example);

    int deleteByPrimaryKey(Integer jobID);

    int insert(TblMappingJob record);

    int insertSelective(TblMappingJob record);

    List<TblMappingJob> selectByExample(TblMappingJobExample example);

    TblMappingJob selectByPrimaryKey(Integer jobID);

    int updateByExampleSelective(@Param("record") TblMappingJob record, @Param("example") TblMappingJobExample example);

    int updateByExample(@Param("record") TblMappingJob record, @Param("example") TblMappingJobExample example);

    int updateByPrimaryKeySelective(TblMappingJob record);

    int updateByPrimaryKey(TblMappingJob record);
}
