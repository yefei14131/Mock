package org.yefei.qa.mock.service;

import org.yefei.qa.mock.bean.rest.RestGroup;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestGroup;

import java.io.IOException;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:03
 */
public interface IRestGroupService {

    void importRestGroupData(List<RestGroup> restGroupList);

    List<RestGroup> makeExportRestGroup(String groupIDs);

    List<TblRestRequestGroup> queryRestGroupList();

    long countRestGroup();

    List<TblRestRequestGroup> queryRestGroupByCode(String groupCode);

    long countRestGroupByCode(String groupCode);

    boolean saveRestGroup(TblRestRequestGroup restRequestGroup) throws IOException;

    boolean deleteRestGroup(int groupID);

    TblRestRequestGroup getGroup(int groupID);
}
