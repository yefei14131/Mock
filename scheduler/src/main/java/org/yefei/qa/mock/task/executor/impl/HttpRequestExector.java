package org.yefei.qa.mock.task.executor.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.config.httpclient.HttpClient;
import org.yefei.qa.mock.config.httpclient.HttpResult;
import org.yefei.qa.mock.debugger.SystemDebugger;
import org.yefei.qa.mock.model.bean.callback.task.AbstractTaskBean;
import org.yefei.qa.mock.model.bean.callback.task.HttpTaskBean;
import org.yefei.qa.mock.task.executor.ITaskExecutor;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author: yefei
 * @date: 2018/9/26 18:30
 */

@Component("httpTaskExector")
@Slf4j
public class HttpRequestExector implements ITaskExecutor {

    @Resource
    private HttpClient httpClient;

    @Autowired
    private SystemDebugger systemDebugger;

    @Override
    public String exec(AbstractTaskBean taskConfig, String traceID) {
        try {
            log.info("Task -> 开始执行httpTask，configs: {}", JSONObject.toJSON(taskConfig));
            HttpTaskBean httpTaskBean = (HttpTaskBean) taskConfig;

            HttpResult result = httpClient.send(
                    httpTaskBean.getUrl(),
                    httpTaskBean.getMethod(),
                    StringUtils.isEmpty(httpTaskBean.getHeaders()) ? null : JSONObject.parseObject(httpTaskBean.getHeaders(), HashMap.class),
                    httpTaskBean.getBody());

            if (result == null ){
                throw new Exception("Task -> 不支持的http请求：" + httpTaskBean.getUrl());
            }

            String resultMsg = String.format("target:%s,  返回结果：%s", httpTaskBean.getUrl(), JSONObject.toJSONString(result));
            if (StringUtils.isNotEmpty(traceID)) {
                systemDebugger.addSystemLog(traceID, "http请求执行成功", resultMsg);
            }


            return resultMsg;
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            String resultMsg = String.format("Task -> http 请求执行异常：%s === config信息：%s", ExceptionUtils.getStackTrace(e), JSONObject.toJSON(taskConfig));
            if (StringUtils.isNotEmpty(traceID)) {
                systemDebugger.addSystemLog(traceID, "http 请求执行异常", resultMsg);
            }
            return resultMsg;
        }

    }



}
