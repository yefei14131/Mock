package org.yefei.qa.mock.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yefei.qa.mock.dao.ITblGrpcRequestScriptDao;
import org.yefei.qa.mock.model.gen.dao.TblGrpcRequestScriptMapper;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestScript;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestScriptExample;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/8/20 19:35
 */
@Repository
@Slf4j
public class TblGrpcRequestScriptDaoImpl implements ITblGrpcRequestScriptDao {

    @Autowired
    private TblGrpcRequestScriptMapper tblGrpcRequestScriptMapper;

    @Override
    public List<TblGrpcRequestScript> queryScripts(String serviceName, String methodName){

        TblGrpcRequestScriptExample example = new TblGrpcRequestScriptExample();
        TblGrpcRequestScriptExample.Criteria criteria = example.createCriteria();
        criteria.andServiceNameEqualTo(serviceName).andMethodNameEqualTo(methodName).andIsActiveEqualTo(true);

        example.setOrderByClause("sortIndex desc");

        return tblGrpcRequestScriptMapper.selectByExampleWithBLOBs(example);

    }


}
