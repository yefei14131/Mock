package org.yefei.qa.mock.dao.common.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.dao.common.IMappingTaskDao;
import org.yefei.qa.mock.mapper.dao.InnerTblMappingTaskMapper;
import org.yefei.qa.mock.model.gen.dao.TblMappingTaskMapper;
import org.yefei.qa.mock.model.gen.pojo.TblMappingTask;
import org.yefei.qa.mock.model.gen.pojo.TblMappingTaskExample;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:16
 */
@Service
public class MappingTaskDaoImpl implements IMappingTaskDao {

    @Autowired
    private TblMappingTaskMapper tblMappingTaskMapper;

    @Autowired
    private InnerTblMappingTaskMapper innerTblMappingTaskMapper;

    @Override
    public int insertMappingTask(TblMappingTask tblMappingTask){
        return tblMappingTaskMapper.insert(tblMappingTask);
    }

    @Override
    public int deleteMappingTask(int taskID){
        return tblMappingTaskMapper.deleteByPrimaryKey(taskID);
    }

    @Override
    public int updateMappingTask(TblMappingTask tblMappingTask){
        return tblMappingTaskMapper.updateByPrimaryKeyWithBLOBs(tblMappingTask);
    }

    @Override
    public List<TblMappingTask> queryMappingTaskList(int jobID){
        TblMappingTaskExample example = buildExample(jobID);
        return tblMappingTaskMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public long countMappingTask(int jobID){
        TblMappingTaskExample example = buildExample(jobID);
        return tblMappingTaskMapper.countByExample(example);
    }

    public TblMappingTaskExample buildExample(int jobID){
        TblMappingTaskExample example = new TblMappingTaskExample();
        TblMappingTaskExample.Criteria criteria = example.createCriteria();
        criteria.andJobIDEqualTo(jobID);

        example.setOrderByClause("isActive desc, sortIndex asc, updateTime desc");

        return example;
    }

    @Override
    public int cloneTask(int sourceJobID, int destJobID, List<BeanScanner.BeanField> fieldList) {

        return innerTblMappingTaskMapper.cloneTask(sourceJobID, destJobID, fieldList);
    }


    /**
     * 删除没有关联 MappingJob 的MappingTask
     * @return
     */
    @Override
    public int deleteUnRelationMappingTask(){
        return innerTblMappingTaskMapper.deleteUnRelationMappingTask();
    }

}
