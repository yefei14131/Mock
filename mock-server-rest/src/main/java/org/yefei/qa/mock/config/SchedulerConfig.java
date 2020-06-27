package org.yefei.qa.mock.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.service.IRequestLogService;

/**
 * @author: yefei
 * @date: 2018/9/10 00:36
 * @desc: 定时任务配置
 */

@Component
@Slf4j
@EnableScheduling
public class SchedulerConfig {


    @Autowired
    private IRequestLogService requestLogService;

    @Scheduled(cron = "0 0 1 * * 1-5")
    private void deleteHistoryData(){
        log.info("开始定时删除历史日志");
        requestLogService.deleteHistoryData();
    }
//
//    @Scheduled(fixedDelay = 2000)
//    private void test() {
//        log.info("Scheduled test: {}", new Date().getSeconds());
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
