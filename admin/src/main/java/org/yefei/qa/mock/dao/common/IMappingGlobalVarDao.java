package org.yefei.qa.mock.dao.common;

import org.yefei.qa.mock.model.gen.pojo.TblMappingGlobalVar;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:08
 */
public interface IMappingGlobalVarDao {

    int insertGlobalVar(TblMappingGlobalVar tblMappingGlobalVar);

    void updateGlobalVar(TblMappingGlobalVar tblMappingGlobalVar);

    int deleteGlobalVar(int globalVarID);

    List<TblMappingGlobalVar> queryGlobalVar(int requestID, String protocol);
}
