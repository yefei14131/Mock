package org.yefei.qa.mock.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.Configuration;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import java.util.concurrent.ExecutionException;

/**
 * @author: yefei
 * @date: 2018/8/31 01:27
 */
@Slf4j
@Configuration
public class ScriptEnginCache extends BaseGuavaCache {

//    public ScriptEnginCache(){
//        super(GuavaContants.EXPIRE_DURATION, GuavaContants.EXPIRE_TIME_UNIT);
//    }

    /**
     * 缓存数据加载方法
     *
     * @param enginName
     * @return
     * @author coshaho
     */
    @Override
    protected ScriptEngine loadData(Object enginName) {
        ScriptEngineManager factory = new ScriptEngineManager( );

        ScriptEngine scriptEngine = factory.getEngineByName((String) enginName);
        if (scriptEngine == null) {
            log.info("can not get scriptEngine, enginName: {}", enginName);

            for (ScriptEngineFactory scriptEngineFactory : factory.getEngineFactories()) {
                log.info("supported =>  enginName: {}, languageName: {}, names: {}", scriptEngineFactory.getEngineName(), scriptEngineFactory.getLanguageName(), scriptEngineFactory.getNames());
            }
        }

        return scriptEngine;
    }

    public ScriptEngine getScriptEngine(String enginName){
        try {
            return (ScriptEngine) cache.get(enginName);
        } catch (ExecutionException e) {
            log.debug(ExceptionUtils.getStackTrace(e));
            return null;
        }
    }


}
