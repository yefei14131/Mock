package org.yefei.qa.mock.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.dao.IMappingTaskDao;
import org.yefei.qa.mock.model.gen.pojo.TblMappingTask;

import javax.annotation.Resource;
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
public class MappingTaskCache extends TimeChangeAbledCache {

    private static final List<TblMappingTask> empty = new ArrayList<>();

    @Resource
    private IMappingTaskDao requestTaskDao;

    /**
     * 缓存数据加载方法
     *
     * @param jobID
     * @return
     * @author coshaho
     */
    @Override
    protected List<TblMappingTask> loadData(Object jobID) {
        return requestTaskDao.queryMappingTasks(Integer.parseInt(jobID.toString()));
    }

    public List<TblMappingTask> queryGrpcMappingRulesDetailsByRequestID(Integer requestID){
        try {
            return (List<TblMappingTask>) cache.get(requestID);
        } catch (ExecutionException e) {
            log.debug(ExceptionUtils.getStackTrace(e));
            return empty;
        }
    }

}
