package org.yefei.qa.mock.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.Configuration;
import org.yefei.qa.mock.contants.GuavaContants;
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
@Configuration
@Slf4j
public class MappingTaskCache extends BaseGuavaCache{

    private static final List<TblMappingTask> empty = new ArrayList<>();

    @Resource
    private IMappingTaskDao requestTaskDao;


    public void updateCacheTime(long duration) {
        super.updateCacheTime(duration, GuavaContants.EXPIRE_TIME_UNIT);
    }


    public MappingTaskCache(){
        super(GuavaContants.EXPIRE_DURATION, GuavaContants.EXPIRE_TIME_UNIT);
    }

    /**
     * 缓存数据加载方法
     *
     * @param requestID
     * @return
     * @author coshaho
     */
    @Override
    protected List<TblMappingTask> loadData(Object requestID) {
        return requestTaskDao.queryMappingTasks(Integer.parseInt(requestID.toString()));
    }

    public List<TblMappingTask> queryRestMappingRulesDetailsByRequestID(Integer requestID){
        try {
            return (List<TblMappingTask>) cache.get(requestID);
        } catch (ExecutionException e) {
            log.debug(ExceptionUtils.getStackTrace(e));
            return empty;
        }
    }

}
