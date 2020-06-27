package org.yefei.qa.mock.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.dao.ITblRestRequestMappingDao;
import org.yefei.qa.mock.model.gen.dao.TblRestRequestMappingMapper;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMapping;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMappingExample;

import java.util.List;

/**
 * Created by yefei on 2018/8/7.
 */
@Service
@Slf4j
public class TblRestRequestMappingDaoImpl implements ITblRestRequestMappingDao {

    @Autowired
    private TblRestRequestMappingMapper tblRestRequestMappingMapper;



    @Override
    public List<TblRestRequestMapping> queryTblRestRequestMapping(String groupCode, String path) {
        TblRestRequestMappingExample example = new TblRestRequestMappingExample();

        TblRestRequestMappingExample.Criteria criteria = example.createCriteria();
        criteria.andGroupCodeEqualTo(groupCode)
                .andPathEqualTo(path)
                .andIsActiveEqualTo(true);

        example.setOrderByClause("sortIndex desc, updateTime asc");
        List<TblRestRequestMapping> tblRestRequestMappings = tblRestRequestMappingMapper.selectByExampleWithBLOBs(example);

        return tblRestRequestMappings;
    }


}
