package org.yefei.qa.mock.dao;

import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMapping;

import java.util.List;

/**
 * Created by yefei on 2018/8/7.
 */
public interface ITblRestRequestMappingDao {

    List<TblRestRequestMapping> queryTblRestRequestMapping(String groupCode, String path);

}
