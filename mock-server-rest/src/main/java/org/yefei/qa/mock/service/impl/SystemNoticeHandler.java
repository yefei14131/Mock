package org.yefei.qa.mock.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.cache.GuavaCacheSubject;
import org.yefei.qa.mock.model.bean.notice.SystemNotice;

/**
 * @author yefei
 * @date: 2020/4/10
 */
@Service
@Slf4j
public class SystemNoticeHandler {

    @Autowired
    private GuavaCacheSubject guavaCacheSubject;

    public void handle(SystemNotice systemNotice) {

        switch (systemNotice.getNoticeType()) {
            case CACHE_TIME:
                guavaCacheSubject.refreshCacheTime();
                break;
            default:
                log.error("未识别的系统通知：{}", systemNotice.getNoticeType());
                break;
        }

    }

}
