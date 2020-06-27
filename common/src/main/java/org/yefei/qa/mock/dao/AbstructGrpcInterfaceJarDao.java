package org.yefei.qa.mock.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.yefei.qa.mock.model.gen.dao.TblGrpcInterfaceJarMapper;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcInterfaceJar;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcInterfaceJarExample;

import java.util.Date;
import java.util.List;

/**
 * @author yefei
 * @date: 2020/4/29
 */
public class AbstructGrpcInterfaceJarDao implements IGrpcProtocolDao {

    @Autowired
    private TblGrpcInterfaceJarMapper tblGrpcInterfaceJarMapper;

    public List<TblGrpcInterfaceJar> queryGrpcInterface(Date lessUpdateTime) {
        TblGrpcInterfaceJarExample example = new TblGrpcInterfaceJarExample();
        TblGrpcInterfaceJarExample.Criteria criteria = example.createCriteria();
        if (lessUpdateTime != null) {
            criteria.andUpdateTimeGreaterThanOrEqualTo(lessUpdateTime);
        }
        return tblGrpcInterfaceJarMapper.selectByExample(example);
    }

    public List<TblGrpcInterfaceJar> queryAllGrpcInterface() {
        TblGrpcInterfaceJarExample example = new TblGrpcInterfaceJarExample();
        TblGrpcInterfaceJarExample.Criteria criteria = example.createCriteria();

        return tblGrpcInterfaceJarMapper.selectByExample(example);
    }
}
