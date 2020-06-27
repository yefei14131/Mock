package org.yefei.qa.mock.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yefei.qa.mock.cache.GlobalDataPool;
import org.yefei.qa.mock.config.ResponseAdapter;
import org.yefei.qa.mock.utils.RequestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yefei
 * @date: 2020/5/16
 */
@Slf4j
@Controller
@RequestMapping("/extends")
public class ExtendsController {

    @Autowired
    private RequestUtils requestUtils;

    @Autowired
    private GlobalDataPool globalDataPool;

    @Autowired
    private ThreadPoolExecutor multiThreadPool;

    @Autowired
    private ResponseAdapter responseAdapter;


    /**
     * 预定义变量，目前只支持单机，TODO 分布式以后再支持
     *
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/define")
    public Object preDefine(HttpServletRequest request, HttpServletResponse response) {

        // 准备 parameters
        HashMap params = new HashMap<>();
        String contentType = request.getContentType();

        if (org.apache.commons.lang3.StringUtils.isNotBlank(contentType) && contentType.contains("json")) {
            // application/json;charset=UTF-8
            params = requestUtils.getRequestBody(request);

        }

        for (Map.Entry entry : request.getParameterMap().entrySet()) {
            String key = entry.getKey().toString();
            String[] valueArr = (String[]) entry.getValue();
            params.put(key, valueArr.length == 1 ? valueArr[0] : valueArr);
        }

        globalDataPool.updateCache(params);
        return globalDataPool.getAll();
    }


    @ResponseBody
    @RequestMapping("/all")
    public Object getAll(HttpServletRequest request, HttpServletResponse response) {

        return globalDataPool.getAll();
    }

    @ResponseBody
    @RequestMapping("/test")
    public Object test(HttpServletRequest request, HttpServletResponse response, @RequestBody String body) {
        try {
            BufferedReader reader = request.getReader();
            char[] buf = new char[512];
            int len = 0;
            StringBuffer contentBuffer = new StringBuffer();
            while ((len = reader.read(buf)) != -1) {
                contentBuffer.append(buf, 0, len);
            }
            String requestBody = contentBuffer.toString();
            log.info("requestBody: {}", requestBody);
        } catch (Exception e) {
            log.error("error", e);
        }

        return globalDataPool.getAll();
    }


}
