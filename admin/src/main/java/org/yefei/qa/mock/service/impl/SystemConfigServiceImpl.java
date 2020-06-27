package org.yefei.qa.mock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.dao.common.ISystemConfigDao;
import org.yefei.qa.mock.enums.SystemConfigTypeEnum;
import org.yefei.qa.mock.model.gen.pojo.TblSystemConfig;
import org.yefei.qa.mock.service.ISystemConfigService;

import java.util.Date;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:14
 */
@Service
public class SystemConfigServiceImpl implements ISystemConfigService {

    @Autowired
    private ISystemConfigDao systemConfigDao;

    @Autowired
    private SystemNoticeService systemNoticeService;

    @Override
    public List<TblSystemConfig> queryTblSystemConfigList(int configType){
        return systemConfigDao.queryTblSystemConfigList(configType);
    }

    @Override
    public long countTblSystemConfig(int configType){
        return systemConfigDao.countTblSystemConfig(configType);
    }

    @Override
    public boolean saveSystemConfig(TblSystemConfig systemConfig) {
        systemConfig.getConfigType();
        systemConfig.setUpdateTime(new Date());
        boolean success;
        if (systemConfig.getConfigID() != null && systemConfig.getConfigID() > 0){
            success = systemConfigDao.updateSystemConfig(systemConfig) > 0 ? true : false;
        }else{
            success = systemConfigDao.insertSystemConfig(systemConfig) > 0 ? true : false;
        }

        if (success) {
            systemNotify(systemConfig);
        }
        return success;
    }

    @Override
    public boolean deleteSystemConfig(int configID){
        return systemConfigDao.deleteSystemConfig(configID) > 0 ? true : false;
    }

    private void systemNotify(TblSystemConfig systemConfig) {
        if (systemConfig.getConfigType() == SystemConfigTypeEnum.SERVER_CACHE.getTypeID()) {
            systemNoticeService.notifyForCacheTime();
            return;
        }
    }


}
