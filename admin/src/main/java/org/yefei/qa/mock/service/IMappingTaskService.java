package org.yefei.qa.mock.service;

import org.yefei.qa.mock.model.gen.pojo.TblMappingTask;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:16
 */
public interface IMappingTaskService {

    List<TblMappingTask> queryMappingTaskList(int jobID);

    long countMappingTask(int jobID);

    int saveMappingTask(TblMappingTask requestTask);

    boolean deleteMappingTask(int taskID);
}
