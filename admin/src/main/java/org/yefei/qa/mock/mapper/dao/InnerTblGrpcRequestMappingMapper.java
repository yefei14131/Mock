package org.yefei.qa.mock.mapper.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestMapping;

import java.util.HashMap;
import java.util.List;

/**
 * @author yefei
 * @date: 2019/11/28
 */
public interface InnerTblGrpcRequestMappingMapper {

    int cloneMapping(HashMap params);

    @Delete("DELETE FROM tbl_grpc_request_mapping WHERE groupID NOT IN ( SELECT groupID FROM tbl_grpc_request_group )")
    int deleteUnRelationMapping();

    @Select("select DISTINCT serviceName, methodName from tbl_grpc_request_mapping  WHERE isActive = 1 ")
    List<TblGrpcRequestMapping> queryAllActiveMethod();
}
