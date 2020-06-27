package org.yefei.qa.mock.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.yefei.qa.mock.dao.IGrpcMappingDao;
import org.yefei.qa.mock.model.gen.dao.TblGrpcRequestMappingMapper;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestMapping;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestMappingExample;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/9/27 20:53
 */
@Repository
public class GrpcMappingDaoImpl implements IGrpcMappingDao {

    @Autowired
    private TblGrpcRequestMappingMapper tblGrpcRequestMappingMapper;

    @Override
    public List<TblGrpcRequestMapping> queryGrpcMappings(String serviceName, String methodName) {
        TblGrpcRequestMappingExample example = new TblGrpcRequestMappingExample();
        TblGrpcRequestMappingExample.Criteria criteria = example.createCriteria();
        criteria.andIsActiveEqualTo(true).andServiceNameEqualTo(serviceName).andMethodNameEqualTo(methodName);

        example.setOrderByClause("sortIndex desc");
        return tblGrpcRequestMappingMapper.selectByExampleWithBLOBs(example);
    }
}
