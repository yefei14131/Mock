package org.yefei.qa.mock.service.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.cache.GuavaCacheSubject;
import org.yefei.qa.mock.model.bean.notice.GrpcJarRefreshNoticeContent;
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

    @Autowired
    private GrpcBizServiceImpl grpcBizService;

    public void handle(SystemNotice systemNotice) {
        try {
            switch (systemNotice.getNoticeType()) {
                case CACHE_TIME:
                    guavaCacheSubject.refreshCacheTime();
                    break;

                case GRPC_JAR:
                    GrpcJarRefreshNoticeContent grpcJarRefreshNoticeContent = JSONObject.parseObject(systemNotice.getNoticeContent(), GrpcJarRefreshNoticeContent.class);
                    if (grpcJarRefreshNoticeContent.isAll()) {
                        grpcBizService.loadAllGrpcInterface();
                    } else {
                        grpcBizService.reloadGrpcInterface(grpcJarRefreshNoticeContent.getJar());
                    }
                    break;

                default:
                    log.error("未识别的系统通知：{}", systemNotice.getNoticeType());
                    break;
            }
        } catch (Exception e) {
            log.error("", e);
        }
    }

}
