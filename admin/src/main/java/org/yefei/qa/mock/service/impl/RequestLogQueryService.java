package org.yefei.qa.mock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.yefei.qa.mock.dao.IRequestEventDao;
import org.yefei.qa.mock.dao.common.IInnerRequestLogDao;
import org.yefei.qa.mock.mapper.pojo.InnerTblRequestLogExample;
import org.yefei.qa.mock.model.gen.pojo.TblRequestEvent;
import org.yefei.qa.mock.model.gen.pojo.TblRequestLog;
import org.yefei.qa.mock.model.gen.pojo.TblRequestLogExample;
import org.yefei.qa.mock.service.IRequestLogQueryService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yefei
 * @date: 2019/12/2
 */
@Service
public class RequestLogQueryService implements IRequestLogQueryService {

    @Autowired
    protected IInnerRequestLogDao requestLogDao;

    @Autowired
    protected IRequestEventDao requestEventDao;


    @Override
    public List<TblRequestEvent> queryRequestEventList(String traceID) {
        return requestEventDao.queryRequestEvent(traceID);
    }

    @Override
    public List<TblRequestLog> queryRequestLog(int page, int pageSize) {
        InnerTblRequestLogExample tblRequestLogExample = this.genTblRequestLogExample(page, pageSize);
        return requestLogDao.queryRequestLogByExample(tblRequestLogExample);
    }

    @Override
    public List<TblRequestLog> queryRequestLogByKeywords(String keywords, int page, int pageSize) {
        List<String> traceIds = requestEventDao.queryTraceIdsByKeywords(keywords, page, pageSize);
        if (CollectionUtils.isEmpty(traceIds)) {
            return new ArrayList<>();
        }
        // traceId已确定，page永远都是1
        TblRequestLogExample tblRequestLogExample = this.genTblRequestLogExample(1, pageSize);
        tblRequestLogExample.createCriteria().andTraceIDIn(traceIds);
        return requestLogDao.queryRequestLogByExample(tblRequestLogExample);
    }


    private InnerTblRequestLogExample genTblRequestLogExample(int page, int pageSize){
        InnerTblRequestLogExample tblRequestLogExample = new InnerTblRequestLogExample();
        if (pageSize == 0) {
            pageSize = 20;
        }

        tblRequestLogExample.setLimit(pageSize);
        tblRequestLogExample.setOffset((page - 1) * pageSize);
        tblRequestLogExample.setOrderByClause("requestTime desc");

        return tblRequestLogExample;
    }

    @Override
    public long countRequestLog() {
        InnerTblRequestLogExample tblRequestLogExample = this.genTblRequestLogExample(0,0);
        return requestLogDao.countRequestLogByExample(tblRequestLogExample);
    }

    @Override
    public long countRequestLogByKeywords(String keywords) {
        return requestEventDao.countByKeywords(keywords);
    }

}
