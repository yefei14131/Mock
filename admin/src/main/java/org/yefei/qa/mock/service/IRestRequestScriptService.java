package org.yefei.qa.mock.service;

import org.yefei.qa.mock.model.gen.pojo.TblRestRequestScript;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:11
 */
public interface IRestRequestScriptService {
    List<TblRestRequestScript> queryRestScriptList(int groupID, String path);

    long countRestScript(int groupID, String path);

    boolean saveRestScript(TblRestRequestScript restRequestScript);

    boolean deleteRestScript(int scriptID);

    TblRestRequestScript getScript(int scriptID);
}
