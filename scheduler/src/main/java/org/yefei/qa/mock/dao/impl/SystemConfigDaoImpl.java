package org.yefei.qa.mock.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.dao.ISystemConfigDao;
import org.yefei.qa.mock.model.gen.dao.TblSystemConfigMapper;
import org.yefei.qa.mock.model.gen.pojo.TblSystemConfig;

/**
 * @author: yefei
 * @date: 2018/9/25 21:21
 */

@Component("systemConfigDao")
public class SystemConfigDaoImpl implements ISystemConfigDao {

    @Autowired
    TblSystemConfigMapper tblSystemConfigMapper;

    @Override
    public TblSystemConfig getSystemConfigByID(int configID){
        return tblSystemConfigMapper.selectByPrimaryKey(configID);
    }

}
