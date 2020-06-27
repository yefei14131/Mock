package org.yefei.qa.mock.service;

import org.yefei.qa.mock.model.gen.pojo.TblSystemConfig;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:14
 */
public interface ISystemConfigService {

    List<TblSystemConfig> queryTblSystemConfigList(int configType);

    long countTblSystemConfig(int configType);

    boolean saveSystemConfig(TblSystemConfig systemConfig);

    boolean deleteSystemConfig(int configID);
}
