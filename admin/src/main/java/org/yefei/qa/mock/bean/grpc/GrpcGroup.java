package org.yefei.qa.mock.bean.grpc;

import lombok.Data;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yefei
 * @date: 2019/12/18
 */
@Data
public class GrpcGroup {
    private TblGrpcRequestGroup tblGrpcRequestGroup;
    private List<GrpcRequestMethod> grpcRequestMethodList = new ArrayList<>();
}
