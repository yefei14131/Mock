package org.yefei.qa.mock.dao;

import org.yefei.qa.mock.model.gen.pojo.TblRequestLog;

/**
 * @author yefei
 * @date: 2019/12/2
 */
public interface IRequestLogDao {

    void insert (TblRequestLog requestLog);

    void deleteHistoryData(int beforeDay);
}
