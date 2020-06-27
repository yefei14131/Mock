package org.yefei.qa.mock.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.dao.IMappingTaskDao;
import org.yefei.qa.mock.model.gen.dao.TblMappingTaskMapper;
import org.yefei.qa.mock.model.gen.pojo.TblMappingTask;
import org.yefei.qa.mock.model.gen.pojo.TblMappingTaskExample;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/9/27 20:53
 */
@Service
public class MappingTaskDaoImpl implements IMappingTaskDao {

    @Autowired
    private TblMappingTaskMapper tblMappingTaskMapper;

    @Override
    public List<TblMappingTask> queryMappingTasks(int jobID) {
        TblMappingTaskExample example = new TblMappingTaskExample();
        TblMappingTaskExample.Criteria criteria = example.createCriteria();
        criteria.andJobIDEqualTo(jobID).andIsActiveEqualTo(true);

        example.setOrderByClause("sortIndex desc");
        return tblMappingTaskMapper.selectByExampleWithBLOBs(example);
    }
}
