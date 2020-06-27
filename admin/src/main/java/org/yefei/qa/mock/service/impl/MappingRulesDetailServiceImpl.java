package org.yefei.qa.mock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.dao.common.IMappingRulesDetailDao;
import org.yefei.qa.mock.model.gen.pojo.TblMappingRulesDetail;
import org.yefei.qa.mock.service.IMappingRulesDetailService;

import java.util.Date;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:08
 */
@Service
public class MappingRulesDetailServiceImpl implements IMappingRulesDetailService {

    @Autowired
    private IMappingRulesDetailDao rulesDetailDao;

    @Override
    public List<TblMappingRulesDetail> queryRulesDetailsList(int requestID, String protocol){
        return rulesDetailDao.queryMappingRulesDetail(requestID, protocol);
    }

    @Override
    public long countMappingRulesDetail(int requestID, String protocol){
        return rulesDetailDao.countMappingRulesDetail(requestID, protocol);
    }



    @Override
    public boolean saveRulesDetails(TblMappingRulesDetail rulesDetail) {
        rulesDetail.setUpdateTime(new Date());
        if (rulesDetail.getRulesDetailID() != null && rulesDetail.getRulesDetailID() > 0){
            return rulesDetailDao.updateMappingRulesDetail(rulesDetail) > 0 ? true : false;
        }else{
            return rulesDetailDao.insertMappingRulesDetail(rulesDetail) > 0 ? true : false;
        }
    }

    @Override
    public boolean deleteRulesDetails(int rulesDetailID){
        return rulesDetailDao.deleteMappingRulesDetail(rulesDetailID) > 0 ? true : false;
    }
}
