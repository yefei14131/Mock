package org.yefei.qa.mock.service;

import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMapping;

import java.util.HashMap;

/**
 * @author: yefei
 * @date: 2018/9/27 21:23
 */
public interface IMappingJobService {

    void addJobs(TblRestRequestMapping tblRestRequestMapping, HashMap userDefined, HashMap params, HashMap headers, HashMap cookies);
}
