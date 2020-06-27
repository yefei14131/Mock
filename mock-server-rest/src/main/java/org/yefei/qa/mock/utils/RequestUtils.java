package org.yefei.qa.mock.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.HashMap;

/**
 * @author: yefei
 * @date: 2018/8/17 19:54
 */
@Slf4j
@Component
public class RequestUtils {

    @Autowired
    private ObjectMapper jacksonFormater;

    public HashMap getRequestBody(HttpServletRequest request) {

        HashMap params = new HashMap();
        try {
            // TODO 看spring读取json的方式
            BufferedReader reader = request.getReader();
            char[] buf = new char[512];
            int len = 0;
            StringBuffer contentBuffer = new StringBuffer();
            while ((len = reader.read(buf)) != -1) {
                contentBuffer.append(buf, 0, len);
            }
            params = getRequestBody(contentBuffer.toString());
        }catch (Exception e){
            log.error("解析请求体异常：{}", ExceptionUtils.getStackTrace(e));
        }
        return params;
    }

    private HashMap getRequestBody(String requestBody) {
        if (requestBody.trim().length() > 0) {
            requestBody  = requestBody.replaceAll("\\s+","");
            try {
//                return JSON.parseObject(requestBody, HashMap.class);
                return jacksonFormater.readValue(requestBody, HashMap.class);
            } catch (Exception e) {
                log.error("请求体无法解析： {}", requestBody);
            }
        }

        return null;
    }
}
