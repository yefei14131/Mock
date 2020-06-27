package org.yefei.qa.mock.bean.grpc;

import lombok.Data;
import org.yefei.qa.mock.bean.rest.MappingJob;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestMapping;
import org.yefei.qa.mock.model.gen.pojo.TblMappingRulesDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yefei
 * @date: 2019/12/18
 */
@Data
public class GrpcRequestMapping {
    private TblGrpcRequestMapping tblGrpcRequestMapping;
    private List<TblMappingRulesDetail> tblMappingRulesDetails = new ArrayList<>();
    private List<MappingJob> mappingJobList = new ArrayList<>();
}
