package org.yefei.qa.mock.dao.grpc;

import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestScript;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:11
 */
public interface IGrpcRequestScriptDao {
    int insertGrpcScript(TblGrpcRequestScript tblGrpcRequestScript);

    int deleteGrpcScript(int scriptID);

    int updateGrpcScript(TblGrpcRequestScript tblGrpcRequestScript);

    List<TblGrpcRequestScript> queryScriptList(String serviceName, String methodName);

    List<TblGrpcRequestScript> queryScriptList(int groupID);

    long countGrpcScript(String serviceName, String methodName);

    int updateServiceAndMethodName(String sourceServiceName, String sourceMethod, String destServiceName, String destMethodName);

    TblGrpcRequestScript getScript(int scriptID);

    int clone(String sourceServiceName, String sourceMethod, String destServiceName, String destMethodName, List<BeanScanner.BeanField> mappingScriptFields);

    int deleteUnRelationMappingScript();
}
