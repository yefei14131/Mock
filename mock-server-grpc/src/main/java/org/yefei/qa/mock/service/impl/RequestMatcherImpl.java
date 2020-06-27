package org.yefei.qa.mock.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.cache.GlobalDataPool;
import org.yefei.qa.mock.cache.GrpcRequestMappingCache;
import org.yefei.qa.mock.cache.MappingRulesDetailCache;
import org.yefei.qa.mock.model.bean.HitCondition;
import org.yefei.qa.mock.model.bean.RecordedRequest;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestMapping;
import org.yefei.qa.mock.model.gen.pojo.TblMappingRulesDetail;
import org.yefei.qa.mock.service.IRequestMacther;
import org.yefei.qa.mock.utils.HitConditionBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yefei on 2018/8/2.
 */

@Service("requestMatcher")
@Slf4j
public class RequestMatcherImpl  implements IRequestMacther {

    @Autowired
    private GrpcRequestMappingCache grpcRequestMappingCache;

    @Autowired
    private MappingRulesDetailCache mappingRulesDetailCache;

    @Autowired
    private GlobalDataPool globalDataPool;

    private HitCondition buildHitCondition(Integer requestID){

        List<TblMappingRulesDetail> tblMappingRulesDetails = mappingRulesDetailCache.queryGrpcMappingRulesDetailsByRequestID(requestID);

        return HitConditionBuilder.buildHitCondition(tblMappingRulesDetails);
    }


    @Override
    public List<RecordedRequest> loadRecordedRequestList(String fullMethodName) {

        List<TblGrpcRequestMapping> tblRestRequestMappings = grpcRequestMappingCache.queryGrpcMappings(fullMethodName);

        List<RecordedRequest> recordedRequests = new ArrayList<>();

        for (TblGrpcRequestMapping tblGrpcRequestMapping : tblRestRequestMappings){

            RecordedRequest recordedRequest = new RecordedRequest();
            recordedRequest.setTblGrpcRequestMapping(tblGrpcRequestMapping);

            recordedRequest.setHitCondition(buildHitCondition(tblGrpcRequestMapping.getRequestID()));
            recordedRequests.add(recordedRequest);
        }


        return recordedRequests;
    }

    @Override
    public RecordedRequest matchRecordedRequest(List<RecordedRequest> recordedRequests, HashMap userDefined, HashMap params) {
        for (RecordedRequest recordedRequest: recordedRequests){
            if (recordedRequest.isMatch(userDefined, params, globalDataPool.getAll())) {
                return recordedRequest;
            }
        }

        return null;
    }
}
