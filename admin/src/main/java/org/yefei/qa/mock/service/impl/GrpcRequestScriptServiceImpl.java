package org.yefei.qa.mock.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.dao.grpc.IGrpcRequestScriptDao;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestScript;
import org.yefei.qa.mock.service.IGrpcRequestScriptService;

import java.util.Date;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/4 22:11
 */
@Service
public class GrpcRequestScriptServiceImpl implements IGrpcRequestScriptService {

    @Autowired
    private IGrpcRequestScriptDao grpcScriptDao;

    @Override
    public List<TblGrpcRequestScript> queryGrpcScriptList(String serviceName, String methodName) {
        return grpcScriptDao.queryScriptList(serviceName, methodName);
    }

    @Override
    public long countGrpcScript(String serviceName, String methodName) {
        return grpcScriptDao.countGrpcScript(serviceName, methodName);
    }

    @Override
    public boolean saveGrpcScript(TblGrpcRequestScript grpcRequestScript) {
        grpcRequestScript.setUpdateTime(new Date());
        if (grpcRequestScript.getScriptID() != null && grpcRequestScript.getScriptID() > 0) {
            return grpcScriptDao.updateGrpcScript(grpcRequestScript) > 0 ? true : false;
        } else {
            return grpcScriptDao.insertGrpcScript(grpcRequestScript) > 0 ? true : false;
        }
    }

    @Override
    public boolean deleteGrpcScript(int scriptID) {
        return grpcScriptDao.deleteGrpcScript(scriptID) > 0 ? true : false;
    }

    @Override
    public TblGrpcRequestScript getScript(int scriptID) {
        return grpcScriptDao.getScript(scriptID);
    }
}
