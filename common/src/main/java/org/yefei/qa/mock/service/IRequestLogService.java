package org.yefei.qa.mock.service;

/**
 * @author yefei
 * @date: 2019/12/2
 */
public interface IRequestLogService {
    void addRequestLog(String requestPath, String traceID);

    void addRequestEvent(String traceID, String eventName, String eventDesc);

    void deleteHistoryData();
}
