package org.yefei.qa.mock.rabbitmq.listener.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.config.ApplicationContextProvider;
import org.yefei.qa.mock.debugger.SystemDebugger;
import org.yefei.qa.mock.enums.TaskTypeEnum;
import org.yefei.qa.mock.model.bean.callback.CallbackJobBean;
import org.yefei.qa.mock.model.bean.callback.CallbackTaskBean;
import org.yefei.qa.mock.rabbitmq.listener.RabbitmqListener;
import org.yefei.qa.mock.task.executor.ITaskExecutor;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/9/10 01:16
 */
@Slf4j
@Component("callbackJobRabbitmqListener")
public class CallbackJobRabbitmqListener implements RabbitmqListener {
    @Resource
    private ApplicationContextProvider applicationContextProvider;

    @Autowired
    private SystemDebugger systemDebugger;

    @Autowired
    private ObjectMapper jacksonFormatter;

    @Override
    public <T> void handleMessage(T message) {
        log.info("{} CallbackJobRabbitmqListener recieve message: {}", System.currentTimeMillis(), message);
        CallbackJobBean callbackJobBean = (CallbackJobBean) message;
        try {
            if (StringUtils.isNotEmpty(callbackJobBean.getTraceID())) {
                systemDebugger.addSystemLog(callbackJobBean.getTraceID(), String.format("开始执行延时任务: %s", callbackJobBean.getMemo()), jacksonFormatter.writeValueAsString(callbackJobBean));
            }
        } catch (JsonProcessingException e) {
            log.error("记录延时任务执行时报错", e);
        }

        List<CallbackTaskBean> taskList = callbackJobBean.getTaskList();
        String resultMsg = execTasks(taskList, callbackJobBean.getTraceID());

    }


    private String execTasks(List<CallbackTaskBean> taskList, String traceID) {
        StringBuilder sb = new StringBuilder();
        if (taskList.size() == 0) {
            sb.append("子任务列表为空");
        }
        taskList.stream().forEach(taskItem -> {
            TaskTypeEnum taskType = taskItem.getTaskType();
            Arrays.stream(TaskTypeEnum.values()).forEach(taskTypeItem -> {
                if (taskTypeItem.equals(taskType)) {
                    try {
                        ITaskExecutor taskExecutor = (ITaskExecutor) applicationContextProvider.getBean(taskTypeItem.getExecutor());
                        String message = taskExecutor.exec(taskItem.getTaskConfig(), traceID);
//                        sb.append(message);
                    } catch (Exception e) {
                        log.error(ExceptionUtils.getStackTrace(e));
                        sb.append(ExceptionUtils.getStackTrace(e));
                    }
                    sb.append("\n");
                }
            });
        });

        return sb.toString();
    }
}
