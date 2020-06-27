package org.yefei.qa.mock.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.Configuration;
import org.yefei.qa.mock.contants.GuavaContants;
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
@Configuration
public class MappingJobCache extends BaseGuavaCache {

    private static final List<TblMappingJob> empty = new ArrayList<>();

    @Resource
    private IMappingJobDao requestJobDao;

    public MappingJobCache(){
        super(GuavaContants.EXPIRE_DURATION, GuavaContants.EXPIRE_TIME_UNIT);
    }

    public void updateCacheTime(long duration) {
        super.updateCacheTime(duration, GuavaContants.EXPIRE_TIME_UNIT);
    }

    /**
     * 缓存数据加载方法
     *
     * @param requestID
     * @return
     * @author coshaho
     */
    @Override
    protected List<TblMappingJob> loadData(Object requestID) {
        return requestJobDao.queryRestMappingJobs(Integer.parseInt(requestID.toString()));
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
