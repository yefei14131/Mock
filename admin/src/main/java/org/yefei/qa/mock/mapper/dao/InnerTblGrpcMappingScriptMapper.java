package org.yefei.qa.mock.mapper.dao;

import java.util.HashMap;

/**
 * @author yefei
 * @date: 2019/11/26
 */
public interface InnerTblGrpcMappingScriptMapper {

    int cloneScript(HashMap params);

    int deleteUnRelationMappingScript();
}
