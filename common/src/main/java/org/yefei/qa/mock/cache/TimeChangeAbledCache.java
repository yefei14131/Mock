package org.yefei.qa.mock.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.yefei.qa.mock.contants.GuavaContants;

import javax.annotation.PostConstruct;

/**
 * @author yefei
 * @date: 2020/10/21
 */
public abstract class TimeChangeAbledCache extends BaseGuavaCache {

    @Autowired
    private GuavaCacheSubject guavaCacheSubject;

    public TimeChangeAbledCache() {
        super(GuavaContants.EXPIRE_DURATION, GuavaContants.EXPIRE_TIME_UNIT);

    }

    @PostConstruct
    protected void init() {
        guavaCacheSubject.addObserver(this);
    }
}
