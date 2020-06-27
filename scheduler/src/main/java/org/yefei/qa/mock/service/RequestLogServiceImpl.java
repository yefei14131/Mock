package org.yefei.qa.mock.service;

import org.springframework.stereotype.Service;
import org.yefei.qa.mock.service.impl.RequestLogServiceBase;

/**
 * @author yefei
 * @date: 2019/12/2
 */
@Service
public class RequestLogServiceImpl extends RequestLogServiceBase {

    @Override
    public void addRequestLog(String requestPath, String traceID) {

    }

}
