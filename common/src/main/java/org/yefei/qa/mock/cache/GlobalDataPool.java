package org.yefei.qa.mock.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.yefei.qa.mock.contants.GuavaContants;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author: yefei
 * @date: 2018/8/31 01:27
 */
@Slf4j
@Configuration
public class GlobalDataPool extends BaseGuavaCache {

    private static final int EXPIRE_DURATION = 30;
    private ConcurrentHashMap<String, Object> preData = new ConcurrentHashMap<>();


    public GlobalDataPool() {
        super(EXPIRE_DURATION, TimeUnit.SECONDS);
    }

    public void updateCacheTime(long duration) {
        super.updateCacheTime(duration, GuavaContants.EXPIRE_TIME_UNIT);
    }

    /**
     * 缓存数据加载方法
     *
     * @param key
     * @return
     * @author coshaho
     */
    @Override
    protected Object loadData(Object key) {
        if (preData.containsKey(key)) {
            return preData.get(key);
        }
        return "";
    }

    public synchronized void updateCache(String key, Object val) {
        this.preData.put(key, val);
        this.cache.refresh(key);
        try {
            this.cache.get(key);
        } catch (ExecutionException e) {
            log.error("", e);
        }
        this.preData.clear();
    }

    public synchronized void updateCache(Map<String, Object> kv) {
        this.preData.putAll(kv);
        kv.keySet().forEach(key -> {
            this.cache.refresh(key);
            try {
                this.cache.get(key);
            } catch (ExecutionException e) {
                log.error("", e);
            }
        });
        this.preData.clear();
    }

    public HashMap<String, Object> getAll() {
        HashMap preDefine = new HashMap();
        this.cache.asMap().forEach((key, val) -> {
            preDefine.put(key, val);
        });
        return preDefine;
    }
}
