package org.yefei.qa.mock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.yefei.qa.mock.dao.IRequestEventDao;
import org.yefei.qa.mock.dao.IRequestLogDao;
import org.yefei.qa.mock.model.gen.pojo.TblRequestEvent;
import org.yefei.qa.mock.service.IRequestLogService;

import java.util.Date;

/**
 * @author yefei
 * @date: 2019/12/2
 */
public abstract class RequestLogServiceBase implements IRequestLogService {

    @Autowired
    protected IRequestLogDao requestLogDao;

    @Autowired
    protected IRequestEventDao requestEventDao;


    @Override
    public abstract void addRequestLog(String requestPath, String traceID);

    @Override
    public void addRequestEvent(String traceID, String eventName, String eventDesc) {
        TblRequestEvent tblRequestEvent = new TblRequestEvent();
        tblRequestEvent.setTraceID(traceID);
        tblRequestEvent.setEventName(eventName);
        tblRequestEvent.setEventDesc(eventDesc);
        tblRequestEvent.setUpdateTime(new Date());
        requestEventDao.insert(tblRequestEvent);
    }

    @Override
    public void deleteHistoryData() {
        requestEventDao.deleteHistoryData(-7);
        requestLogDao.deleteHistoryData(-7);
    }
}
