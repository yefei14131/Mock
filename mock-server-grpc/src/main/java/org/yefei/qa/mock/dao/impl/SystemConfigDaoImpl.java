package org.yefei.qa.mock.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.config.ApplicationContextProvider;
import org.yefei.qa.mock.dao.ISystemConfigDao;
import org.yefei.qa.mock.model.gen.dao.TblSystemConfigMapper;
import org.yefei.qa.mock.model.gen.pojo.TblSystemConfig;
import org.yefei.qa.mock.model.gen.pojo.TblSystemConfigExample;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:14
 */
@Service
public class SystemConfigDaoImpl implements ISystemConfigDao {

    @Autowired
    private TblSystemConfigMapper tblSystemConfigMapper;

    @Autowired
    private ApplicationContextProvider applicationContextProvider;

    @Override
    public int insertSystemConfig(TblSystemConfig tblSystemConfig) {
        return tblSystemConfigMapper.insert(tblSystemConfig);
    }

    @Override
    public List<TblSystemConfig> queryTblSystemConfigList(int configType) {
        TblSystemConfigExample example = buildExample(configType);
        return tblSystemConfigMapper.selectByExample(example);
    }

    @Override
    public long countTblSystemConfig(int configType) {
        TblSystemConfigExample example = buildExample(configType);
        return tblSystemConfigMapper.countByExample(example);
    }

    public TblSystemConfigExample buildExample(int configType) {
        TblSystemConfigExample example = new TblSystemConfigExample();
        TblSystemConfigExample.Criteria criteria = example.createCriteria();
        criteria.andConfigTypeEqualTo(configType);

        example.setOrderByClause("updateTime desc");
        return example;
    }

    @Override
    public int deleteSystemConfig(int configID) {
        return tblSystemConfigMapper.deleteByPrimaryKey(configID);
    }

    @Override
    public int updateSystemConfig(TblSystemConfig tblSystemConfig) {
        return tblSystemConfigMapper.updateByPrimaryKeySelective(tblSystemConfig);
    }

    @Override
    public TblSystemConfig queryTblSystemConfig(int configType) {
        TblSystemConfigExample example = new TblSystemConfigExample();
        example.createCriteria().andConfigTypeEqualTo(configType);
        List<TblSystemConfig> tblSystemConfigs = tblSystemConfigMapper.selectByExample(example);
        return (tblSystemConfigs.size() > 0) ? tblSystemConfigs.get(0) : null;
    }
}
