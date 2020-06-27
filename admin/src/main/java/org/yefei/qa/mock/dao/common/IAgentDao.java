package org.yefei.qa.mock.dao.common;

import org.springframework.stereotype.Repository;
import org.yefei.qa.mock.model.gen.pojo.TblAgentInstance;

import java.util.Date;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:14
 */
@Repository
public interface IAgentDao {

    List<TblAgentInstance> queryAgent(String serviceName, String agentName, String ip);

    List<TblAgentInstance> queryActiveAgentList(Date lessTime);

    long countActiveAgentList(Date lessTime);

    void insert(TblAgentInstance tblAgentInstance);

    void update(TblAgentInstance tblAgentInstance);

    void remove(Date maxTime);

    TblAgentInstance get(int ID);
}
