package org.yefei.qa.mock.dao.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.dao.common.IMappingJobDao;
import org.yefei.qa.mock.enums.ProtocolEnum;
import org.yefei.qa.mock.mapper.dao.InnerTblMappingJobMapper;
import org.yefei.qa.mock.model.gen.dao.TblMappingJobMapper;
import org.yefei.qa.mock.model.gen.pojo.TblMappingJob;
import org.yefei.qa.mock.model.gen.pojo.TblMappingJobExample;

import java.util.HashMap;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:16
 */
@Service
public class MappingJobDaoImpl implements IMappingJobDao {

    @Autowired
    private TblMappingJobMapper tblMappingJobMapper;

    @Autowired
    private InnerTblMappingJobMapper innerTblMappingJobMapper;

    @Override
    public int insertMappingJob(TblMappingJob tblMappingJob){
        return tblMappingJobMapper.insert(tblMappingJob);
    }

    @Override
    public int deleteMappingJob(int jobID){
        return tblMappingJobMapper.deleteByPrimaryKey(jobID);
    }

    @Override
    public List<TblMappingJob> queryTblMappingJobList(int requestID, String protocol){
        TblMappingJobExample example = buildExample(requestID, protocol);
        return tblMappingJobMapper.selectByExample(example);
    }

    @Override
    public long countMappingJob(int requestID, String protocol){
        TblMappingJobExample example = buildExample(requestID, protocol);
        return tblMappingJobMapper.countByExample(example);
    }

    private TblMappingJobExample buildExample(int requestID, String protocol){
        TblMappingJobExample example = new TblMappingJobExample();
        TblMappingJobExample.Criteria criteria = example.createCriteria();
        criteria.andProtocolEqualTo(protocol).andRequestIDEqualTo(requestID);

        example.setOrderByClause("delay desc, isActive desc, sortIndex asc, updateTime desc");
        return example;
    }

    @Override
    public int updateMappingJob(TblMappingJob tblMappingJob){
        return tblMappingJobMapper.updateByPrimaryKeySelective(tblMappingJob);
    }

    @Override
    public int cloneJob(int sourceRequestID, String protocol, int destRequestID, List<BeanScanner.BeanField> fieldList) {
        HashMap params = new HashMap<>();
        params.put("sourceRequestID", sourceRequestID);
        params.put("protocol", protocol);
        params.put("destRequestID", destRequestID);
        params.put("fieldList", fieldList);
        params.put("jobID", 0);

        int effect = innerTblMappingJobMapper.cloneJob(params);

        return effect == 0 ? 0 : (int) params.get("jobID");
    }


    @Override
    public int cloneJob(int sourceJobID, int destRequestID, List<BeanScanner.BeanField> fieldList) {
        HashMap params = new HashMap<>();
        params.put("sourceJobID", sourceJobID);
        params.put("destRequestID", destRequestID);
        params.put("fieldList", fieldList);
        params.put("jobID", 0);

        int effect = innerTblMappingJobMapper.cloneJob(params);

        return effect == 0 ? 0 : (int) params.get("jobID");
    }


    /**
     * 删除没有关联rest request的MappingJob
     * @return
     */
    @Override
    public int deleteUnRelationRestMappingJob(){
        return innerTblMappingJobMapper.deleteUnRelationRestMappingJob(ProtocolEnum.HTTP.getProtocol());
    }

    /**
     * 删除没有关联grpc request的MappingJob
     *
     * @return
     */
    @Override
    public int deleteUnRelationGrpcMappingJob() {
        return innerTblMappingJobMapper.deleteUnRelationRestMappingJob(ProtocolEnum.GRPC.getProtocol());
    }

}
