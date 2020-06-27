package org.yefei.qa.mock.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.dao.IGrpcMappingDao;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author: yefei
 * @date: 2018/12/2 23:42
 */

@Component
@Slf4j
public class GrpcRequestMappingCache extends BaseGuavaCache {

    @Autowired
    private IGrpcMappingDao grpcMappingDao;


    private List<TblGrpcRequestMapping> empty = new ArrayList<>();
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

        return grpcMappingDao.queryGrpcMappings(serviceName, methodName);
    }


    public List<TblGrpcRequestMapping> queryGrpcMappings(String fullMethodName) {
        try {
            return (List<TblGrpcRequestMapping>) this.cache.get(fullMethodName);

        } catch (ExecutionException e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }

        return empty;
    }

}
