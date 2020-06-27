package org.yefei.qa.mock.dao.grpc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.dao.grpc.IGrpcRequestScriptDao;
import org.yefei.qa.mock.mapper.dao.InnerTblGrpcMappingScriptMapper;
import org.yefei.qa.mock.model.gen.dao.TblGrpcRequestScriptMapper;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestScript;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestScriptExample;

import java.util.HashMap;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:11
 */
@Service
public class GrpcRequestScriptDaoDaoImpl implements IGrpcRequestScriptDao {

    @Autowired
    private TblGrpcRequestScriptMapper tblGrpcRequestScriptMapper;

    @Autowired
    private InnerTblGrpcMappingScriptMapper innerTblGrpcMappingScriptMapper;

    @Override
    public int insertGrpcScript(TblGrpcRequestScript tblGrpcRequestScript) {
        return tblGrpcRequestScriptMapper.insert(tblGrpcRequestScript);
    }

    @Override
    public int deleteGrpcScript(int scriptID) {
        return tblGrpcRequestScriptMapper.deleteByPrimaryKey(scriptID);
    }

    @Override
    public int updateGrpcScript(TblGrpcRequestScript tblGrpcRequestScript) {
        return tblGrpcRequestScriptMapper.updateByPrimaryKeyWithBLOBs(tblGrpcRequestScript);
    }

    @Override
    public List<TblGrpcRequestScript> queryScriptList(String serviceName, String methodName) {
        TblGrpcRequestScriptExample example = buildExample(serviceName, methodName);
        return tblGrpcRequestScriptMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<TblGrpcRequestScript> queryScriptList(int groupID) {

        TblGrpcRequestScriptExample example = new TblGrpcRequestScriptExample();
        TblGrpcRequestScriptExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIDEqualTo(groupID);

        example.setOrderByClause("sortIndex asc, updateTime desc");

        return tblGrpcRequestScriptMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public long countGrpcScript(String serviceName, String methodName) {
        TblGrpcRequestScriptExample example = buildExample(serviceName, methodName);
        return tblGrpcRequestScriptMapper.countByExample(example);
    }

    private TblGrpcRequestScriptExample buildExample(int groupID, String method) {
        TblGrpcRequestScriptExample example = new TblGrpcRequestScriptExample();
        TblGrpcRequestScriptExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIDEqualTo(groupID).andMethodNameEqualTo(method);
        criteria.andMethodNameEqualTo(method);

        example.setOrderByClause("sortIndex asc, updateTime desc");
        return example;
    }

    private TblGrpcRequestScriptExample buildExample(int groupID, String serviceName, String methodName) {
        TblGrpcRequestScriptExample example = new TblGrpcRequestScriptExample();
        TblGrpcRequestScriptExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIDEqualTo(groupID).andServiceNameEqualTo(serviceName).andMethodNameEqualTo(methodName);

        example.setOrderByClause("sortIndex asc, updateTime desc");
        return example;
    }

    private TblGrpcRequestScriptExample buildExample(String serviceName, String methodName) {
        TblGrpcRequestScriptExample example = new TblGrpcRequestScriptExample();
        TblGrpcRequestScriptExample.Criteria criteria = example.createCriteria();
        criteria.andServiceNameEqualTo(serviceName).andMethodNameEqualTo(methodName);

        example.setOrderByClause("sortIndex asc, updateTime desc");
        return example;
    }

//
//    @Override
//    public int updateGroupCode(int groupID, String groupCode){
//        TblGrpcRequestScript tblGrpcRequestScript = new TblGrpcRequestScript();
//        tblGrpcRequestScript.setGroupName(groupCode);
//        tblGrpcRequestScript.setServiceName(groupCode);
//
//        TblGrpcRequestScriptExample example = new TblGrpcRequestScriptExample();
//        TblGrpcRequestScriptExample.Criteria criteria = example.createCriteria();
////        criteria.andGroupIDEqualTo(groupID);
//
//        return tblGrpcRequestScriptMapper.updateByExampleSelective(tblGrpcRequestScript, example);
//    }

    @Override
    public int updateMethodName(int groupID, String newMethod, String orgMethod) {
        TblGrpcRequestScript tblGrpcRequestScript = new TblGrpcRequestScript();
        tblGrpcRequestScript.setMethodName(newMethod);

        TblGrpcRequestScriptExample example = new TblGrpcRequestScriptExample();
        TblGrpcRequestScriptExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIDEqualTo(groupID).andMethodNameEqualTo(newMethod);

        return tblGrpcRequestScriptMapper.updateByExampleSelective(tblGrpcRequestScript, example);
    }

    @Override
    public TblGrpcRequestScript getScript(int scriptID) {
        return tblGrpcRequestScriptMapper.selectByPrimaryKey(scriptID);
    }

    @Override
    public int clone(int groupID, String sourcePath, String destPath, List<BeanScanner.BeanField> mappingScriptFields) {

        HashMap params = new HashMap<>();
        params.put("groupID", groupID);
        params.put("sourcePath", sourcePath);
        params.put("destPath", destPath);
        params.put("fieldList", mappingScriptFields);

        return innerTblGrpcMappingScriptMapper.cloneScript(params);
    }

    /**
     * 删除没有关联group的script
     *
     * @return
     */
    @Override
    public int deleteUnRelationMappingScript() {
        return innerTblGrpcMappingScriptMapper.deleteUnRelationMappingScript();
    }
}
