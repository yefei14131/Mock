package org.yefei.qa.mock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.dao.rest.IRestRequestScriptDao;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestScript;
import org.yefei.qa.mock.service.IRestRequestScriptService;

import java.util.Date;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:11
 */
@Service
public class RestRequestScriptServiceImpl implements IRestRequestScriptService {

    @Autowired
    private IRestRequestScriptDao restScriptDao;

    @Override
    public List<TblRestRequestScript> queryRestScriptList(int groupID, String path){
        return restScriptDao.queryScriptList(groupID, path);
    }

    @Override
    public long countRestScript(int groupID, String path){
        return restScriptDao.countRestScript(groupID, path);
    }

    @Override
    public boolean saveRestScript(TblRestRequestScript restRequestScript) {
        restRequestScript.setUpdateTime(new Date());
        if (restRequestScript.getScriptID() != null && restRequestScript.getScriptID() > 0){
            return restScriptDao.updateRestScript(restRequestScript) > 0 ? true : false;
        }else{
            return restScriptDao.insertRestScript(restRequestScript) > 0 ? true : false;
        }
    }

    @Override
    public boolean deleteRestScript(int scriptID){
        return restScriptDao.deleteRestScript(scriptID) > 0 ? true : false;
    }

    @Override
    public TblRestRequestScript getScript(int scriptID){
        return restScriptDao.getScript(scriptID);
    }
}
