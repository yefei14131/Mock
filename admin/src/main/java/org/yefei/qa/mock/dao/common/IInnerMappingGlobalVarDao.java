package org.yefei.qa.mock.dao.common;

import org.yefei.qa.mock.config.BeanScanner;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:08
 */
public interface IInnerMappingGlobalVarDao {


    int cloneMappingGlobalVar(int sourceRequestID, String protocol, int destRequestID, List<BeanScanner.BeanField> fieldList);

    /**
     * 删除没有关联rest request的GlobalVar
     *
     * @return
     */
    int deleteUnRelationRestMappingGlobalVar();

    /**
     * 删除没有关联grpc request的GlobalVar
     *
     * @return
     */
    int deleteUnRelationGrpcMappingGlobalVar();

}
