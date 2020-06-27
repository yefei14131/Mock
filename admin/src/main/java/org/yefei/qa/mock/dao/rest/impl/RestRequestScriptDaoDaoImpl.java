package org.yefei.qa.mock.dao.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.dao.rest.IRestRequestScriptDao;
import org.yefei.qa.mock.mapper.dao.InnerTblRestMappingScriptMapper;
import org.yefei.qa.mock.model.gen.dao.TblRestRequestScriptMapper;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestScript;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestScriptExample;

import java.util.HashMap;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:11
 */
@Service
public class RestRequestScriptDaoDaoImpl implements IRestRequestScriptDao {

    @Autowired
    private TblRestRequestScriptMapper tblRestRequestScriptMapper;

    @Autowired
    private InnerTblRestMappingScriptMapper innerTblRestMappingScriptMapper;

    @Override
    public int insertRestScript(TblRestRequestScript tblRestRequestScript){
        return tblRestRequestScriptMapper.insert(tblRestRequestScript);
    }

    @Override
    public int deleteRestScript(int scriptID){
        return tblRestRequestScriptMapper.deleteByPrimaryKey(scriptID);
    }

    @Override
    public int updateRestScript(TblRestRequestScript tblRestRequestScript){
        return tblRestRequestScriptMapper.updateByPrimaryKeyWithBLOBs(tblRestRequestScript);
    }

    @Override
    public List<TblRestRequestScript> queryScriptList(int groupID, String path){
        TblRestRequestScriptExample example = buildExample(groupID, path);
        return tblRestRequestScriptMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<TblRestRequestScript> queryScriptList(int groupID){

        TblRestRequestScriptExample example = new TblRestRequestScriptExample();
        TblRestRequestScriptExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIDEqualTo(groupID);

        example.setOrderByClause("sortIndex asc, updateTime desc");

        return tblRestRequestScriptMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public long countRestScript(int groupID, String path){
        TblRestRequestScriptExample example = buildExample(groupID, path);
        return tblRestRequestScriptMapper.countByExample(example);
    }

    private TblRestRequestScriptExample buildExample(int groupID, String path){
        TblRestRequestScriptExample example = new TblRestRequestScriptExample();
        TblRestRequestScriptExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIDEqualTo(groupID).andPathEqualTo(path);

        example.setOrderByClause("sortIndex asc, updateTime desc");
        return example;
    }


    @Override
    public int updateGroupCode(int groupID, String groupCode){
        TblRestRequestScript tblRestRequestScript = new TblRestRequestScript();
        tblRestRequestScript.setGroupCode(groupCode);

        TblRestRequestScriptExample example = new TblRestRequestScriptExample();
        TblRestRequestScriptExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIDEqualTo(groupID);

        return tblRestRequestScriptMapper.updateByExampleSelective(tblRestRequestScript, example);
    }

    @Override
    public int updatePath(int groupID, String newPath, String orgPath){
        TblRestRequestScript tblRestRequestScript = new TblRestRequestScript();
        tblRestRequestScript.setPath(newPath);

        TblRestRequestScriptExample example = new TblRestRequestScriptExample();
        TblRestRequestScriptExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIDEqualTo(groupID).andPathEqualTo(orgPath);

        return tblRestRequestScriptMapper.updateByExampleSelective(tblRestRequestScript, example);
    }

    @Override
    public TblRestRequestScript getScript(int scriptID){
        return tblRestRequestScriptMapper.selectByPrimaryKey(scriptID);
    }

    @Override
    public int clone(int groupID, String sourcePath, String destPath, List<BeanScanner.BeanField> mappingScriptFields){

        HashMap params = new HashMap<>();
        params.put("groupID", groupID);
        params.put("sourcePath", sourcePath);
        params.put("destPath", destPath);
        params.put("fieldList", mappingScriptFields);

        return innerTblRestMappingScriptMapper.cloneScript(params);
    }

    /**
     * 删除没有关联group的script
     * @return
     */
    @Override
    public int deleteUnRelationMappingScript(){
        return innerTblRestMappingScriptMapper.deleteUnRelationMappingScript();
    }
}
