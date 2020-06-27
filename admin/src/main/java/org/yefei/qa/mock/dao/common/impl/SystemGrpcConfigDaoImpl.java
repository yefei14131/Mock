package org.yefei.qa.mock.dao.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.dao.common.ISystemGrpcConfigDao;
import org.yefei.qa.mock.mapper.dao.InnerTblGrpcInterfaceJarMapper;
import org.yefei.qa.mock.mapper.pojo.InnerTblGrpcInterfaceJarExample;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcInterfaceJar;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:16
 */
@Service
public class SystemGrpcConfigDaoImpl implements ISystemGrpcConfigDao {

    @Autowired
    private InnerTblGrpcInterfaceJarMapper grpcInterfaceJarMapper;

    @Override
    public List<TblGrpcInterfaceJar> queryGrpcInterfaceList(int page, int pageSize) {
        page = page < 1 ? 10 : page;
        int offset = page < 0 ? 0 : pageSize * (page - 1);

        InnerTblGrpcInterfaceJarExample example = new InnerTblGrpcInterfaceJarExample();
        example.setLimit(pageSize);
        example.setOffset(offset);

        return grpcInterfaceJarMapper.selectByExample(example);
    }

    @Override
    public long countGrpcInterfaceList() {
        InnerTblGrpcInterfaceJarExample example = new InnerTblGrpcInterfaceJarExample();
        return grpcInterfaceJarMapper.countByExample(example);
    }

    @Override
    public TblGrpcInterfaceJar get(int ID) {
        return grpcInterfaceJarMapper.selectByPrimaryKey(ID);
    }

    @Override
    public int insert(TblGrpcInterfaceJar interfaceJar) {
        return grpcInterfaceJarMapper.insert(interfaceJar);
    }

    @Override
    public int update(TblGrpcInterfaceJar interfaceJar) {
        return grpcInterfaceJarMapper.updateByPrimaryKey(interfaceJar);
    }

    @Override
    public int delete(int ID) {
        return grpcInterfaceJarMapper.deleteByPrimaryKey(ID);
    }
}
