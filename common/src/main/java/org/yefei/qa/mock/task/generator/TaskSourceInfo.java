package org.yefei.qa.mock.task.generator;

import lombok.Data;
import org.yefei.qa.mock.enums.SourceServerEnum;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestMapping;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMapping;

/**
 * @author yefei
 * @date: 2020/6/27
 */
@Data
public class TaskSourceInfo {
    private SourceServerEnum sourceServer;
    private TblGrpcRequestMapping grpcRequestMapping;
    private TblRestRequestMapping restRequestMapping;
}
