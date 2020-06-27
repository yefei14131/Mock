package org.yefei.qa.mock.bean.rest;

import lombok.Data;
import org.yefei.qa.mock.model.gen.pojo.TblMappingJob;
import org.yefei.qa.mock.model.gen.pojo.TblMappingTask;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yefei
 * @date: 2019/12/18
 */
@Data
public class MappingJob {
    private TblMappingJob tblMappingJob;
    private List<TblMappingTask> tblMappingTaskList = new ArrayList<>();
}
