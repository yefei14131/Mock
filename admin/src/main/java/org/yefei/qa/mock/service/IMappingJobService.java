package org.yefei.qa.mock.service;

import org.yefei.qa.mock.model.gen.pojo.TblMappingJob;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:15
 */
public interface IMappingJobService {
    List<TblMappingJob> queryMappingJobList(int requestID, String protocol);

    long countMappingJob(int requestID, String protocol);

    int saveMappingJob(TblMappingJob requestJob);

    boolean deleteMappingJob(int jobID);
}
