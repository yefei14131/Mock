package org.yefei.qa.mock.dao.common;

import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.model.gen.pojo.TblMappingRulesDetail;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:08
 */
public interface IMappingRulesDetailDao {
    int insertMappingRulesDetail(TblMappingRulesDetail tblMappingRulesDetail);

    int deleteMappingRulesDetail(int rulesDetailID);

    List<TblMappingRulesDetail> queryMappingRulesDetail(int requestID, String protocol);

    long countMappingRulesDetail(int requestID, String protocol);

    int updateMappingRulesDetail(TblMappingRulesDetail tblMappingRulesDetail);

    int cloneMappingRules(int sourceRequestID, String protocol, int destRequestID, List<BeanScanner.BeanField> fieldList);

    int deleteUnRelationRestMappingRulesDetail();

    int deleteUnRelationGrpcMappingRulesDetail();

}
