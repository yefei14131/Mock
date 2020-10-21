package org.yefei.qa.mock.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.yefei.qa.mock.contants.GuavaContants;
import org.yefei.qa.mock.dao.ISystemConfigDao;
import org.yefei.qa.mock.model.bean.config.RabbitmqConfigBean;
import org.yefei.qa.mock.model.gen.pojo.TblMappingRulesDetail;
import org.yefei.qa.mock.model.gen.pojo.TblSystemConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author: yefei
 * @date: 2018/8/31 01:27
 */
@Configuration
@Slf4j
public class SystemConfigRabbitmqTemplateCache extends BaseGuavaCache{
    private static final List<TblMappingRulesDetail> empty = new ArrayList<>();

    @Autowired
    private ISystemConfigDao systemConfigDao;


    public SystemConfigRabbitmqTemplateCache(){
        super(GuavaContants.EXPIRE_DURATION_LONG, GuavaContants.EXPIRE_TIME_UNIT_LONG);
    }

    /**
     * 缓存数据加载方法
     *
     * @param configID
     * @return
     * @author coshaho
     */
    @Override
    protected RabbitTemplate loadData(Object configID) {
        try {
            TblSystemConfig systemConfig =  systemConfigDao.getSystemConfigByID(Integer.parseInt(configID.toString()));

            RabbitmqConfigBean rabbitmqConfigBean = new ObjectMapper().readValue(systemConfig.getConfigData(), RabbitmqConfigBean.class);

            return genRabbitTemplate(rabbitmqConfigBean);

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }

        return null;
    }


    public RabbitTemplate getRabbitTemplate(int configID){
        try {
            return  (RabbitTemplate)cache.get(configID);
        } catch (ExecutionException e) {
            log.error(ExceptionUtils.getStackTrace(e));
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }

        return null;
    }


    private RabbitTemplate genRabbitTemplate(RabbitmqConfigBean rabbitmqConfigBean){
        ConnectionFactory rabbitConnectionFactory = new ConnectionFactory();
        rabbitConnectionFactory.setHost(rabbitmqConfigBean.getHost());
        rabbitConnectionFactory.setPort(rabbitmqConfigBean.getPort());
        rabbitConnectionFactory.setUsername(rabbitmqConfigBean.getUsername());
        rabbitConnectionFactory.setPassword(rabbitmqConfigBean.getPassword());

        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(rabbitConnectionFactory);

        return new RabbitTemplate(cachingConnectionFactory);
    }




}
