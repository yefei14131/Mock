package org.yefei.qa.mock.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.dao.IMappingGlobalVarDao;
import org.yefei.qa.mock.model.gen.dao.TblMappingGlobalVarMapper;
import org.yefei.qa.mock.model.gen.pojo.TblMappingGlobalVar;
import org.yefei.qa.mock.model.gen.pojo.TblMappingGlobalVarExample;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:08
 */
@Service
public class MappingGlobalVarDaoImpl implements IMappingGlobalVarDao {

    @Autowired
    private TblMappingGlobalVarMapper tblMappingGlobalVarMapper;

    @Override
    public int insertGlobalVar(TblMappingGlobalVar tblMappingGlobalVar) {
        return tblMappingGlobalVarMapper.insertSelective(tblMappingGlobalVar);
    }

    @Override
    public void updateGlobalVar(TblMappingGlobalVar tblMappingGlobalVar) {
        tblMappingGlobalVarMapper.updateByPrimaryKeySelective(tblMappingGlobalVar);
    }

    @Override
    public int deleteGlobalVar(int globalVarID) {
        return tblMappingGlobalVarMapper.deleteByPrimaryKey(globalVarID);
    }

    @Override
    public List<TblMappingGlobalVar> queryGlobalVarList(int requestID, String protocol) {
        TblMappingGlobalVarExample example = new TblMappingGlobalVarExample();
        example.createCriteria().andRequestIDEqualTo(requestID).andProtocolEqualTo(protocol);

        return tblMappingGlobalVarMapper.selectByExample(example);
    }
}
