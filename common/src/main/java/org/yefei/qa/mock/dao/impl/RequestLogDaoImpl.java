package org.yefei.qa.mock.dao.impl;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.dao.IRequestLogDao;
import org.yefei.qa.mock.model.gen.dao.TblRequestLogMapper;
import org.yefei.qa.mock.model.gen.pojo.TblRequestLog;
import org.yefei.qa.mock.model.gen.pojo.TblRequestLogExample;

import java.util.Date;

/**
 * @author yefei
 * @date: 2019/12/2
 */
@Service
public class RequestLogDaoImpl implements IRequestLogDao {

    @Autowired
    private TblRequestLogMapper tblRequestLogMapper;

    @Override
    public void insert(TblRequestLog requestLog) {
        tblRequestLogMapper.insert(requestLog);
    }

    @Override
    public void deleteHistoryData(int beforeDay) {
        TblRequestLogExample example = new TblRequestLogExample();
        example.createCriteria().andUpdateTimeLessThan(DateUtils.addDays(new Date(), beforeDay));
        tblRequestLogMapper.deleteByExample(example);
    }
}
