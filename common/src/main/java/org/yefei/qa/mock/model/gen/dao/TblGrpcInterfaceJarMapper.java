package org.yefei.qa.mock.model.gen.dao;

import org.apache.ibatis.annotations.Param;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcInterfaceJar;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcInterfaceJarExample;

import java.util.List;

public interface TblGrpcInterfaceJarMapper {
    long countByExample(TblGrpcInterfaceJarExample example);

    int deleteByExample(TblGrpcInterfaceJarExample example);

    int deleteByPrimaryKey(Integer itemID);

    int insert(TblGrpcInterfaceJar record);

    int insertSelective(TblGrpcInterfaceJar record);

    List<TblGrpcInterfaceJar> selectByExample(TblGrpcInterfaceJarExample example);

    TblGrpcInterfaceJar selectByPrimaryKey(Integer itemID);

    int updateByExampleSelective(@Param("record") TblGrpcInterfaceJar record, @Param("example") TblGrpcInterfaceJarExample example);

    int updateByExample(@Param("record") TblGrpcInterfaceJar record, @Param("example") TblGrpcInterfaceJarExample example);

    int updateByPrimaryKeySelective(TblGrpcInterfaceJar record);

    int updateByPrimaryKey(TblGrpcInterfaceJar record);
}
