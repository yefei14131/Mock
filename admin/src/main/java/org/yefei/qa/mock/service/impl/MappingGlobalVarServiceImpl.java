package org.yefei.qa.mock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.dao.common.IMappingGlobalVarDao;
import org.yefei.qa.mock.model.gen.pojo.TblMappingGlobalVar;
import org.yefei.qa.mock.service.IMappingGlobalVarService;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:08
 */
@Service
public class MappingGlobalVarServiceImpl implements IMappingGlobalVarService {

    @Autowired
    private IMappingGlobalVarDao mappingGlobalVarDao;

    @Override
    public int insertGlobalVar(TblMappingGlobalVar tblMappingGlobalVar) {
        return mappingGlobalVarDao.insertGlobalVar(tblMappingGlobalVar);
    }

    @Override
    public boolean saveGlobalVar(TblMappingGlobalVar tblMappingGlobalVar) {
        if (tblMappingGlobalVar.getGlobalVarID() == null || tblMappingGlobalVar.getGlobalVarID() == 0) {
            mappingGlobalVarDao.insertGlobalVar(tblMappingGlobalVar);
        } else {
            mappingGlobalVarDao.updateGlobalVar(tblMappingGlobalVar);
        }
        return true;
    }

    @Override
    public boolean deleteGlobalVar(int globalVarID) {
        return mappingGlobalVarDao.deleteGlobalVar(globalVarID) > 0 ? true : false;
    }

    @Override
    public List<TblMappingGlobalVar> queryGlobalVarList(int requestID, String protocol) {
        return mappingGlobalVarDao.queryGlobalVar(requestID, protocol);
    }

}
