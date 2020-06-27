package org.yefei.qa.mock.mapper.pojo;

import lombok.Data;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcInterfaceJarExample;

/**
 * @author yefei
 * @date: 2020/4/1
 */
@Data
public class InnerTblGrpcInterfaceJarExample extends TblGrpcInterfaceJarExample {
    protected int offset = 0;
    protected int limit = 10;
}
