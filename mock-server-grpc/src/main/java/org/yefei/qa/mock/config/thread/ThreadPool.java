package org.yefei.qa.mock.config.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yefei
 * @date: 2020/5/17
 */
@Configuration
public class ThreadPool {

    @Bean(name = "multiThreadPool")
    public ThreadPoolExecutor createThreadPoolExecutor() {
        return new ThreadPoolExecutor(2, 50, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(20));
    }
}
