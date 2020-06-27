package org.yefei.qa.mock.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.dao.ITblRestRequestScriptDao;
import org.yefei.qa.mock.model.gen.dao.TblRestRequestScriptMapper;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestScript;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestScriptExample;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/8/20 19:35
 */
@Service
@Slf4j
public class TblRestRequestScriptDaoImpl implements ITblRestRequestScriptDao {

    @Autowired
    private TblRestRequestScriptMapper tblRestRequestScriptMapper;

    @Override
    public List<TblRestRequestScript> queryScripts(String groupCode, String path){

        TblRestRequestScriptExample example = new TblRestRequestScriptExample();
        TblRestRequestScriptExample.Criteria criteria = example.createCriteria();
        criteria.andGroupCodeEqualTo(groupCode).andPathEqualTo(path).andIsActiveEqualTo(true);

        example.setOrderByClause("sortIndex desc");

        return tblRestRequestScriptMapper.selectByExampleWithBLOBs(example);

    }


}
