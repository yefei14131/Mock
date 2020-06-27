package org.yefei.qa.mock.dao.common;

import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.model.gen.pojo.TblMappingJob;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:15
 */
public interface IMappingJobDao {
    int insertMappingJob(TblMappingJob tblMappingJob);

    int deleteMappingJob(int jobID);

    List<TblMappingJob> queryTblMappingJobList(int requestID, String protocol);

    long countMappingJob(int requestID, String protocol);

    int updateMappingJob(TblMappingJob tblMappingJob);

    /**
     * clone mapping job
     * @param sourceRequestID
     * @param protocol
     * @param destRequestID
     * @param fieldList
     * @return new jobID
     */
    int cloneJob(int sourceRequestID, String protocol, int destRequestID, List<BeanScanner.BeanField> fieldList);

    int cloneJob(int sourceJobID, int destRequestID, List<BeanScanner.BeanField> fieldList);

    int deleteUnRelationRestMappingJob();

    int deleteUnRelationGrpcMappingJob();


}
