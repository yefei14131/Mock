package org.yefei.qa.mock.mapper.pojo;

import lombok.Data;
import org.yefei.qa.mock.model.gen.pojo.TblRequestLogExample;

/**
 * @author yefei
 * @date: 2019/12/2
 */
@Data
public class InnerTblRequestLogExample extends TblRequestLogExample {
    protected int offset = 0;
    protected int limit = 10;
}
