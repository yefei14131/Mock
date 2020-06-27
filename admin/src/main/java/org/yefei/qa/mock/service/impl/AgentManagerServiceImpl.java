package org.yefei.qa.mock.service.impl;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.dao.common.IAgentDao;
import org.yefei.qa.mock.model.gen.pojo.TblAgentInstance;
import org.yefei.qa.mock.service.IAgentManagerService;
import org.yefei.qa.mock.websocket.WebSocketOnlineStatePusher;

import java.util.Date;
import java.util.List;

/**
 * @author yefei
 * @date: 2020/4/17
 */
@Service
public class AgentManagerServiceImpl implements IAgentManagerService {

    // 查询最近多长时间内有请求的agent
    private final int queryOnlineMinutes = 10;

    @Autowired
    private IAgentDao agentDao;

    @Autowired
    private WebSocketOnlineStatePusher webSocketOnlineStatePusher;

    @Override
    public TblAgentInstance agentRequest(String serviceName, String agentName, String ip) {
        TblAgentInstance agentInstance = this.get(serviceName, agentName, ip);
        if (agentInstance == null) {
            agentInstance = insert(serviceName, agentName, ip);
        } else {
            agentInstance.setLastRequestTime(new Date());
            agentInstance.setUpdateTime(new Date());
            agentDao.update(agentInstance);
        }
        return agentInstance;
    }

    private TblAgentInstance gen(String serviceName, String agentName, String ip) {
        TblAgentInstance agentInstance = new TblAgentInstance();
        agentInstance.setAgentName(agentName);
        agentInstance.setIp(ip);
        agentInstance.setServiceName(serviceName);
        agentInstance.setLastRequestTime(new Date());
        agentInstance.setUpdateTime(new Date());
        return agentInstance;
    }

    private TblAgentInstance insert(String serviceName, String agentName, String ip) {
        TblAgentInstance agentInstance = gen(serviceName, agentName, ip);
        agentInstance.setIsActive(true);
        agentInstance.setFirstRequestTime(new Date());
        agentDao.insert(agentInstance);
        return agentInstance;
    }

    @Override
    public void setActive(int ID, boolean active) {
        TblAgentInstance agentInstance = agentDao.get(ID);
        agentInstance.setIsActive(active);
        //更新数据库
        agentDao.update(agentInstance);
        //通知agent
        webSocketOnlineStatePusher.pushAgentState(agentInstance);
    }

    @Override
    public TblAgentInstance get(String serviceName, String agentName, String ip) {
        List<TblAgentInstance> existsList = agentDao.queryAgent(serviceName, agentName, ip);
        return existsList == null || existsList.isEmpty() ? null : existsList.get(0);
    }

    @Override
    public List<TblAgentInstance> queryActiveAgentList() {
        return agentDao.queryActiveAgentList(genLessTime());
    }

    @Override
    public long countActiveAgentList() {
        return agentDao.countActiveAgentList(genLessTime());
    }


    private Date genLessTime() {
        return DateUtils.addMinutes(new Date(), -queryOnlineMinutes);
    }

}
