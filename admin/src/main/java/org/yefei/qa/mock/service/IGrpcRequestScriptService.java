package org.yefei.qa.mock.service;

import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestScript;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:11
 */
public interface IGrpcRequestScriptService {
    List<TblGrpcRequestScript> queryGrpcScriptList(String serviceName, String methodName);

    long countGrpcScript(String serviceName, String methodName);

    boolean saveGrpcScript(TblGrpcRequestScript grpcRequestScript);

    boolean deleteGrpcScript(int scriptID);

    TblGrpcRequestScript getScript(int scriptID);
}
