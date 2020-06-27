package org.yefei.qa.mock.bean.rest;

import lombok.Data;
import org.yefei.qa.mock.model.gen.pojo.TblMappingRulesDetail;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yefei
 * @date: 2019/12/18
 */
@Data
public class RestRequestMapping {
    private TblRestRequestMapping tblRestRequestMapping;
    private List<TblMappingRulesDetail> tblMappingRulesDetails = new ArrayList<>();
    private List<MappingJob> mappingJobList = new ArrayList<>();
}
