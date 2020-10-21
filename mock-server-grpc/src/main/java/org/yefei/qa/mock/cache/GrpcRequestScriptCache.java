package org.yefei.qa.mock.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.dao.ITblGrpcRequestScriptDao;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestScript;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author: yefei
 * @date: 2018/8/31 01:27
 */
@Component
@Slf4j
@DependsOn("guavaCacheSubject")
public class GrpcRequestScriptCache extends TimeChangeAbledCache {

    private static final List<TblGrpcRequestScript> empty = new ArrayList<>();

    @Autowired
    private ITblGrpcRequestScriptDao tblGrpcRequestScriptDao;

    /**
     * 缓存数据加载方法
     *
     * @param
     * @return
     * @author coshaho
     */
    @Override
    protected Object loadData(Object fullMethodName) {
        String[] arr = fullMethodName.toString().split("/");
        String serviceName = arr[0];
        String methodName = arr[1];

        return tblGrpcRequestScriptDao.queryScripts(serviceName, methodName);
    }




    public  List<TblGrpcRequestScript> queryScripts(String fullMethodName) {
        try {
            return (List<TblGrpcRequestScript>) cache.get(fullMethodName);

        } catch (ExecutionException e) {
            log.debug(ExceptionUtils.getStackTrace(e));
            return empty;
        }

    }
}
