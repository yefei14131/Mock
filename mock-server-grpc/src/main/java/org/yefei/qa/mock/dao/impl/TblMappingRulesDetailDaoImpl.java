package org.yefei.qa.mock.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.dao.ITbMappingRulesDetailDao;
import org.yefei.qa.mock.enums.ProtocolEnum;
import org.yefei.qa.mock.model.gen.dao.TblMappingRulesDetailMapper;
import org.yefei.qa.mock.model.gen.pojo.TblMappingRulesDetail;
import org.yefei.qa.mock.model.gen.pojo.TblMappingRulesDetailExample;

import java.util.List;

/**
 * Created by yefei on 2018/8/7.
 */
@Service
public class TblMappingRulesDetailDaoImpl implements ITbMappingRulesDetailDao {

    @Autowired
    private TblMappingRulesDetailMapper tblMappingRulesDetailMapper;


    @Override
    public List<TblMappingRulesDetail> queryGrpcMappingRulesDetailsByRequestID(Integer requestID){

        TblMappingRulesDetailExample example = new TblMappingRulesDetailExample();
        TblMappingRulesDetailExample.Criteria criteria = example.createCriteria();
        criteria.andRequestIDEqualTo(requestID).andProtocolEqualTo(ProtocolEnum.GRPC.getProtocol());

        example.setOrderByClause("sortIndex desc, rulesDetailID asc");

        return tblMappingRulesDetailMapper.selectByExample(example);

    }

}
