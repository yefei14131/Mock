package org.yefei.qa.mock.dao.rest;

import org.yefei.qa.mock.model.gen.pojo.TblRestRequestGroup;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:03
 */
public interface IRestGroupDao {
    int insertRestRequestGroup(TblRestRequestGroup tblRestRequestGroup);

    int deleteRestRequestGroup(int groupID);

    int updateRestRequestGroup(TblRestRequestGroup tblRestRequestGroup);

    List<TblRestRequestGroup> queryRestRequestGroupList();

    long countRestRequestGroup();

    List<TblRestRequestGroup> queryRestGroupByCode(String groupCode);

    long countRestGroupByCode(String groupCode);

    TblRestRequestGroup getGroup(int groupID);

    List<TblRestRequestGroup> queryRestGroupByIDS(List<Integer> groupIDS);

    List<TblRestRequestGroup> queryActiveGroupHost();
}
