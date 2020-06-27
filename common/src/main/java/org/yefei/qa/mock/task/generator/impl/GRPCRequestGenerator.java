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
import org.yefei.qa.mock.model.bean.callback.task.GrpcTaskBean;
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
@Slf4j
@Component("grpcTaskGenerator")
public class GRPCRequestGenerator implements ITaskGenerator {

    @Autowired
    private SystemDebugger systemDebugger;

    @Autowired
    private GlobalDataPool globalDataPool;

    @Autowired
    private RestPluginExecutor restPluginExecutor;


    @Override
    public CallbackTaskBean generate(TaskSourceInfo taskSourceInfo, TblMappingTask requestTask, HashMap params, HashMap... dataPool) {
        try {
            HashMap[] newDataPool = DataPoolUtil.newDataPool(globalDataPool.getAll(), dataPool);

            CallbackTaskBean taskBean = new CallbackTaskBean();

            String configs = requestTask.getConfigs();

            GrpcTaskBean grpcTaskBean = JSONObject.parseObject(configs, GrpcTaskBean.class);
            HashMap<String, String> requesHeaders = JSONObject.parseObject(StringUtils.isEmpty(grpcTaskBean.getHeaders()) ? "{}" : grpcTaskBean.getHeaders(), HashMap.class);
            String body = grpcTaskBean.getBody();

            //替换 target 中的变量
            String target = VariableManager.replaceContent(grpcTaskBean.getTarget(), newDataPool);
            grpcTaskBean.setTarget(target);

            //替换 body 中的变量
            body = VariableManager.replaceContent(body, newDataPool);
            //执行插件
            if (SourceServerEnum.REST.equals(taskSourceInfo.getRestRequestMapping())) {
                body = restPluginExecutor.buildCallbackContent(taskSourceInfo.getRestRequestMapping().getGroupCode(), taskSourceInfo.getRestRequestMapping().getPath(), params, body);
            }

            grpcTaskBean.setBody(body);

            //替换 requestHeader中的变量
            if (requesHeaders != null && requesHeaders.size() > 0) {
                requesHeaders.forEach((k, v) -> {
                    requesHeaders.put(k, VariableManager.replaceContent(v, newDataPool));
                });
            }

            taskBean.setTaskConfig(grpcTaskBean);
            taskBean.setTaskType(TaskTypeEnum.GRPC_REQUEST);

            return taskBean;

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            systemDebugger.addSystemLog("生成http任务数据时报错", ExceptionUtils.getStackTrace(e));

            return null;
        }
    }


}
