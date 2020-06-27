package org.yefei.qa.mock.model.gen.dao;

import org.apache.ibatis.annotations.Param;
import org.yefei.qa.mock.model.gen.pojo.TblAgentInstance;
import org.yefei.qa.mock.model.gen.pojo.TblAgentInstanceExample;

import java.util.List;

public interface TblAgentInstanceMapper {
    long countByExample(TblAgentInstanceExample example);

    int deleteByExample(TblAgentInstanceExample example);

    int deleteByPrimaryKey(Integer ID);

    int insert(TblAgentInstance record);

    int insertSelective(TblAgentInstance record);

    List<TblAgentInstance> selectByExample(TblAgentInstanceExample example);

    TblAgentInstance selectByPrimaryKey(Integer ID);

    int updateByExampleSelective(@Param("record") TblAgentInstance record, @Param("example") TblAgentInstanceExample example);

    int updateByExample(@Param("record") TblAgentInstance record, @Param("example") TblAgentInstanceExample example);

    int updateByPrimaryKeySelective(TblAgentInstance record);

    int updateByPrimaryKey(TblAgentInstance record);
}
