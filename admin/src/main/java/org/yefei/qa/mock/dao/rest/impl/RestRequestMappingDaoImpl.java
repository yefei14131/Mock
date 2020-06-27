package org.yefei.qa.mock.dao.rest.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.dao.rest.IRestRequestMappingDao;
import org.yefei.qa.mock.mapper.dao.InnerTblRestRequestMappingMapper;
import org.yefei.qa.mock.model.gen.dao.TblRestRequestMappingMapper;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMapping;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMappingExample;

import java.util.HashMap;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:07
 */
@Service
public class RestRequestMappingDaoImpl implements IRestRequestMappingDao {

    @Autowired
    private TblRestRequestMappingMapper tblRestRequestMappingMapper;

    @Autowired
    private InnerTblRestRequestMappingMapper innerTblRestRequestMappingMapper;


    @Override
    public int insertRestRequestMapping(TblRestRequestMapping tblRestRequestMapping){
        return tblRestRequestMappingMapper.insert(tblRestRequestMapping);
    }

    @Override
    public int deleteRestRequestMaster(int requestID){
        return tblRestRequestMappingMapper.deleteByPrimaryKey(requestID);
    }

    @Override
    public int updateRestRequestMapping(TblRestRequestMapping tblRestRequestMapping){
        return tblRestRequestMappingMapper.updateByPrimaryKeyWithBLOBs(tblRestRequestMapping);
    }

    @Override
    public List<TblRestRequestMapping> queryRestRequestMappingList(int groupID){
        TblRestRequestMappingExample example = new TblRestRequestMappingExample();
        TblRestRequestMappingExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIDEqualTo(groupID);

        example.setOrderByClause("path asc, sortIndex desc, updateTime desc");

        return tblRestRequestMappingMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public long countRestRequestMapping(int groupID){
        TblRestRequestMappingExample example = new TblRestRequestMappingExample();
        TblRestRequestMappingExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIDEqualTo(groupID);

        example.setOrderByClause("path asc, sortIndex asc, updateTime desc");

        return tblRestRequestMappingMapper.countByExample(example);
    }

    @Override
    public int updateGroupCode(int groupID, String groupCode){
        TblRestRequestMapping tblRestRequestMapping = new TblRestRequestMapping();
        tblRestRequestMapping.setGroupCode(groupCode);

        TblRestRequestMappingExample example = new TblRestRequestMappingExample();
        TblRestRequestMappingExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIDEqualTo(groupID);


        return tblRestRequestMappingMapper.updateByExampleSelective(tblRestRequestMapping, example);
    }

    @Override
    public TblRestRequestMapping getMapping(int requestID){
        return tblRestRequestMappingMapper.selectByPrimaryKey(requestID);
    }

    @Override
    public int cloneRestMapping(int sourceRequestID, List<BeanScanner.BeanField> fieldList) {
        HashMap params = new HashMap<>();
        params.put("sourceRequestID", sourceRequestID);
        params.put("fieldList", fieldList);
        params.put("requestID", 0);

        int effect = innerTblRestRequestMappingMapper.cloneMapping(params);
        return effect == 0 ? 0 : (int) params.get("requestID");
    }

    @Override
    public int countMapping(TblRestRequestMappingExample example) {
        return (int)tblRestRequestMappingMapper.countByExample(example);
    }

    /**
     * 删除没有关联group的mapping
     * @return
     */
    @Override
    public int deleteUnRelationMapping(){
        return innerTblRestRequestMappingMapper.deleteUnRelationMapping();
    }

    @Override
    public List<TblRestRequestMapping> queryAllActiveMapping() {
        return innerTblRestRequestMappingMapper.queryAllActiveMapping();
    }
}
