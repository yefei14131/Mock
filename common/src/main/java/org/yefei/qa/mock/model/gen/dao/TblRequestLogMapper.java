package org.yefei.qa.mock.model.gen.dao;

import org.apache.ibatis.annotations.Param;
import org.yefei.qa.mock.model.gen.pojo.TblRequestLog;
import org.yefei.qa.mock.model.gen.pojo.TblRequestLogExample;

import java.util.List;

public interface TblRequestLogMapper {
    long countByExample(TblRequestLogExample example);

    int deleteByExample(TblRequestLogExample example);

    int deleteByPrimaryKey(Integer requestID);

    int insert(TblRequestLog record);

    int insertSelective(TblRequestLog record);

    List<TblRequestLog> selectByExample(TblRequestLogExample example);

    TblRequestLog selectByPrimaryKey(Integer requestID);

    int updateByExampleSelective(@Param("record") TblRequestLog record, @Param("example") TblRequestLogExample example);

    int updateByExample(@Param("record") TblRequestLog record, @Param("example") TblRequestLogExample example);

    int updateByPrimaryKeySelective(TblRequestLog record);

    int updateByPrimaryKey(TblRequestLog record);
}
