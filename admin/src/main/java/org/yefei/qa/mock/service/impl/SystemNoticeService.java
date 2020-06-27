package org.yefei.qa.mock.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.model.bean.notice.GrpcJarRefreshNoticeContent;
import org.yefei.qa.mock.model.bean.notice.SystemNotice;
import org.yefei.qa.mock.model.bean.notice.SystemNoticeConsumer;
import org.yefei.qa.mock.model.bean.notice.SystemNoticeType;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcInterfaceJar;
import org.yefei.qa.mock.rabbitmq.RabbitmqProps;

/**
 * @author yefei
 * @date: 2020/4/17
 */
@Service
public class SystemNoticeService {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitmqProps rabbitProperties;


    public void notifyForCacheTime() {
        SystemNotice systemNotice = new SystemNotice();
        systemNotice.getNoticeConsumerList().add(SystemNoticeConsumer.REST);
        systemNotice.getNoticeConsumerList().add(SystemNoticeConsumer.GRPC);
        systemNotice.setNoticeType(SystemNoticeType.CACHE_TIME);
        rabbitTemplate.convertAndSend(rabbitProperties.getSystemNoticeExchangeName(), null, systemNotice);
    }


    public void notifyForGrpcJar(TblGrpcInterfaceJar grpcInterfaceJar) {
        SystemNotice systemNotice = new SystemNotice();
        systemNotice.getNoticeConsumerList().add(SystemNoticeConsumer.SCHEDULER);
        systemNotice.getNoticeConsumerList().add(SystemNoticeConsumer.GRPC);
        systemNotice.setNoticeType(SystemNoticeType.GRPC_JAR);

        GrpcJarRefreshNoticeContent grpcJarRefreshNoticeContent = new GrpcJarRefreshNoticeContent();
        grpcJarRefreshNoticeContent.setAll(false);
        grpcJarRefreshNoticeContent.setJar(grpcInterfaceJar);
        systemNotice.setNoticeContent(JSONObject.toJSONString(grpcJarRefreshNoticeContent));

        rabbitTemplate.convertAndSend(rabbitProperties.getSystemNoticeExchangeName(), null, systemNotice);
    }
}
