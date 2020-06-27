package org.yefei.qa.mock.task.executor.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.enums.ScriptLanguageEnum;
import org.yefei.qa.mock.model.bean.callback.task.AbstractTaskBean;
import org.yefei.qa.mock.model.bean.callback.task.ScriptTaskBean;
import org.yefei.qa.mock.script.exector.IScriptExecutor;
import org.yefei.qa.mock.task.executor.ITaskExecutor;

import javax.annotation.Resource;
import java.util.Arrays;

//import org.yefei.qa.mock.script.executor.IScriptExecutor;

/**
 * @author: yefei
 * @date: 2018/9/26 18:30
 */
@Slf4j
@Component("scriptTaskExector")
public class ScriptExector implements ITaskExecutor {

    @Resource
    private ObjectMapper jacksonFormatter;

    @Resource(name = "scriptExecutor")
    private IScriptExecutor scriptExecutor;

    @Override
    public String exec(AbstractTaskBean taskConfig, String traceID) {
        try {
            log.info("Task -> 开始执行scriptTask，configs: {}", JSONObject.toJSON(taskConfig));
            ScriptTaskBean scriptTaskBean = (ScriptTaskBean) taskConfig;
            String scriptLanguage = scriptTaskBean.getScriptLanguage();

            StringBuilder resultMsg = new StringBuilder();
            Arrays.stream(ScriptLanguageEnum.values()).forEach(scriptLanguageItem -> {
                if (scriptLanguageItem.getLanguage().equals(scriptLanguage)){

                    String result = scriptExecutor.exec(scriptLanguageItem.getEngineName(), scriptTaskBean.getScriptContent(), scriptTaskBean.getData(), null, null, null);
                    resultMsg.append(result);
                    log.info(result);
                }
            });

            return resultMsg.toString();

        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return ExceptionUtils.getStackTrace(e);
        }

    }
}
