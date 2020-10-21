package org.yefei.qa.mock.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.dao.ITblMappingRulesDetailDao;
import org.yefei.qa.mock.model.gen.pojo.TblMappingRulesDetail;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author: yefei
 * @date: 2018/8/31 01:27
 */
@Component
@Slf4j
@DependsOn("guavaCacheSubject")
public class MappingRulesDetailCache extends TimeChangeAbledCache {

    private static final List<TblMappingRulesDetail> empty = new ArrayList<>();

    @Autowired
    private ITblMappingRulesDetailDao tblMappingRulesDetailDao;

    /**
     * 缓存数据加载方法
     *
     * @param requestID
     * @return
     * @author coshaho
     */
    @Override
    protected List<TblMappingRulesDetail> loadData(Object requestID) {
        return tblMappingRulesDetailDao.queryRestMappingRulesDetailsByRequestID(Integer.valueOf(requestID.toString()));
    }

    public List<TblMappingRulesDetail> queryRestMappingRulesDetailsByRequestID(Integer requestID){
        try {
            return (List<TblMappingRulesDetail>) cache.get(requestID);
        } catch (ExecutionException e) {
            log.debug(ExceptionUtils.getStackTrace(e));
            return empty;
        }
    }
//
//
//    public List<TblMappingRulesDetail> queryGrpcMappingRulesDetailsByRequestID(Integer requestID){
//        try {
//            String requestTarget = ProtocolConstants.GRPC + String.valueOf(requestID);
//            return (List<TblMappingRulesDetail>) cache.get(requestTarget);
//
//        } catch (ExecutionException e) {
//            log.debug(ExceptionUtils.getStackTrace(e));
//            return empty;
//        }
//    }
}
