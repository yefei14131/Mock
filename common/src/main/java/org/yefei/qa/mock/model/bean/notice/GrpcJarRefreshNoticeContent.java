package org.yefei.qa.mock.model.bean.notice;

import lombok.Data;
import org.yefei.qa.mock.model.bean.AbstractSerializable;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcInterfaceJar;

/**
 * @author yefei
 * @date: 2020/4/10
 */
@Data
public class GrpcJarRefreshNoticeContent extends AbstractSerializable {
    private boolean isAll;
    private TblGrpcInterfaceJar jar;
}
