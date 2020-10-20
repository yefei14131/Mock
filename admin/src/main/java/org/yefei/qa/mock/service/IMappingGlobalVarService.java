package org.yefei.qa.mock.service;

import org.yefei.qa.mock.model.gen.pojo.TblMappingGlobalVar;
import org.yefei.qa.mock.model.gen.pojo.TblMappingRulesDetail;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:08
 */
public interface IMappingGlobalVarService {

    int insertGlobalVar(TblMappingGlobalVar tblMappingGlobalVar);

    boolean saveGlobalVar(TblMappingGlobalVar tblMappingGlobalVar);

    boolean deleteGlobalVar(int globalVarID);

    List<TblMappingGlobalVar> queryGlobalVarList(int requestID, String protocol);
}
