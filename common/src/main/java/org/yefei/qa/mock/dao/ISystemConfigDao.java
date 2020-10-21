package org.yefei.qa.mock.dao;

import org.yefei.qa.mock.model.gen.pojo.TblSystemConfig;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:14
 */
public interface ISystemConfigDao {
    int insertSystemConfig(TblSystemConfig tblSystemConfig);

    List<TblSystemConfig> queryTblSystemConfigList(int configType);

    long countTblSystemConfig(int configType);

    int deleteSystemConfig(int configID);

    int updateSystemConfig(TblSystemConfig tblSystemConfig);

    TblSystemConfig queryTblSystemConfig(int configType);

    TblSystemConfig getSystemConfigByID(int configID);
}
