package org.yefei.qa.mock.model.gen.dao;

import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMapping;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMappingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TblRestRequestMappingMapper {
    long countByExample(TblRestRequestMappingExample example);

    int deleteByExample(TblRestRequestMappingExample example);

    int deleteByPrimaryKey(Integer requestID);

    int insert(TblRestRequestMapping record);

    int insertSelective(TblRestRequestMapping record);

    List<TblRestRequestMapping> selectByExampleWithBLOBs(TblRestRequestMappingExample example);

    List<TblRestRequestMapping> selectByExample(TblRestRequestMappingExample example);

    TblRestRequestMapping selectByPrimaryKey(Integer requestID);

    int updateByExampleSelective(@Param("record") TblRestRequestMapping record, @Param("example") TblRestRequestMappingExample example);

    int updateByExampleWithBLOBs(@Param("record") TblRestRequestMapping record, @Param("example") TblRestRequestMappingExample example);

    int updateByExample(@Param("record") TblRestRequestMapping record, @Param("example") TblRestRequestMappingExample example);

    int updateByPrimaryKeySelective(TblRestRequestMapping record);

    int updateByPrimaryKeyWithBLOBs(TblRestRequestMapping record);

    int updateByPrimaryKey(TblRestRequestMapping record);
}
