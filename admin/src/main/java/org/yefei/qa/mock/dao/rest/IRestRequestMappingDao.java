package org.yefei.qa.mock.dao.rest;

import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMapping;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMappingExample;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:06
 */
public interface IRestRequestMappingDao {
    int insertRestRequestMapping(TblRestRequestMapping tblRestRequestMapping);

    int deleteRestRequestMaster(int requestID);

    int updateRestRequestMapping(TblRestRequestMapping tblRestRequestMapping);

    List<TblRestRequestMapping> queryRestRequestMappingList(int groupID);

    long countRestRequestMapping(int groupID);

    int updateGroupCode(int groupID, String groupCode);

    TblRestRequestMapping getMapping(int requestID);

    /**
     *  clone rest mapping
     * @param sourceRequestID
     * @param fieldList
     * @return  new requestID
     */
    int cloneRestMapping(int sourceRequestID, List<BeanScanner.BeanField> fieldList);

    int countMapping(TblRestRequestMappingExample example);

    int deleteUnRelationMapping();

    List<TblRestRequestMapping> queryAllActiveMapping();
}
