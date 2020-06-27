package org.yefei.qa.mock.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yefei
 * @date: 2020/3/31
 */
@Configuration
@ConfigurationProperties(prefix = "grpc")
@Data
public class GrpcConfig {

    private int port = 6565;

    private String cacheDir = "/tmp/mock-server/jars";

}
