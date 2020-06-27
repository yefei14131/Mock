package org.yefei.qa.mock.dao;

import org.yefei.qa.mock.model.gen.pojo.TblGrpcInterfaceJar;

import java.util.Date;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:14
 */
public interface IGrpcProtocolDao {

    List<TblGrpcInterfaceJar> queryGrpcInterface(Date lessUpdateTime);

    List<TblGrpcInterfaceJar> queryAllGrpcInterface();
}
