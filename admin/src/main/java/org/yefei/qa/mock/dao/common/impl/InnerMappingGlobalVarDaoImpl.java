package org.yefei.qa.mock.dao.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.dao.common.IInnerMappingGlobalVarDao;
import org.yefei.qa.mock.enums.ProtocolEnum;
import org.yefei.qa.mock.mapper.dao.InnerTblMappingGlobalVarMapper;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:08
 */
@Service
public class InnerMappingGlobalVarDaoImpl implements IInnerMappingGlobalVarDao {

    @Autowired
    private InnerTblMappingGlobalVarMapper innerTblMappingGlobalVarMapper;


    @Override
    public int cloneMappingGlobalVar(int sourceRequestID, String protocol, int destRequestID, List<BeanScanner.BeanField> fieldList) {

        return innerTblMappingGlobalVarMapper.cloneMappingGlobalVar(sourceRequestID, protocol, destRequestID, fieldList);
    }

    /**
     * 删除没有关联rest request的GlobalVar
     *
     * @return
     */
    @Override
    public int deleteUnRelationRestMappingGlobalVar() {
        return innerTblMappingGlobalVarMapper.deleteUnRelationRestMappingGlobalVar(ProtocolEnum.HTTP.getProtocol());
    }

    /**
     * 删除没有关联grpc request的GlobalVar
     *
     * @return
     */
    @Override
    public int deleteUnRelationGrpcMappingGlobalVar() {
        return innerTblMappingGlobalVarMapper.deleteUnRelationGrpcMappingGlobalVar(ProtocolEnum.GRPC.getProtocol());
    }

}
