package org.yefei.qa.mock.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.dao.common.ISystemGrpcConfigDao;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcInterfaceJar;
import org.yefei.qa.mock.service.ISystemGrpcConfigService;

import java.util.Date;
import java.util.List;

/**
 * @author yefei
 * @date: 2020/4/1
 */
@Service
@Slf4j
public class SystemGrpcConfigServiceImpl implements ISystemGrpcConfigService {

    @Autowired
    private ISystemGrpcConfigDao systemGrpcConfigDao;

    @Autowired
    private SystemNoticeService systemNoticeService;

    @Autowired
    private GrpcBizServiceImpl grpcBizService;

    @Override
    public List<TblGrpcInterfaceJar> queryGrpcInterfaceList(int page, int pageSize) {
        return systemGrpcConfigDao.queryGrpcInterfaceList(page, pageSize);
    }

    @Override
    public long countGrpcInterfaceList() {
        return systemGrpcConfigDao.countGrpcInterfaceList();
    }

    @Override
    public TblGrpcInterfaceJar get(int itemID) {
        return systemGrpcConfigDao.get(itemID);
    }


    @Override
    public boolean saveGrpcInterface(TblGrpcInterfaceJar grpcInterfaceJar) {
        grpcInterfaceJar.setUpdateTime(new Date());
        if (grpcInterfaceJar.getItemID() == 0) {
            systemGrpcConfigDao.insert(grpcInterfaceJar);
        } else {
            systemGrpcConfigDao.update(grpcInterfaceJar);
        }
        if (grpcInterfaceJar.getIsActive()) {
            systemNoticeService.notifyForGrpcJar(grpcInterfaceJar);
        }
        return true;
    }

    @Override
    public boolean delete(int ID) {
        systemGrpcConfigDao.delete(ID);
        return true;
    }


}
