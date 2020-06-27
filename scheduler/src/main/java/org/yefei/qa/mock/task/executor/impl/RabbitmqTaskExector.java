package org.yefei.qa.mock.task.executor.impl;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.cache.SystemConfigRabbitmqTemplateCache;
import org.yefei.qa.mock.debugger.SystemDebugger;
import org.yefei.qa.mock.model.bean.callback.task.AbstractTaskBean;
import org.yefei.qa.mock.model.bean.callback.task.RabbitMqMessageType;
import org.yefei.qa.mock.model.bean.callback.task.RabbitmqTaskBean;
import org.yefei.qa.mock.task.executor.ITaskExecutor;

import javax.annotation.Resource;

/**
 * @author: yefei
 * @date: 2018/9/26 18:30
 */
@Slf4j
@Component("rabbitmqTaskExector")
public class RabbitmqTaskExector implements ITaskExecutor {

    @Autowired
    private SystemDebugger systemDebugger;

    @Resource
    private SystemConfigRabbitmqTemplateCache systemConfigRabbitmqTemplateCache;

    public String exec(AbstractTaskBean taskConfig, String traceID) {
        try {
            String configStr = JSONObject.toJSONString(taskConfig);
            log.info("Task -> 开始执行rabbitmqTask，configs: {}", configStr);
            systemDebugger.addSystemLog(traceID, "开始执行rabbitmqTask", configStr);
            RabbitmqTaskBean rabbitmqTaskBean = (RabbitmqTaskBean) taskConfig;
            RabbitTemplate rabbitTemplate = systemConfigRabbitmqTemplateCache.getRabbitTemplate(rabbitmqTaskBean.getSystemConfigID());

            Object msg = null;
            if (RabbitMqMessageType.STRING.equals(((RabbitmqTaskBean) taskConfig).getMessageType())) {
                msg = rabbitmqTaskBean.getMsgData();
            } else if (RabbitMqMessageType.BYTE.equals(((RabbitmqTaskBean) taskConfig).getMessageType())) {
                msg = rabbitmqTaskBean.getMsgData().getBytes();
            } else {
                msg = rabbitmqTaskBean.getMsgData().getBytes();
            }
            //发送延时消息
            rabbitTemplate.convertAndSend(rabbitmqTaskBean.getExchangeName(), rabbitmqTaskBean.getRoutingKey(), msg, new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    message.getMessageProperties().setDelay(rabbitmqTaskBean.getDelay());
                    return message;
                }
            });
            systemDebugger.addSystemLog(traceID, "rabbitmqTask执行完成", configStr);
            return "Task -> rabbitmq 消息发送成功: " + rabbitmqTaskBean.getExchangeName();
        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return String.format("Task -> rabbitmq 消息发送异常: %s", ExceptionUtils.getStackTrace(e));
        }
    }


}
