package org.yefei.qa.mock.dao.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.dao.common.IInnerRequestLogDao;
import org.yefei.qa.mock.mapper.dao.InnerTblRequestLogMapper;
import org.yefei.qa.mock.mapper.pojo.InnerTblRequestLogExample;
import org.yefei.qa.mock.model.gen.pojo.TblRequestLog;
import org.yefei.qa.mock.model.gen.pojo.TblRequestLogExample;

import java.util.List;

/**
 * @author yefei
 * @date: 2019/12/2
 */
@Service
public class InnerRequestLogDaoImpl implements IInnerRequestLogDao {

    @Autowired
    private InnerTblRequestLogMapper tblRequestLogMapper;

    @Override
    public List<TblRequestLog> queryRequestLogByExample(InnerTblRequestLogExample tblRequestLogExample) {
       return tblRequestLogMapper.selectByExample(tblRequestLogExample);
    }

    @Override
    public long countRequestLogByExample(TblRequestLogExample tblRequestLogExample) {
        return tblRequestLogMapper.countByExample(tblRequestLogExample);
    }

}
