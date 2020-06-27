package org.yefei.qa.mock.mapper.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.yefei.qa.mock.config.BeanScanner;

import java.util.List;

/**
 * @author yefei
 * @date: 2019/11/26
 */
public interface InnerTblMappingTaskMapper {
    int cloneTask(@Param("sourceJobID") int sourceJobID, @Param("destJobID") int destJobID, @Param("fieldList") List<BeanScanner.BeanField> fieldList);

    @Delete("DELETE FROM tbl_mapping_task WHERE jobID NOT IN ( SELECT jobID FROM tbl_mapping_job)")
    int deleteUnRelationMappingTask();
}
