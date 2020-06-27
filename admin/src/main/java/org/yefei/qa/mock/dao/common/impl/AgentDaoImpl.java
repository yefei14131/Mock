package org.yefei.qa.mock.dao.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.dao.common.IAgentDao;
import org.yefei.qa.mock.model.gen.dao.TblAgentInstanceMapper;
import org.yefei.qa.mock.model.gen.pojo.TblAgentInstance;
import org.yefei.qa.mock.model.gen.pojo.TblAgentInstanceExample;

import java.util.Date;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:16
 */
@Service
public class AgentDaoImpl implements IAgentDao {

    @Autowired
    private TblAgentInstanceMapper agentInstanceMapper;

    @Override
    public List<TblAgentInstance> queryAgent(String serviceName, String agentName, String ip) {
        TblAgentInstanceExample example = new TblAgentInstanceExample();
        example.createCriteria().andServiceNameEqualTo(serviceName).andAgentNameEqualTo(agentName).andIpEqualTo(ip);

        return agentInstanceMapper.selectByExample(example);
    }

    @Override
    public List<TblAgentInstance> queryActiveAgentList(Date lessTime) {
        TblAgentInstanceExample example = new TblAgentInstanceExample();
        example.createCriteria().andLastRequestTimeGreaterThan(lessTime);
        example.setOrderByClause("lastRequestTime desc, isActive desc, agentName ");

        return agentInstanceMapper.selectByExample(example);
    }

    @Override
    public long countActiveAgentList(Date lessTime) {
        TblAgentInstanceExample example = new TblAgentInstanceExample();
        example.createCriteria().andLastRequestTimeGreaterThan(lessTime);

        return agentInstanceMapper.countByExample(example);
    }

    @Override
    public void insert(TblAgentInstance tblAgentInstance) {
        agentInstanceMapper.insert(tblAgentInstance);
    }

    @Override
    public void update(TblAgentInstance tblAgentInstance) {
        agentInstanceMapper.updateByPrimaryKey(tblAgentInstance);
    }

    @Override
    public void remove(Date maxTime) {
        TblAgentInstanceExample example = new TblAgentInstanceExample();
        example.createCriteria().andLastRequestTimeLessThan(maxTime);
        agentInstanceMapper.deleteByExample(example);
    }

    @Override
    public TblAgentInstance get(int ID) {
        return agentInstanceMapper.selectByPrimaryKey(ID);
    }
}
