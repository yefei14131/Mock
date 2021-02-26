package org.yefei.qa.mock.service;

import org.yefei.qa.mock.model.gen.pojo.TblRequestEvent;
import org.yefei.qa.mock.model.gen.pojo.TblRequestLog;

import java.util.List;

/**
 * @author yefei
 * @date: 2019/12/2
 */
public interface IRequestLogQueryService {

    List<TblRequestEvent> queryRequestEventList(String traceID);

    List<TblRequestLog> queryRequestLog(int page, int pageSize);

    List<TblRequestLog> queryRequestLogByKeywords(String keywords, int page, int pageSize);

    long countRequestLog();

    long countRequestLogByKeywords(String keywords);
}
