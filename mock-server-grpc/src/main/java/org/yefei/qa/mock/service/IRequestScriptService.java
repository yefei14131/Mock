package org.yefei.qa.mock.service;

import java.util.HashMap;

/**
 * Created by yefei on 2018/7/31.
 */
public interface IRequestScriptService {


    void execScripts(String fullMethodName, HashMap userDefined, HashMap params);
}
