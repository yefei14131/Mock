package org.yefei.qa.mock.config.httpclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.annotation.Resource;

/**
 * @author: yefei
 * @date: 2018/9/26 21:48
 */
@Data
@Slf4j
public class HttpResult {
    private int code;
    private String body;

    @Resource
    private ObjectMapper jacksonFormatter;

    public HttpResult(int code, String body) {
        this.code = code;
        this.body = body;
    }


    public String toString(){
        if(StringUtils.isNotBlank(this.body)){
            return this.body;
        }

        try {
            return jacksonFormatter.writeValueAsString(this);

        } catch (JsonProcessingException e) {
            log.error(ExceptionUtils.getStackTrace(e));

            return null;
        }
    }
}

