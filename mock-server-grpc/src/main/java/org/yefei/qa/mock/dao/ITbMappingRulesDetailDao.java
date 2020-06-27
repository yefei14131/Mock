package org.yefei.qa.mock.dao;

import org.yefei.qa.mock.model.gen.pojo.TblMappingRulesDetail;

import java.util.List;

/**
 * Created by yefei on 2018/8/7.
 */
public interface ITbMappingRulesDetailDao {

    List<TblMappingRulesDetail> queryGrpcMappingRulesDetailsByRequestID(Integer requestID);

}
