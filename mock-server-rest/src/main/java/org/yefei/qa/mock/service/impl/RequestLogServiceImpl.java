package org.yefei.qa.mock.service.impl;

import org.springframework.stereotype.Service;
import org.yefei.qa.mock.enums.ProtocolEnum;
import org.yefei.qa.mock.model.gen.pojo.TblRequestLog;

import java.util.Date;

/**
 * @author yefei
 * @date: 2019/12/2
 */
@Service
public class RequestLogServiceImpl extends RequestLogServiceBase{

    @Override
    public void addRequestLog(String requestPath, String traceID) {
        TblRequestLog requestLog = new TblRequestLog();
        requestLog.setProtocol(ProtocolEnum.HTTP.getProtocol());
        requestLog.setRequestPath(requestPath);
        requestLog.setTraceID(traceID);
        requestLog.setRequestTime(new Date());
        requestLog.setUpdateTime(new Date());

        requestLogDao.insert(requestLog);
    }

}
