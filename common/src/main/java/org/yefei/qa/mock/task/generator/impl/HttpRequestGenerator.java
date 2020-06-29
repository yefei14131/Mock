package org.yefei.qa.mock.task.generator.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.cache.GlobalDataPool;
import org.yefei.qa.mock.debugger.SystemDebugger;
import org.yefei.qa.mock.enums.SourceServerEnum;
import org.yefei.qa.mock.enums.TaskTypeEnum;
import org.yefei.qa.mock.model.bean.callback.CallbackTaskBean;
import org.yefei.qa.mock.model.bean.callback.task.HttpTaskBean;
import org.yefei.qa.mock.model.gen.pojo.TblMappingTask;
import org.yefei.qa.mock.plugin.core.handler.RestPluginExecutor;
import org.yefei.qa.mock.task.generator.DataPoolUtil;
import org.yefei.qa.mock.task.generator.ITaskGenerator;
import org.yefei.qa.mock.task.generator.TaskSourceInfo;
import org.yefei.qa.mock.utils.VariableManager;

import java.util.HashMap;

/**
 * @author: yefei
 * @date: 2018/9/26 18:30
 */

@Component("httpTaskGenerator")
@Slf4j
public class HttpRequestGenerator implements ITaskGenerator {

    @Autowired
    private SystemDebugger systemDebugger;

    @Autowired
    private RestPluginExecutor restPluginExecutor;

    @Autowired
    private GlobalDataPool globalDataPool;

    @Override
    public CallbackTaskBean generate(TaskSourceInfo taskSourceInfo, TblMappingTask requestTask, HashMap params, HashMap... dataPool) {
        try {
            HashMap[] newDataPool = DataPoolUtil.newDataPool(globalDataPool.getAll(), dataPool);

            //http task
            CallbackTaskBean taskBean = new CallbackTaskBean();

            String configs = requestTask.getConfigs();

            HttpTaskBean httpTaskBean = JSONObject.parseObject(configs, HttpTaskBean.class);

            HashMap<String, String> requesHeaders = JSONObject.parseObject(StringUtils.isEmpty(httpTaskBean.getHeaders()) ? "{}" : httpTaskBean.getHeaders(), HashMap.class);
            String body = httpTaskBean.getBody();

            //替换 url 中的变量
            String url = VariableManager.replaceContent(httpTaskBean.getUrl(), newDataPool);
            httpTaskBean.setUrl(url);

            //替换 body 中的变量
            body = VariableManager.replaceContent(body, newDataPool);

            //执行rest插件
            if (SourceServerEnum.REST.equals(taskSourceInfo.getSourceServer())) {
                body = restPluginExecutor.buildCallbackContent(taskSourceInfo.getRestRequestMapping().getGroupCode(), taskSourceInfo.getRestRequestMapping().getPath(), params, body);
            }

            httpTaskBean.setBody(body);

            //替换 requestHeader中的变量
            if (requesHeaders != null && requesHeaders.size() > 0){
                requesHeaders.forEach((k, v) -> {
                    requesHeaders.put(k, VariableManager.replaceContent(v, newDataPool));
                });
            }

            taskBean.setTaskConfig(httpTaskBean);
            taskBean.setTaskType(TaskTypeEnum.HTTP_REQUEST);

            return taskBean;
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            systemDebugger.addSystemLog("生成http任务数据时报错", ExceptionUtils.getStackTrace(e));

            return null;
        }

    }



}
