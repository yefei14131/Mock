package org.yefei.qa.mock.dao.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.dao.common.IMappingRulesDetailDao;
import org.yefei.qa.mock.enums.ProtocolEnum;
import org.yefei.qa.mock.mapper.dao.InnerTblMappingRulesDetailMapper;
import org.yefei.qa.mock.model.gen.dao.TblMappingRulesDetailMapper;
import org.yefei.qa.mock.model.gen.pojo.TblMappingRulesDetail;
import org.yefei.qa.mock.model.gen.pojo.TblMappingRulesDetailExample;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:08
 */
@Service
public class MappingRulesDetailDaoImpl implements IMappingRulesDetailDao {

    @Autowired
    private TblMappingRulesDetailMapper tblMappingRulesDetailMapper;

    @Autowired
    private InnerTblMappingRulesDetailMapper innerTblMappingRulesDetailMapper;

    @Override
    public int insertMappingRulesDetail(TblMappingRulesDetail tblMappingRulesDetail){
        return tblMappingRulesDetailMapper.insert(tblMappingRulesDetail);
    }

    @Override
    public int deleteMappingRulesDetail(int rulesDetailID){
        return tblMappingRulesDetailMapper.deleteByPrimaryKey(rulesDetailID);
    }

    @Override
    public List<TblMappingRulesDetail> queryMappingRulesDetail(int requestID, String protocol){
        TblMappingRulesDetailExample example = buildExample(requestID, protocol);
        return tblMappingRulesDetailMapper.selectByExample(example);
    }

    @Override
    public long countMappingRulesDetail(int requestID, String protocol){
        TblMappingRulesDetailExample example = buildExample(requestID, protocol);
        return tblMappingRulesDetailMapper.countByExample(example);
    }

    public TblMappingRulesDetailExample buildExample(int requestID, String protocol){
        TblMappingRulesDetailExample example = new TblMappingRulesDetailExample();
        TblMappingRulesDetailExample.Criteria criteria = example.createCriteria();
        criteria.andProtocolEqualTo(protocol).andRequestIDEqualTo(requestID);

        example.setOrderByClause("sortIndex asc, updateTime asc");
        return example;
    }

    @Override
    public int updateMappingRulesDetail(TblMappingRulesDetail tblMappingRulesDetail){
        return tblMappingRulesDetailMapper.updateByPrimaryKeySelective(tblMappingRulesDetail);
    }

    @Override
    public int cloneMappingRules(int sourceRequestID, String protocol, int destRequestID, List<BeanScanner.BeanField> fieldList) {

        return innerTblMappingRulesDetailMapper.cloneMappingRules(sourceRequestID, protocol, destRequestID, fieldList);
    }

    /**
     * 删除没有关联rest request的RulesDetail
     * @return
     */
    @Override
    public int deleteUnRelationRestMappingRulesDetail(){
        return innerTblMappingRulesDetailMapper.deleteUnRelationRestMappingRulesDetail(ProtocolEnum.HTTP.getProtocol());
    }

    /**
     * 删除没有关联grpc request的RulesDetail
     *
     * @return
     */
    @Override
    public int deleteUnRelationGrpcMappingRulesDetail() {
        return innerTblMappingRulesDetailMapper.deleteUnRelationRestMappingRulesDetail(ProtocolEnum.GRPC.getProtocol());
    }

}
