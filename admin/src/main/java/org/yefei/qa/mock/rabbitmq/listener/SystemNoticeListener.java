package org.yefei.qa.mock.rabbitmq.listener;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.model.bean.notice.SystemNotice;
import org.yefei.qa.mock.model.bean.notice.SystemNoticeConsumer;
import org.yefei.qa.mock.rabbitmq.handler.SystemNoticeHandler;
import org.yefei.qa.mock.utils.RabbitMqMessageUtils;

/**
 * @author yefei
 * @date: 2020/4/10
 */
@Component
@Slf4j
public class SystemNoticeListener<T> {

    @Autowired
    private SystemNoticeHandler systemNoticeHandler;

    public void handleMessage(T message) {

        try {
            SystemNotice systemNotice = RabbitMqMessageUtils.formatMesage(message, SystemNotice.class);
            if (systemNotice == null)
                return;

            log.info("收到系统通知消息：{}", JSONObject.toJSONString(systemNotice));

            if (systemNotice.getNoticeConsumerList().contains(SystemNoticeConsumer.GRPC) || systemNotice.getNoticeConsumerList().contains(SystemNoticeConsumer.ALL)) {
                systemNoticeHandler.handle(systemNotice);
            } else {
                log.debug("不需要当前服务处理的系统消息：{}", systemNotice.getNoticeConsumerList());
            }

        } catch (Exception e) {
            log.error("处理系统通知消息异常", e);
        }
    }
}
