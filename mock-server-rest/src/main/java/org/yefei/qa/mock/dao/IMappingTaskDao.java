package org.yefei.qa.mock.dao;

import org.yefei.qa.mock.model.gen.pojo.TblMappingTask;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/9/27 20:47
 */
public interface IMappingTaskDao {
    List<TblMappingTask> queryMappingTasks(int jobID);
}
