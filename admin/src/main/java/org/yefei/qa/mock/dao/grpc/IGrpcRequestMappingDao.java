package org.yefei.qa.mock.dao.grpc;

import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestMapping;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestMappingExample;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:06
 */
public interface IGrpcRequestMappingDao {
    int insertGrpcRequestMapping(TblGrpcRequestMapping tblGrpcRequestMapping);

    int deleteGrpcRequestMaster(int requestID);

    int updateGrpcRequestMapping(TblGrpcRequestMapping tblGrpcRequestMapping);

    List<TblGrpcRequestMapping> queryGrpcRequestMappingList(int groupID);

    long countGrpcRequestMapping(int groupID);

//    int updateGroupCode(int groupID, String groupCode);

    TblGrpcRequestMapping getMapping(int requestID);

    /**
     * clone grpc mapping
     *
     * @param sourceRequestID
     * @param fieldList
     * @return new requestID
     */
    int cloneGrpcMapping(int sourceRequestID, List<BeanScanner.BeanField> fieldList);

    int countMapping(TblGrpcRequestMappingExample example);

    int deleteUnRelationMapping();

    List<TblGrpcRequestMapping> queryAllActiveMethod();
}
