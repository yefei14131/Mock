package org.yefei.qa.mock.model.gen.dao;

import org.apache.ibatis.annotations.Param;
import org.yefei.qa.mock.model.gen.pojo.TblRequestEvent;
import org.yefei.qa.mock.model.gen.pojo.TblRequestEventExample;

import java.util.List;

public interface TblRequestEventMapper {
    long countByExample(TblRequestEventExample example);

    int deleteByExample(TblRequestEventExample example);

    int deleteByPrimaryKey(Integer requestEventID);

    int insert(TblRequestEvent record);

    int insertSelective(TblRequestEvent record);

    List<TblRequestEvent> selectByExampleWithBLOBs(TblRequestEventExample example);

    List<TblRequestEvent> selectByExample(TblRequestEventExample example);

    TblRequestEvent selectByPrimaryKey(Integer requestEventID);

    int updateByExampleSelective(@Param("record") TblRequestEvent record, @Param("example") TblRequestEventExample example);

    int updateByExampleWithBLOBs(@Param("record") TblRequestEvent record, @Param("example") TblRequestEventExample example);

    int updateByExample(@Param("record") TblRequestEvent record, @Param("example") TblRequestEventExample example);

    int updateByPrimaryKeySelective(TblRequestEvent record);

    int updateByPrimaryKeyWithBLOBs(TblRequestEvent record);

    int updateByPrimaryKey(TblRequestEvent record);
}
