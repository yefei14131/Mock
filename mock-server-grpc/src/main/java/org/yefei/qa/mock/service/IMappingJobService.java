package org.yefei.qa.mock.service;

import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestMapping;

import java.util.HashMap;

/**
 * @author: yefei
 * @date: 2018/9/27 21:23
 */
public interface IMappingJobService {

    void addJobs(TblGrpcRequestMapping tblGrpcRequestMapping, HashMap userDefined, HashMap params);
}
