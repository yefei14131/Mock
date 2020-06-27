package org.yefei.qa.mock.dao.grpc.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.dao.grpc.IGrpcRequestMappingDao;
import org.yefei.qa.mock.mapper.dao.InnerTblGrpcRequestMappingMapper;
import org.yefei.qa.mock.model.gen.dao.TblGrpcRequestMappingMapper;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestMapping;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestMappingExample;

import java.util.HashMap;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:07
 */
@Service
public class GrpcRequestMappingDaoImpl implements IGrpcRequestMappingDao {

    @Autowired
    private TblGrpcRequestMappingMapper tblGrpcRequestMappingMapper;

    @Autowired
    private InnerTblGrpcRequestMappingMapper innerTblGrpcRequestMappingMapper;


    @Override
    public int insertGrpcRequestMapping(TblGrpcRequestMapping tblGrpcRequestMapping) {
        return tblGrpcRequestMappingMapper.insert(tblGrpcRequestMapping);
    }

    @Override
    public int deleteGrpcRequestMaster(int requestID) {
        return tblGrpcRequestMappingMapper.deleteByPrimaryKey(requestID);
    }

    @Override
    public int updateGrpcRequestMapping(TblGrpcRequestMapping tblGrpcRequestMapping) {
        return tblGrpcRequestMappingMapper.updateByPrimaryKeyWithBLOBs(tblGrpcRequestMapping);
    }

    @Override
    public List<TblGrpcRequestMapping> queryGrpcRequestMappingList(int groupID) {
        TblGrpcRequestMappingExample example = new TblGrpcRequestMappingExample();
        TblGrpcRequestMappingExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIDEqualTo(groupID);

        example.setOrderByClause("methodName asc, sortIndex desc, updateTime desc");

        return tblGrpcRequestMappingMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public long countGrpcRequestMapping(int groupID) {
        TblGrpcRequestMappingExample example = new TblGrpcRequestMappingExample();
        TblGrpcRequestMappingExample.Criteria criteria = example.createCriteria();
        criteria.andGroupIDEqualTo(groupID);

        example.setOrderByClause("methodName asc, sortIndex asc, updateTime desc");

        return tblGrpcRequestMappingMapper.countByExample(example);
    }

//    @Override
//    public int updateGroupCode(int groupID, String groupCode){
//        TblGrpcRequestMapping tblGrpcRequestMapping = new TblGrpcRequestMapping();
////        tblGrpcRequestMapping.setGroupCode(groupCode);
//
//        TblGrpcRequestMappingExample example = new TblGrpcRequestMappingExample();
//        TblGrpcRequestMappingExample.Criteria criteria = example.createCriteria();
//        criteria.andGroupIDEqualTo(groupID);
//
//
//        return tblGrpcRequestMappingMapper.updateByExampleSelective(tblGrpcRequestMapping, example);
//    }

    @Override
    public TblGrpcRequestMapping getMapping(int requestID) {
        return tblGrpcRequestMappingMapper.selectByPrimaryKey(requestID);
    }

    @Override
    public int cloneGrpcMapping(int sourceRequestID, List<BeanScanner.BeanField> fieldList) {
        HashMap params = new HashMap<>();
        params.put("sourceRequestID", sourceRequestID);
        params.put("fieldList", fieldList);
        params.put("requestID", 0);

        int effect = innerTblGrpcRequestMappingMapper.cloneMapping(params);
        return effect == 0 ? 0 : (int) params.get("requestID");
    }

    @Override
    public int countMapping(TblGrpcRequestMappingExample example) {
        return (int) tblGrpcRequestMappingMapper.countByExample(example);
    }

    /**
     * 删除没有关联group的mapping
     *
     * @return
     */
    @Override
    public int deleteUnRelationMapping() {
        return innerTblGrpcRequestMappingMapper.deleteUnRelationMapping();
    }

    @Override
    public List<TblGrpcRequestMapping> queryAllActiveMethod() {
        return innerTblGrpcRequestMappingMapper.queryAllActiveMethod();
    }
}
