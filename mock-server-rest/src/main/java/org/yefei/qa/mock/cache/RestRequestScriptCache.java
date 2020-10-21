package org.yefei.qa.mock.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.dao.ITblRestRequestScriptDao;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestScript;

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
public class RestRequestScriptCache extends TimeChangeAbledCache {

    private static final List<TblRestRequestScript> empty = new ArrayList<>();

    @Autowired
    private ITblRestRequestScriptDao tblRestRequestScriptDao;

    /**
     * 缓存数据加载方法
     *
     * @param uri
     * @return
     * @author coshaho
     */
    @Override
    protected List<TblRestRequestScript> loadData(Object uri) {
        String uriStr = String.valueOf(uri);
        String[] pathArr = uriStr.split("/");
        String groupCode = pathArr[0];
        String path = uriStr.substring(groupCode.length());
        return tblRestRequestScriptDao.queryScripts(groupCode, path);
    }




    public  List<TblRestRequestScript> queryScripts(String groupCode, String path) {
        try {
            return (List<TblRestRequestScript>) cache.get(groupCode + path);

        } catch (ExecutionException e) {
            log.debug(ExceptionUtils.getStackTrace(e));
            return empty;
        }

    }
}
