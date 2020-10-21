package org.yefei.qa.mock.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.contants.GuavaContants;
import org.yefei.qa.mock.dao.ISystemConfigDao;
import org.yefei.qa.mock.enums.SystemConfigTypeEnum;
import org.yefei.qa.mock.model.bean.config.ServerCacheConfigBean;
import org.yefei.qa.mock.model.gen.pojo.TblSystemConfig;

import javax.annotation.PostConstruct;
import java.util.LinkedList;

/**
 * @author yefei
 * @date: 2020/10/21
 */
@Component
@Slf4j
public class GuavaCacheSubject {

    private LinkedList<BaseGuavaCache> observers = new LinkedList<>();

    @Autowired
    private ISystemConfigDao systemConfigDao;

    private volatile boolean inited = false;
    private volatile long duration = 0;

    @PostConstruct
    protected void init() {
        inited = true;
        refreshCacheTime();

    }

    public void refreshCacheTime() {
        long durationTime = queryCacheTimeDuration();
        refreshCacheTime(durationTime);
    }

    private void refreshCacheTime(long duration) {
        try {
            if (duration < 0) {
                return;
            }
            this.duration = duration;
            observers.forEach(cache -> {
                refreshItem(cache, duration);
            });
            log.info("更新缓存时间成功，缓存时间为: {} 秒", duration);
        } catch (Exception e) {
            log.info("更新缓存时间失败", e);
        }
    }

    private void refreshItem(BaseGuavaCache cache, long duration) {
        cache.updateCacheTime(duration, GuavaContants.EXPIRE_TIME_UNIT);
        log.info("更新缓存时间成功，缓存时间为: {} 秒, {}", duration, cache.getClass().getCanonicalName());
    }

    public void addObserver(BaseGuavaCache cache) {
        observers.add(cache);
        if (inited) {
            refreshItem(cache, duration);
        }
    }

    private long queryCacheTimeDuration() {
        try {
            TblSystemConfig tblSystemConfig = systemConfigDao.queryTblSystemConfig(SystemConfigTypeEnum.SERVER_CACHE.getTypeID());
            if (tblSystemConfig != null && StringUtils.isNotEmpty(tblSystemConfig.getConfigData())) {
                ServerCacheConfigBean serverCacheConfigBean = new ObjectMapper().readValue(tblSystemConfig.getConfigData(), ServerCacheConfigBean.class);
                return serverCacheConfigBean.getCacheTimeSeconds();
            }
        } catch (Exception e) {
            log.error("查询缓存时间出错", e);
        }

        return -1;
    }
}
