package org.yefei.qa.mock.dao.impl;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.dao.IRequestEventDao;
import org.yefei.qa.mock.model.gen.dao.CommonInnerTblRequestEventMapper;
import org.yefei.qa.mock.model.gen.dao.TblRequestEventMapper;
import org.yefei.qa.mock.model.gen.pojo.TblRequestEvent;
import org.yefei.qa.mock.model.gen.pojo.TblRequestEventExample;

import java.util.Date;
import java.util.List;

/**
 * @author yefei
 * @date: 2019/12/2
 */
@Service
public class RequestEventDaoImpl implements IRequestEventDao {

    @Autowired
    private TblRequestEventMapper tblRequestEventMapper;

    @Autowired
    private CommonInnerTblRequestEventMapper commonInnerTblRequestEventMapper;

    @Override
    public void insert(TblRequestEvent requestEvent) {
        tblRequestEventMapper.insert(requestEvent);
    }

    @Override
    public List<TblRequestEvent> queryRequestEvent(String traceID) {
        TblRequestEventExample example = new TblRequestEventExample();
        example.createCriteria().andTraceIDEqualTo(traceID);
        return tblRequestEventMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public void deleteHistoryData(int beforeDay) {
        TblRequestEventExample example = new TblRequestEventExample();
        example.createCriteria().andUpdateTimeLessThan(DateUtils.addDays(new Date(), beforeDay));
        tblRequestEventMapper.deleteByExample(example);
    }

    @Override
    public List<String> queryTraceIdsByKeywords(String keywords, int page, int pageSize) {
        int offset = pageSize * (page - 1);
        return commonInnerTblRequestEventMapper.queryTraceIdsByKeywords(keywords, offset, pageSize);
    }

    @Override
    public long countByKeywords(String keywords) {
        return commonInnerTblRequestEventMapper.countByKeywords(keywords);
    }
}
