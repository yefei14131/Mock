package org.yefei.qa.mock.dao.common;

import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.model.gen.pojo.TblMappingTask;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:16
 */
public interface IMappingTaskDao {
    int insertMappingTask(TblMappingTask tblMappingTask);

    int deleteMappingTask(int taskID);

    int updateMappingTask(TblMappingTask tblMappingTask);

    List<TblMappingTask> queryMappingTaskList(int jobID);

    long countMappingTask(int jobID);

    int cloneTask(int sourceJobID, int destJobID, List<BeanScanner.BeanField> fieldList);

    int deleteUnRelationMappingTask();
}
