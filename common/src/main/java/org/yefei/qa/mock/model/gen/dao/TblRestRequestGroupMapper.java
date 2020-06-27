package org.yefei.qa.mock.model.gen.dao;

import org.yefei.qa.mock.model.gen.pojo.TblRestRequestGroup;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TblRestRequestGroupMapper {
    long countByExample(TblRestRequestGroupExample example);

    int deleteByExample(TblRestRequestGroupExample example);

    int deleteByPrimaryKey(Integer groupID);

    int insert(TblRestRequestGroup record);

    int insertSelective(TblRestRequestGroup record);

    List<TblRestRequestGroup> selectByExample(TblRestRequestGroupExample example);

    TblRestRequestGroup selectByPrimaryKey(Integer groupID);

    int updateByExampleSelective(@Param("record") TblRestRequestGroup record, @Param("example") TblRestRequestGroupExample example);

    int updateByExample(@Param("record") TblRestRequestGroup record, @Param("example") TblRestRequestGroupExample example);

    int updateByPrimaryKeySelective(TblRestRequestGroup record);

    int updateByPrimaryKey(TblRestRequestGroup record);
}
