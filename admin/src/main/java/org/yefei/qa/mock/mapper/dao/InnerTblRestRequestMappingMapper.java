package org.yefei.qa.mock.mapper.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMapping;

import java.util.HashMap;
import java.util.List;

/**
 * @author yefei
 * @date: 2019/11/28
 */
public interface InnerTblRestRequestMappingMapper {

    int cloneMapping(HashMap params);

    @Delete("DELETE FROM tbl_rest_request_mapping WHERE groupID NOT IN ( SELECT groupID FROM tbl_rest_request_group )")
    int deleteUnRelationMapping();

    @Select("select DISTINCT groupCode, path FROM tbl_rest_request_mapping WHERE isActive = 1 ")
    List<TblRestRequestMapping> queryAllActiveMapping();
}
