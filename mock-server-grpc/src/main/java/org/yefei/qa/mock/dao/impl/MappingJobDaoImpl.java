package org.yefei.qa.mock.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.dao.IMappingJobDao;
import org.yefei.qa.mock.enums.ProtocolEnum;
import org.yefei.qa.mock.model.gen.dao.TblMappingJobMapper;
import org.yefei.qa.mock.model.gen.pojo.TblMappingJob;
import org.yefei.qa.mock.model.gen.pojo.TblMappingJobExample;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/9/27 20:52
 */
@Service
public class MappingJobDaoImpl implements IMappingJobDao {

    @Autowired
    private TblMappingJobMapper tblMappingJobMapper;

    @Override
    public List<TblMappingJob> queryGRPCMappingJobs(int requestID) {
        TblMappingJobExample example = new TblMappingJobExample();
        TblMappingJobExample.Criteria criteria = example.createCriteria();
        criteria.andRequestIDEqualTo(requestID).andProtocolEqualTo(ProtocolEnum.GRPC.getProtocol()).andIsActiveEqualTo(true);

        example.setOrderByClause("sortIndex asc");

        return tblMappingJobMapper.selectByExample(example);
    }
}
