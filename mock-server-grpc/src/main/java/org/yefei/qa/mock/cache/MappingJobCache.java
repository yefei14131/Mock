package org.yefei.qa.mock.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.dao.IMappingJobDao;
import org.yefei.qa.mock.model.gen.pojo.TblMappingJob;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author: yefei
 * @date: 2018/8/31 01:27
 */
@Slf4j
@Component
@DependsOn("guavaCacheSubject")
public class MappingJobCache extends TimeChangeAbledCache {

    private static final List<TblMappingJob> empty = new ArrayList<>();

    @Resource
    private IMappingJobDao requestJobDao;

    /**
     * 缓存数据加载方法
     *
     * @param requestID
     * @return
     * @author coshaho
     */
    @Override
    protected List<TblMappingJob> loadData(Object requestID) {
        return requestJobDao.queryGRPCMappingJobs(Integer.parseInt(requestID.toString()));
    }

    public List<TblMappingJob> queryTblMappingJobsByRequestID(Integer requestID){
        try {
            return (List<TblMappingJob>) cache.get(requestID);
        } catch (ExecutionException e) {
            log.debug(ExceptionUtils.getStackTrace(e));
            return empty;
        }
    }

}
