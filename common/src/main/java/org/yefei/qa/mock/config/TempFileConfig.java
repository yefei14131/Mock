package org.yefei.qa.mock.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: yefei
 * @date: 2018/11/11 21:31
 */
@Component
@Data
public class TempFileConfig {

    @Value("${spring.application.name}")
    private String applicationName;

    public String getTempDir(){
        return System.getProperties().getProperty("user.home") + "/temp/qa/" + applicationName + "/grpcjars/";
    }
}
