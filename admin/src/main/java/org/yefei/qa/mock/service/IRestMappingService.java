package org.yefei.qa.mock.service;

import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMapping;
import org.yefei.qa.mock.network.mapping.RestMappingAgentSimple;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:06
 */
public interface IRestMappingService {
    List<TblRestRequestMapping> queryRestMappingList(int groupID);

    long countRestMapping(int groupID);

    int saveRestMapping(TblRestRequestMapping restRequestMapping);

    boolean deleteRestMapping(int requestID);

    TblRestRequestMapping getMapping(int requestID);

    boolean clone(int sourceRequestID);

    List<RestMappingAgentSimple> queryAllActiveMapping();
}
