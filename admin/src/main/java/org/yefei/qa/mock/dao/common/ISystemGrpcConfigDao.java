package org.yefei.qa.mock.dao.common;

import org.springframework.stereotype.Repository;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcInterfaceJar;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:14
 */
@Repository
public interface ISystemGrpcConfigDao {

    List<TblGrpcInterfaceJar> queryGrpcInterfaceList(int page, int pageSize);

    long countGrpcInterfaceList();

    TblGrpcInterfaceJar get(int ID);

    int insert(TblGrpcInterfaceJar interfaceJar);

    int update(TblGrpcInterfaceJar interfaceJar);

    int delete(int ID);
}
