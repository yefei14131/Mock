package org.yefei.qa.mock.service.impl;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.yefei.qa.mock.utils.VariableManager;

import java.util.HashMap;

/**
 * @author: yefei
 * @date: 2018/8/18 22:24
 */
@SpringBootTest
public class ResponseWriterTest {


    @Test
    public void buildResponseContenTest(){
        String content = "{\"msg\":\"成功\",\"code\":\"${user.host}\",\"success\":\"${user.age}\",\"noRedCount\":\"${userName}\",\"host\":\"${user.host}\",\"noReleaseCount\":\"0\"}";
        HashMap params = new HashMap<>();
        params.put("userName", "yefei");

        HashMap<String, String> user = new HashMap<>();
        user.put("host", "local");
        user.put("age", "18");
        params.put("user", user);

        HashMap userDefined = new HashMap();
        String result = VariableManager.replaceContent(content, userDefined, params);
        System.out.println(result);
    }
}
