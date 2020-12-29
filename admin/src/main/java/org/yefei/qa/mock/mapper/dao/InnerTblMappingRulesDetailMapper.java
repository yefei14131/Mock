package org.yefei.qa.mock.mapper.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.yefei.qa.mock.config.BeanScanner;

import java.util.List;

/**
 * @author yefei
 * @date: 2019/11/26
 */
public interface InnerTblMappingRulesDetailMapper {

    int cloneMappingRules(@Param("sourceRequestID") int sourceRequestID, @Param("protocol") String protocol, @Param("destRequestID") int destRequestID, @Param("fieldList") List<BeanScanner.BeanField> fieldList);

    @Delete("DELETE FROM tbl_mapping_rules_detail WHERE requestID NOT IN ( SELECT requestID FROM tbl_rest_request_mapping) AND protocol = #{protocol}")
    int deleteUnRelationRestMappingRulesDetail(String protocol);

    @Delete("DELETE FROM tbl_mapping_rules_detail WHERE requestID NOT IN ( SELECT requestID FROM tbl_grpc_request_mapping) AND protocol = #{protocol}")
    int deleteUnRelationGrpcMappingRulesDetail(String protocol);
}
