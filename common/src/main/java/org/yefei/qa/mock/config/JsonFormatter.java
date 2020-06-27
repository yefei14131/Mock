package org.yefei.qa.mock.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.googlecode.protobuf.format.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author: yefei
 * @date: 2018/9/9 12:13
 */
@Configuration
@Slf4j
public class JsonFormatter {

    @Bean(name = "jacksonFormatter")
    public ObjectMapper jacksonFormatter() {
        return new ObjectMapper();
    }

    @Bean(name = "grpcJsonFormatter")
    public JsonFormat grpcJsonFormatter(){
        return new JsonFormat();
    }


}
