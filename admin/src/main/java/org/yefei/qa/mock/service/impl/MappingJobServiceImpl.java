package org.yefei.qa.mock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.dao.common.IMappingJobDao;
import org.yefei.qa.mock.dao.common.IMappingTaskDao;
import org.yefei.qa.mock.model.gen.pojo.TblMappingJob;
import org.yefei.qa.mock.service.IMappingJobService;

import java.util.Date;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:16
 */
@Service
public class MappingJobServiceImpl implements IMappingJobService {

    @Autowired
    private IMappingJobDao mappingJobDao;

    @Autowired
    private IMappingTaskDao mappingTaskDao;

    @Override
    public List<TblMappingJob> queryMappingJobList(int requestID, String protocol){
        return mappingJobDao.queryTblMappingJobList(requestID, protocol);
    }

    @Override
    public long countMappingJob(int requestID, String protocol){
        return mappingJobDao.countMappingJob(requestID, protocol);
    }

    @Override
    public int saveMappingJob(TblMappingJob requestJob) {
        requestJob.setUpdateTime(new Date());
        if (requestJob.getJobID() != null && requestJob.getJobID() > 0){
            return mappingJobDao.updateMappingJob(requestJob);
        }else{
            if ( mappingJobDao.insertMappingJob(requestJob) == 1){
                return requestJob.getJobID();
            }else {
                return 0;
            }
        }
    }

    @Override
    public boolean deleteMappingJob(int jobID){

        if (mappingJobDao.deleteMappingJob(jobID) > 0 ) {
            mappingTaskDao.deleteUnRelationMappingTask();
        }
        return true;
    }
}
