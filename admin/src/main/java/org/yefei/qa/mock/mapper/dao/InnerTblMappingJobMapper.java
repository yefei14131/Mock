package org.yefei.qa.mock.mapper.dao;

import org.apache.ibatis.annotations.Delete;

import java.util.HashMap;

/**
 * @author yefei
 * @date: 2019/11/26
 */
public interface InnerTblMappingJobMapper {

    int cloneJob(HashMap params);

    @Delete("DELETE FROM tbl_mapping_job WHERE requestID NOT IN ( SELECT requestID FROM tbl_rest_request_mapping) AND protocol = #{protocol}")
    int deleteUnRelationRestMappingJob(String protocol);

    @Delete("DELETE FROM tbl_mapping_job WHERE requestID NOT IN ( SELECT requestID FROM tbl_grpc_request_mapping) AND protocol = #{protocol}")
    int deleteUnRelationGrpcMappingJob(String protocol);
}
