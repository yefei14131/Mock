package org.yefei.qa.mock.dao;

import org.yefei.qa.mock.model.gen.pojo.TblSystemConfig;

/**
 * @author: yefei
 * @date: 2018/9/25 21:20
 */
public interface ISystemConfigDao {

    TblSystemConfig getSystemConfigByID(int configID);

}
