package org.yefei.qa.mock.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.cache.*;
import org.yefei.qa.mock.contants.GuavaContants;
import org.yefei.qa.mock.dao.ISystemConfigDao;
import org.yefei.qa.mock.enums.SystemConfigTypeEnum;
import org.yefei.qa.mock.model.bean.config.ServerCacheConfigBean;
import org.yefei.qa.mock.model.gen.pojo.TblSystemConfig;
import org.yefei.qa.mock.service.ICacheManager;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author yefei
 * @date: 2019/11/16
 */
@Service("cacheManager")
@Slf4j
public class CacheManagerImpl implements ICacheManager {

    @Autowired
    private ISystemConfigDao systemConfigDao;

    @Autowired
    private ObjectMapper jacksonFormatter;

    @Autowired
    private MappingJobCache mappingJobCache;

    @Autowired
    private MappingRulesDetailCache mappingRulesDetailCache;

    @Autowired
    private MappingTaskCache mappingTaskCache;

    @Autowired
    private GrpcRequestMappingCache grpcRequestMappingCache;

    @Autowired
    private GrpcRequestScriptCache grpcRequestScriptCache;

    @Override
    public void refreshCacheTime() {
        try {
            TblSystemConfig tblSystemConfig = systemConfigDao.queryTblSystemConfig(SystemConfigTypeEnum.SERVER_CACHE.getTypeID());
            if (tblSystemConfig != null && StringUtils.isNotEmpty(tblSystemConfig.getConfigData())) {
                ServerCacheConfigBean serverCacheConfigBean = jacksonFormatter.readValue(tblSystemConfig.getConfigData(), ServerCacheConfigBean.class);
                long cacheTimeSeconds = serverCacheConfigBean.getCacheTimeSeconds();
                if (GuavaContants.EXPIRE_DURATION != cacheTimeSeconds) {
                    mappingJobCache.updateCacheTime(serverCacheConfigBean.getCacheTimeSeconds(), TimeUnit.SECONDS);
                    mappingRulesDetailCache.updateCacheTime(serverCacheConfigBean.getCacheTimeSeconds(), TimeUnit.SECONDS);
                    mappingTaskCache.updateCacheTime(serverCacheConfigBean.getCacheTimeSeconds(), TimeUnit.SECONDS);
                    grpcRequestMappingCache.updateCacheTime(serverCacheConfigBean.getCacheTimeSeconds(), TimeUnit.SECONDS);
                    grpcRequestScriptCache.updateCacheTime(serverCacheConfigBean.getCacheTimeSeconds(), TimeUnit.SECONDS);

                    GuavaContants.EXPIRE_DURATION = cacheTimeSeconds;
                    log.info("更新缓存时间成功，缓存时间为: {} 秒", cacheTimeSeconds);
                }
            }
        } catch (Exception e) {
            log.error("更新缓存时间出错", e);
        }
    }

    @PostConstruct
    public void refresh() {
        log.info("初始化缓存时间");
        refreshCacheTime();
    }
}
