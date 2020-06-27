package org.yefei.qa.mock.dao;

import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestMapping;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/9/27 20:47
 */
public interface IGrpcMappingDao {

    List<TblGrpcRequestMapping> queryGrpcMappings(String serviceName, String methodName);

}
