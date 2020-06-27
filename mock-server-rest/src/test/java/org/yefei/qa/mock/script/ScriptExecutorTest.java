package org.yefei.qa.mock.script;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.yefei.qa.mock.script.exector.IScriptExecutor;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author: yefei
 * @date: 2018/8/19 21:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = {"com.hualala"})
public class ScriptExecutorTest {

    @Resource()
    @Autowired
    private IScriptExecutor scriptExecutor;

    @Test
    public void execGroovyScript() {
        HashMap userDefined = buildUserDefined();
        HashMap params = buildParams();
        HashMap headers = buildHeaders();
        HashMap cookies = buildCookies();

        String groovyCode = genScript();

        scriptExecutor.exec("groovy", groovyCode, userDefined, params, headers, cookies);

        assert userDefined.containsKey("groovyDefine");
        assert (userDefined.get("groovyDefine")).equals("hello");

        TestCase.assertTrue(userDefined.containsKey("groovyDefine"));
        TestCase.assertEquals(userDefined.get("groovyDefine"), "hello");

    }

    private HashMap buildUserDefined(){
        HashMap userDefined = new HashMap();


        return userDefined;
    }

    private HashMap buildParams(){
        HashMap params = new HashMap();
        params.put("firstname", "ye");
        params.put("lastname", "fei");

        return params;
    }

    private HashMap buildHeaders(){
        HashMap headers = new HashMap();

        return headers;
    }

    private HashMap buildCookies(){
        HashMap cookies = new HashMap();

        return cookies;
    }

    private String genScript(){
        String script =  "vars.put('username', params.get('firstname') + params.get('lastname'));" +
                "vars.put('groovyDefine', 'hello');\n";

        String a = "vars.put('username', params.get('firstname') + params.get('lastname'));\nvars.put(ageTotle', Integer.parseInt(params.get('age')) + 2;";

        return script;
    }


    @Test
    public void execJavascriptScript() {
        HashMap userDefined = buildUserDefined();
        HashMap params = buildParams();
        HashMap headers = buildHeaders();
        HashMap cookies = buildCookies();

        String jsScript = genJsScript();

        scriptExecutor.exec("JavaScript", jsScript, userDefined, params, headers, cookies);

        TestCase.assertTrue(userDefined.containsKey("jsDefine"));
        TestCase.assertEquals(userDefined.get("username"), "yefei");
//        TestCase.assertTrue("userDefined.containsKey(\"kkk\")" , userDefined.containsKey("kkk"));

    }

    private String genJsScript() {
        String script = "vars['username'] = params['firstname'] + params['lastname'];" +
                "vars['jsDefine'] = 'hello';\n";
        return script;
    }
}
