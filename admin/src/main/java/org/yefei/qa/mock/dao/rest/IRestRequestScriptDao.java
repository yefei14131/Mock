package org.yefei.qa.mock.dao.rest;

import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestScript;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:11
 */
public interface IRestRequestScriptDao {
    int insertRestScript(TblRestRequestScript tblRestRequestScript);

    int deleteRestScript(int scriptID);

    int updateRestScript(TblRestRequestScript tblRestRequestScript);

    List<TblRestRequestScript> queryScriptList(int groupID, String path);

    List<TblRestRequestScript> queryScriptList(int groupID);

    long countRestScript(int groupID, String path);

    int updateGroupCode(int groupID, String groupCode);

    int updatePath(int groupID, String newPath, String orgPath);

    TblRestRequestScript getScript(int scriptID);

    int clone(int groupID, String sourcePath, String destPath, List<BeanScanner.BeanField> mappingScriptFields);

    int deleteUnRelationMappingScript();
}
