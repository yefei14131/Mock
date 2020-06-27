package org.yefei.qa.mock.dao;

import org.yefei.qa.mock.model.gen.pojo.TblRequestEvent;

import java.util.List;

/**
 * @author yefei
 * @date: 2019/12/2
 */
public interface IRequestEventDao {

    void insert(TblRequestEvent tblRequestEvent);

    List<TblRequestEvent> queryRequestEvent(String traceID);

    void deleteHistoryData(int beforeDay);
}
