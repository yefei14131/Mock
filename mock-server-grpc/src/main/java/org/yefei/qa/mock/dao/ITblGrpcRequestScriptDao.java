package org.yefei.qa.mock.dao;

import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestScript;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/8/20 19:34
 */
public interface ITblGrpcRequestScriptDao {

    List<TblGrpcRequestScript> queryScripts(String serviceName, String methodName);

}
