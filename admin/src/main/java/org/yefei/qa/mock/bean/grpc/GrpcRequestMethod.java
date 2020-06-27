package org.yefei.qa.mock.bean.grpc;

import lombok.Data;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestScript;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yefei
 * @date: 2019/12/18
 */
@Data
public class GrpcRequestMethod {
    private String fullMethod;
    private String serviceName;
    private String methodName;
    private List<TblGrpcRequestScript> tblRestRequestScriptList = new ArrayList<>();
    private List<GrpcRequestMapping> grpcRequestMappingList = new ArrayList<>();
}
