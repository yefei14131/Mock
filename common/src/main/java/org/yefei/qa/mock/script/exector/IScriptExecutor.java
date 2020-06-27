package org.yefei.qa.mock.script.exector;

import java.util.HashMap;

/**
 * @author: yefei
 * @date: 2018/9/27 10:43
 */
public interface IScriptExecutor {
    String exec(String enginName, String scriptContent, HashMap userDefined, HashMap params, HashMap headers, HashMap cookies);
}
