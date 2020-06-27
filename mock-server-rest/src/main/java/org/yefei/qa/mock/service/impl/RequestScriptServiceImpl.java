package org.yefei.qa.mock.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.cache.RestRequestScriptCache;
import org.yefei.qa.mock.debugger.SystemDebugger;
import org.yefei.qa.mock.enums.ScriptLanguageEnum;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestScript;
import org.yefei.qa.mock.script.exector.IScriptExecutor;
import org.yefei.qa.mock.service.IRequestScriptService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yefei on 2018/8/2.
 */
@Slf4j
@Service("requestScriptService")
public class RequestScriptServiceImpl implements IRequestScriptService {

    @Resource(name = "restRequestScriptCache")
    private RestRequestScriptCache restRequestScriptCache;

    @Resource(name = "scriptExecutor")
    private IScriptExecutor scriptExecutor;

    @Autowired
    private SystemDebugger systemDebugger;

    @Autowired
    private ObjectMapper jacksonFormatter;

    @Override
    public void execScripts(String groupCode, String path, HashMap userDefined, HashMap params, HashMap headers, HashMap cookies){

        List<TblRestRequestScript> tblRestRequestScripts = restRequestScriptCache.queryScripts(groupCode, path);

        if (tblRestRequestScripts.size() == 0) {
            systemDebugger.addSystemLog("没有需要执行的脚本", "没有需要执行的脚本");
        }
        tblRestRequestScripts.forEach(script->{
            String scriptLanguage = script.getScriptLanguage();

            StringBuilder resultMsg = new StringBuilder();

            Arrays.stream(ScriptLanguageEnum.values()).forEach(scriptLanguageItem -> {

                if (scriptLanguageItem.getLanguage().equals(scriptLanguage)){

                    String result = scriptExecutor.exec(scriptLanguageItem.getEngineName(), script.getScriptContent(), userDefined, params, headers, cookies);
                    resultMsg.append(result);
                    systemDebugger.addSystemLog("脚本执行完成", String.format("执行结果：%s\n脚本语言%s\n脚本内容:\n%s", result, scriptLanguage, script.getScriptContent()));
                }
            });

            log.info(resultMsg.toString());
        });

        if (tblRestRequestScripts.size() > 0) {
            systemDebugger.addSystemLog("脚本执行完成，自定义变量为", userDefined);
        }

    }

}
