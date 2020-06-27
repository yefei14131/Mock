package org.yefei.qa.mock.model.gen.dao;

import org.apache.ibatis.annotations.Param;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestScript;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestScriptExample;

import java.util.List;

public interface TblRestRequestScriptMapper {
    long countByExample(TblRestRequestScriptExample example);

    int deleteByExample(TblRestRequestScriptExample example);

    int deleteByPrimaryKey(Integer scriptID);

    int insert(TblRestRequestScript record);

    int insertSelective(TblRestRequestScript record);

    List<TblRestRequestScript> selectByExampleWithBLOBs(TblRestRequestScriptExample example);

    List<TblRestRequestScript> selectByExample(TblRestRequestScriptExample example);

    TblRestRequestScript selectByPrimaryKey(Integer scriptID);

    int updateByExampleSelective(@Param("record") TblRestRequestScript record, @Param("example") TblRestRequestScriptExample example);

    int updateByExampleWithBLOBs(@Param("record") TblRestRequestScript record, @Param("example") TblRestRequestScriptExample example);

    int updateByExample(@Param("record") TblRestRequestScript record, @Param("example") TblRestRequestScriptExample example);

    int updateByPrimaryKeySelective(TblRestRequestScript record);

    int updateByPrimaryKeyWithBLOBs(TblRestRequestScript record);

    int updateByPrimaryKey(TblRestRequestScript record);
}
