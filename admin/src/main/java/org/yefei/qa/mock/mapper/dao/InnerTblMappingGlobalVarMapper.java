package org.yefei.qa.mock.mapper.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.yefei.qa.mock.config.BeanScanner;

import java.util.List;

public interface InnerTblMappingGlobalVarMapper {

    int cloneMappingGlobalVar(@Param("sourceRequestID") int sourceRequestID, @Param("protocol") String protocol, @Param("destRequestID") int destRequestID, @Param("fieldList") List<BeanScanner.BeanField> fieldList);

    @Delete("DELETE FROM tbl_mapping_global_var WHERE requestID NOT IN ( SELECT requestID FROM tbl_rest_request_mapping) AND protocol = #{protocol}")
    int deleteUnRelationRestMappingGlobalVar(String protocol);

    @Delete("DELETE FROM tbl_mapping_global_var WHERE requestID NOT IN ( SELECT requestID FROM tbl_grpc_request_mapping) AND protocol = #{protocol}")
    int deleteUnRelationGrpcMappingGlobalVar(String protocol);

}