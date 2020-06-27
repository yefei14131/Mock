package org.yefei.qa.mock.script.exector.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.annotation.Configuration;
import org.yefei.qa.mock.cache.ScriptEnginCache;
import org.yefei.qa.mock.script.exector.IScriptExecutor;

import javax.annotation.Resource;
import javax.script.Bindings;
import javax.script.ScriptEngine;
import java.util.HashMap;

/**
 * @author: yefei
 * @date: 2018/8/18 22:37
 */
@Configuration("scriptExecutor")
@Slf4j
public class ScriptExecutorImpl implements IScriptExecutor {

    @Resource
    private ScriptEnginCache scriptEnginCache;

    public String exec(String enginName, String scriptContent, HashMap userDefined, HashMap params, HashMap headers, HashMap cookies){

        try{

            ScriptEngine scriptEngine = scriptEnginCache.getScriptEngine(enginName);

            Bindings bings = scriptEngine.createBindings();

            bings.put("vars", userDefined);
            bings.put("params", params);

            if (headers != null) {
                bings.put("headers", headers);
            }

            if(cookies != null){
                bings.put("cookies", cookies);
            }

            if (scriptEngine == null){
                return String.format("不支持的脚本Engine: %s", enginName);
            }

            Object result =  scriptEngine.eval(scriptContent, bings);

            log.info("{}脚本执行结果：{}", enginName, result);
            if (result == null){
                return String.format("%s脚本执行完成", enginName);
            }else{
                return String.format("%s脚本执行完成,结果：%s", enginName, result);
            }

        }catch (Exception e){
//            log.error("执行{}脚本错误，错误信息{}；脚本内容:{}", enginName, ExceptionUtils.getStackTrace(e), scriptContent);
            return String.format("执行%s脚本错误，错误信息%s；脚本内容:%s", enginName, ExceptionUtils.getStackTrace(e), scriptContent);
        }
    }

}
