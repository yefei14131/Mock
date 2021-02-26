package org.yefei.qa.mock.model.gen.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommonInnerTblRequestEventMapper {

    @Select("select traceID, updateTime from tbl_request_event where eventDesc like concat('%', #{keywords}, '%') group by traceID order by updateTime desc limit #{offset}, #{size}; ")
    List<String> queryTraceIdsByKeywords(@Param("keywords") String keywords, @Param("offset") int offset, @Param("size") int size);


    @Select("select count(1) from (select traceID from tbl_request_event where eventDesc like concat('%', #{keywords}, '%') group by traceID ) as t")
    int countByKeywords(@Param("keywords") String keywords);

}
