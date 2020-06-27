package org.yefei.qa.mock.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.cache.GrpcRequestScriptCache;
import org.yefei.qa.mock.debugger.SystemDebugger;
import org.yefei.qa.mock.enums.ScriptLanguageEnum;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestScript;
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

    @Resource(name = "grpcRequestScriptCache")
    private GrpcRequestScriptCache grpcRequestScriptCache;

    @Resource(name = "scriptExecutor")
    private IScriptExecutor scriptExecutor;

    @Autowired
    private SystemDebugger systemDebugger;


    @Override
    public void execScripts(String fullMethodName, HashMap userDefined, HashMap params){

        List<TblGrpcRequestScript> tblRestRequestScripts = grpcRequestScriptCache.queryScripts(fullMethodName);

        if (tblRestRequestScripts.size() == 0) {
            systemDebugger.addSystemLog("没有需要执行的脚本", "没有需要执行的脚本");
        }

        tblRestRequestScripts.forEach(script->{
            String scriptLanguage = script.getScriptLanguage();

            StringBuilder resultMsg = new StringBuilder();

            Arrays.stream(ScriptLanguageEnum.values()).forEach(scriptLanguageItem -> {

                if (scriptLanguageItem.getLanguage().equals(scriptLanguage)){

                    String result = scriptExecutor.exec(scriptLanguageItem.getEngineName(), script.getScriptContent(), userDefined, params, null, null);
                    resultMsg.append(result);
                    systemDebugger.addSystemLog("脚本执行完成", String.format("执行结果：%s\n脚本语言%s\n脚本内容:\n%s", result, scriptLanguage, script.getScriptContent()));

                }
            });

        });


        if (tblRestRequestScripts.size() > 0) {
            systemDebugger.addSystemLog("脚本执行完成，自定义变量为", userDefined);
        }
    }

}
