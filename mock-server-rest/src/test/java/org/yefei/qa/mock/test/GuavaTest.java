package org.yefei.qa.mock.test;

import lombok.extern.slf4j.Slf4j;
import org.yefei.qa.mock.cache.BaseGuavaCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author yefei
 * @date: 2020/4/12
 */
@Slf4j
public class GuavaTest extends BaseGuavaCache {

    GuavaTest() {
        super(3, TimeUnit.SECONDS);
        log.info("cache time 3s");
    }

    @Override
    protected Object loadData(Object o) {
        log.info("load data");
        return "123";
    }


    public static void main(String[] args) throws InterruptedException {
        GuavaTest cache = new GuavaTest();

        for (int i = 0; i < 10; i++) {
            print(cache);
            Thread.sleep(1000);
        }

        cache.updateCacheTime(5, TimeUnit.SECONDS);
        log.info("updateCacheTime 5s");

        for (int i = 0; i < 10; i++) {
            print(cache);
            Thread.sleep(1000);
        }


    }

    private static void print(GuavaTest cache) {
        String key = "key";
        log.info("key {} get value: {}", key, cache.getVal(key));
    }


    public Object getVal(String key) {
        try {
            return cache.get(key);
        } catch (ExecutionException e) {
            log.error("cache get error", e);
            return null;
        }
    }

}
