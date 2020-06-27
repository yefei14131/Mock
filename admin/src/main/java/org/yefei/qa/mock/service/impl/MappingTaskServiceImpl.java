package org.yefei.qa.mock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.dao.common.IMappingTaskDao;
import org.yefei.qa.mock.model.gen.pojo.TblMappingTask;
import org.yefei.qa.mock.service.IMappingTaskService;

import java.util.Date;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:16
 */
@Service
public class MappingTaskServiceImpl implements IMappingTaskService {

    @Autowired
    private IMappingTaskDao mappingTaskDao;

    @Override
    public List<TblMappingTask> queryMappingTaskList(int jobID){
        return mappingTaskDao.queryMappingTaskList(jobID);
    }

    @Override
    public long countMappingTask(int jobID){
        return mappingTaskDao.countMappingTask(jobID);
    }

    @Override
    public int saveMappingTask(TblMappingTask requestTask) {
        requestTask.setUpdateTime(new Date());
        if (requestTask.getTaskID() != null && requestTask.getTaskID() > 0){
            return mappingTaskDao.updateMappingTask(requestTask);
        }else{
            mappingTaskDao.insertMappingTask(requestTask);
            return requestTask.getTaskID();
        }
    }

    @Override
    public boolean deleteMappingTask(int taskID){
        return mappingTaskDao.deleteMappingTask(taskID) > 0 ? true : false;
    }
}
