package org.yefei.qa.mock.mapper.dao;

import org.apache.ibatis.annotations.Delete;

import java.util.HashMap;

/**
 * @author yefei
 * @date: 2019/11/26
 */
public interface InnerTblRestMappingScriptMapper {

    int cloneScript(HashMap params);

    @Delete("DELETE FROM tbl_rest_request_script WHERE groupID NOT IN ( SELECT groupID FROM tbl_rest_request_mapping )")
    int deleteUnRelationMappingScript();
}
