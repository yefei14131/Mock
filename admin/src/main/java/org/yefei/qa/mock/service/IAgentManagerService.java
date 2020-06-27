package org.yefei.qa.mock.service;

import org.yefei.qa.mock.model.gen.pojo.TblAgentInstance;

import java.util.List;

/**
 * @author yefei
 * @date: 2020/5/13
 */
public interface IAgentManagerService {

    TblAgentInstance agentRequest(String serviceName, String agentName, String ip);

    void setActive(int ID, boolean active);

    TblAgentInstance get(String serviceName, String agentName, String ip);

    List<TblAgentInstance> queryActiveAgentList();

    long countActiveAgentList();
}
