package org.yefei.qa.mock.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.cache.MappingJobCache;
import org.yefei.qa.mock.cache.MappingTaskCache;
import org.yefei.qa.mock.config.ApplicationContextProvider;
import org.yefei.qa.mock.debugger.SystemDebugger;
import org.yefei.qa.mock.enums.SourceServerEnum;
import org.yefei.qa.mock.enums.TaskTypeEnum;
import org.yefei.qa.mock.model.bean.callback.CallbackJobBean;
import org.yefei.qa.mock.model.bean.callback.CallbackTaskBean;
import org.yefei.qa.mock.model.gen.pojo.TblMappingJob;
import org.yefei.qa.mock.model.gen.pojo.TblMappingTask;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMapping;
import org.yefei.qa.mock.rabbitmq.RabbitmqProps;
import org.yefei.qa.mock.service.IMappingJobService;
import org.yefei.qa.mock.task.generator.ITaskGenerator;
import org.yefei.qa.mock.task.generator.TaskSourceInfo;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/9/27 21:25
 */

@Service("requestJobService")
@Slf4j
public class MappingJobServiceImpl implements IMappingJobService {

    @Resource
    MappingJobCache requestJobCache;

    @Resource
    MappingTaskCache requestTaskCache;

    @Resource
    private RabbitmqProps rabbitmqProps;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper jacksonFormatter;

    @Resource
    private ApplicationContextProvider applicationContextProvider;

    @Autowired
    private SystemDebugger systemDebugger;

    @Override
    public void addJobs(TblRestRequestMapping restRequestMapping, HashMap userDefined, HashMap params, HashMap headers, HashMap cookies) {
        List<TblMappingJob> tblMappingJobs = requestJobCache.queryTblMappingJobsByRequestID(restRequestMapping.getRequestID());

        systemDebugger.addSystemLog("即将被执行的延时任务数", String.valueOf(tblMappingJobs.size()));
        if (tblMappingJobs != null && tblMappingJobs.size() > 0 ){
            log.info("开始添加延时任务：{}", tblMappingJobs);

            //设置任务来源信息
            TaskSourceInfo taskSourceInfo = new TaskSourceInfo();
            taskSourceInfo.setRestRequestMapping(restRequestMapping);
            taskSourceInfo.setSourceServer(SourceServerEnum.REST);

            tblMappingJobs.stream().forEach(tblMappingJob->{
                this.addJob(tblMappingJob, taskSourceInfo, userDefined, params, headers, cookies);
            });
        }
    }

    private void addJob(TblMappingJob tblMappingJob, TaskSourceInfo taskSourceInfo, HashMap userDefined, HashMap params, HashMap headers, HashMap cookies) {
        try {
            List<TblMappingTask> tblMappingTasks = requestTaskCache.queryRestMappingRulesDetailsByRequestID(tblMappingJob.getJobID());

            systemDebugger.addSystemLog(String.format("任务 %s(%d) 包含的子任务数: %d", tblMappingJob.getMemo(), tblMappingJob.getJobID(), tblMappingTasks.size()), String.valueOf(tblMappingTasks.size()));
            if (tblMappingTasks == null || tblMappingTasks.size() == 0) {
                return;
            }

            CallbackJobBean callbackJobBean = new CallbackJobBean();
            callbackJobBean.setDelay(tblMappingJob.getDelay());
            callbackJobBean.setNeedNotify(false);
            callbackJobBean.setSourceServer(SourceServerEnum.REST.getSrc());

            tblMappingTasks.stream().forEach(requestTaskItem -> {

                int taskType = requestTaskItem.getTaskType();

                Arrays.stream(TaskTypeEnum.values()).forEach(taskTypeEnumItem -> {
                    if (taskTypeEnumItem.getTaskType() == taskType) {
                        try {
                            ITaskGenerator taskGenerator = (ITaskGenerator) applicationContextProvider.getBean(taskTypeEnumItem.getGenerator());
                            CallbackTaskBean callbackTaskBean = taskGenerator.generate(taskSourceInfo, requestTaskItem, params, userDefined, params, headers, cookies);
                            if (callbackTaskBean != null) {
                                callbackJobBean.getTaskList().add(callbackTaskBean);
                            }

                        } catch (Exception e) {
                            log.error(ExceptionUtils.getStackTrace(e));
                        }
                    }
                });
            });
            if (systemDebugger.isDebugging()) {
                callbackJobBean.setTraceID(systemDebugger.getTraceID());
            }
            callbackJobBean.setMemo(tblMappingJob.getMemo());
            
            rabbitTemplate.convertAndSend(rabbitmqProps.getCallbackJobExchangeName(), rabbitmqProps.getCallbackJobRoutingKey(), callbackJobBean, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setDelay(callbackJobBean.getDelay());
                    return message;
                }
            });
            log.info("添加延时任务成功：{}", callbackJobBean);
            systemDebugger.addSystemLog("添加延时任务成功", callbackJobBean);

        }catch (Exception e){
            log.error("添加job出错，job信息：{}\n 异常信息{}", tblMappingJob, ExceptionUtils.getStackTrace(e));
            systemDebugger.addSystemLog(String.format("添加job出错，job信息: %s(%d)", tblMappingJob.getMemo(), tblMappingJob.getJobID()), ExceptionUtils.getStackTrace(e));
        }
    }
}
