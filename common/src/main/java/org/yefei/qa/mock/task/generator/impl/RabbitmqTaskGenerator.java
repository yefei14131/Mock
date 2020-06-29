package org.yefei.qa.mock.task.generator.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.cache.GlobalDataPool;
import org.yefei.qa.mock.debugger.SystemDebugger;
import org.yefei.qa.mock.enums.SourceServerEnum;
import org.yefei.qa.mock.enums.TaskTypeEnum;
import org.yefei.qa.mock.model.bean.callback.CallbackTaskBean;
import org.yefei.qa.mock.model.bean.callback.task.RabbitmqTaskBean;
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
@Component("rabbitmqTaskGenerator")
public class RabbitmqTaskGenerator implements ITaskGenerator {

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

            String configs = requestTask.getConfigs();
            RabbitmqTaskBean rabbitmqTaskBean = JSONObject.parseObject(configs, RabbitmqTaskBean.class);

            String msgData = rabbitmqTaskBean.getMsgData();

            //替换变量
            msgData = VariableManager.replaceContent(msgData, newDataPool);

            //执行rest插件
            if (SourceServerEnum.REST.equals(taskSourceInfo.getSourceServer())) {
                msgData = restPluginExecutor.buildCallbackContent(taskSourceInfo.getRestRequestMapping().getGroupCode(), taskSourceInfo.getRestRequestMapping().getPath(), params, msgData);
            }

            rabbitmqTaskBean.setMsgData(msgData);

            CallbackTaskBean taskBean = new CallbackTaskBean();

            taskBean.setTaskType(TaskTypeEnum.RABBIT_MQ);
            taskBean.setTaskConfig(rabbitmqTaskBean);

            return taskBean;
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            systemDebugger.addSystemLog("生成rabbitmq任务数据时报错", ExceptionUtils.getStackTrace(e));

            return null;
        }
    }


}
