package org.yefei.qa.mock.mapper.dao;

import org.apache.ibatis.annotations.Select;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestGroup;

import java.util.List;

/**
 * @author yefei
 * @date: 2020/4/27
 */
public interface InnerTblRestGroupMapper {

    @Select("select groupCode, sourceHost FROM tbl_rest_request_group WHERE isActive = 1 ")
    List<TblRestRequestGroup> queryActiveGroupHost();
}
