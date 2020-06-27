package org.yefei.qa.mock.service;

import org.yefei.qa.mock.model.gen.pojo.TblGrpcInterfaceJar;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:14
 */
public interface ISystemGrpcConfigService {

    List<TblGrpcInterfaceJar> queryGrpcInterfaceList(int page, int pageSize);

    long countGrpcInterfaceList();

    TblGrpcInterfaceJar get(int ID);

    boolean saveGrpcInterface(TblGrpcInterfaceJar grpcInterfaceJar);

    boolean delete(int ID);
}
