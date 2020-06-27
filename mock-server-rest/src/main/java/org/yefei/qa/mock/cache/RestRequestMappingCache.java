package org.yefei.qa.mock.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.yefei.qa.mock.contants.GuavaContants;
import org.yefei.qa.mock.dao.ITblRestRequestMappingDao;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author: yefei
 * @date: 2018/8/31 01:26
 */
@Configuration
@Slf4j
public class RestRequestMappingCache extends BaseGuavaCache {

    private static final List<TblRestRequestMapping> empty = new ArrayList<>();

    @Autowired
    private ITblRestRequestMappingDao tblRestRequestMappingDao;


    public void updateCacheTime(long duration) {
        super.updateCacheTime(duration, GuavaContants.EXPIRE_TIME_UNIT);
    }

    public RestRequestMappingCache(){
        super(GuavaContants.EXPIRE_DURATION, GuavaContants.EXPIRE_TIME_UNIT);
    }

    /**
     * 缓存数据加载方法
     *
     * @param uri
     * @return
     * @author coshaho
     */
    @Override
    protected List<TblRestRequestMapping> loadData(Object uri) {
        log.info("load cache, uri:{} ", uri);
        String uriStr = String.valueOf(uri);
        String[] pathArr = uriStr.split("/");
        String groupCode = pathArr[0];
        String path = uriStr.substring(groupCode.length());

        return tblRestRequestMappingDao.queryTblRestRequestMapping(groupCode, path);

    }


    public List<TblRestRequestMapping> queryTblRestRequestMapping(String groupCode, String path) {
        try {
            return (List<TblRestRequestMapping>) cache.get(groupCode + path);

        } catch (ExecutionException e) {
            log.debug(ExceptionUtils.getStackTrace(e));
            return empty;
        }

    }
}
