package org.yefei.qa.mock.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.cache.GlobalDataPool;
import org.yefei.qa.mock.cache.MappingRulesDetailCache;
import org.yefei.qa.mock.cache.RestRequestMappingCache;
import org.yefei.qa.mock.debugger.SystemDebugger;
import org.yefei.qa.mock.model.bean.HitCondition;
import org.yefei.qa.mock.model.bean.RecordedRequest;
import org.yefei.qa.mock.model.gen.pojo.TblMappingRulesDetail;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMapping;
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
    private RestRequestMappingCache restRequestMappingCache;

    @Autowired
    private MappingRulesDetailCache mappingRulesDetailCache;

    @Autowired
    private GlobalDataPool globalDataPool;

    @Autowired
    private SystemDebugger systemDebugger;

    private HitCondition buildHitCondition(Integer requestID){
        List<TblMappingRulesDetail> tblMappingRulesDetails = mappingRulesDetailCache.queryRestMappingRulesDetailsByRequestID(requestID);

        return HitConditionBuilder.buildHitCondition(tblMappingRulesDetails, systemDebugger);
    }


    @Override
    public List<RecordedRequest> loadRecordedRequestList(String groupCode, String path) {
        // TODO 缓存命中规则
        List<TblRestRequestMapping> tblRestRequestMappings = restRequestMappingCache.queryTblRestRequestMapping(groupCode, path);

        List<RecordedRequest> recordedRequests = new ArrayList<>();

        for (TblRestRequestMapping tblRestRequestMapping : tblRestRequestMappings){

            RecordedRequest recordedRequest = new RecordedRequest();
            recordedRequest.setTblRestRequestMapping(tblRestRequestMapping);

            recordedRequest.setHitCondition(buildHitCondition(tblRestRequestMapping.getRequestID()));
            recordedRequests.add(recordedRequest);
        }


        return recordedRequests;
    }

    @Override
    public RecordedRequest matchRecordedRequest(List<RecordedRequest> recordedRequests, HashMap userDefined, HashMap params, HashMap<String, String> headers, HashMap<String, String> cookies) {
        for (RecordedRequest recordedRequest: recordedRequests){
            try {
                if (recordedRequest.isMatch(userDefined, params, headers, cookies, globalDataPool.getAll())) {
                    return recordedRequest;
                }
            } catch (Exception e) {
                systemDebugger.addSystemLog(
                        "执行匹配规则出错，requestId: " + recordedRequest.getTblRestRequestMapping().getRequestID(),
                        ExceptionUtils.getStackTrace(e));
                throw e;
            }
        }

        return null;
    }
}
