package org.yefei.qa.mock.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.dao.IMappingGlobalVarDao;
import org.yefei.qa.mock.model.gen.pojo.TblMappingGlobalVar;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author: yefei
 * @date: 2018/8/31 01:27
 */
@Slf4j
@Component
public class GlobalVarCache extends TimeChangeAbledCache {

    @Autowired
    private IMappingGlobalVarDao mappingGlobalVarDao;

    @Override
    protected List<TblMappingGlobalVar> loadData(Object requestID_protocol) {
        String[] split = ((String) requestID_protocol).split("_");

        return mappingGlobalVarDao.queryGlobalVarList(Integer.valueOf(split[0]), split[1]);
    }

    public List<TblMappingGlobalVar> getGlobalVarList(int requestID, String protocol) {
        try {
            return (List<TblMappingGlobalVar>) cache.get(requestID + "_" + protocol);
        } catch (ExecutionException e) {
            log.debug(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }


}
