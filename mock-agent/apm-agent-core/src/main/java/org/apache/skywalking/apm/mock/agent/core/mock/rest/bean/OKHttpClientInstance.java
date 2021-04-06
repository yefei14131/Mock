package org.apache.skywalking.apm.mock.agent.core.mock.rest.bean;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

/**
 * @author yefei
 * @date: 2020/4/23
 */
public enum OKHttpClientInstance {
    INSTANCE {
        @Override
        public OkHttpClient instance() {
            ConnectionPool connectionPool = new ConnectionPool(10, 10, TimeUnit.MINUTES);
            return new OkHttpClient.Builder()
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectionPool(connectionPool)
                    .build();
        }
    };

    public abstract OkHttpClient instance();
}
