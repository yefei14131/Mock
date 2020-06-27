package org.yefei.qa.mock.service;

import org.yefei.qa.mock.model.gen.pojo.TblMappingRulesDetail;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:08
 */
public interface IMappingRulesDetailService {

    List<TblMappingRulesDetail> queryRulesDetailsList(int requestID, String protocol);

    long countMappingRulesDetail(int requestID, String protocol);

    boolean saveRulesDetails(TblMappingRulesDetail rulesDetail);

    boolean deleteRulesDetails(int rulesDetailID);
}
