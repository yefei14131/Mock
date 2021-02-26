package org.yefei.qa.mock.dao.common;

import org.yefei.qa.mock.model.gen.pojo.TblRequestLog;
import org.yefei.qa.mock.model.gen.pojo.TblRequestLogExample;

import java.util.List;

/**
 * @author yefei
 * @date: 2019/12/2
 */
public interface IInnerRequestLogDao {

    List<TblRequestLog> queryRequestLogByExample(TblRequestLogExample tblRequestLogExample);

    long countRequestLogByExample(TblRequestLogExample tblRequestLogExample);
}
