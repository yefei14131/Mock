package org.yefei.qa.mock.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;

/**
 * @author yefei
 * @date: 2020/5/18
 */
public class VariableManagerTest {

    @Test
    public void getRequestValue() {
        double val = Math.pow(1.05, 12);
        System.out.println(val);
    }

    @Test
    public void replaceContent() {
        HashMap userDefined = buildUserDefined();
        HashMap params = buildParams();
        HashMap globalDataPool = buildGlobalDataPool();

        String input = "{\"mutableFirstName\":\"${${firstname}}\", \"firstname\":\"${firstname}\", \"lastname\":\"${lastname}\", \"mutableLastName\":\"${_${lastname}}\"}";
        String output = VariableManager.replaceContent(input, userDefined, params, globalDataPool);

        JSONObject result = JSON.parseObject(output);

        TestCase.assertEquals("firstname", params.get("firstname"), result.getString("firstname"));
        TestCase.assertEquals("lastname", params.get("lastname"), result.getString("lastname"));
        TestCase.assertEquals("mutableFirstName", globalDataPool.get("ye"), result.getString("mutableFirstName"));
        TestCase.assertEquals("mutableLastName", globalDataPool.get("_fei"), result.getString("mutableLastName"));
    }


    private HashMap buildUserDefined() {
        HashMap userDefined = new HashMap();

        return userDefined;
    }

    private HashMap buildParams() {
        HashMap params = new HashMap();
        params.put("firstname", "ye");
        params.put("lastname", "fei");

        return params;
    }

    private HashMap buildGlobalDataPool() {
        HashMap params = new HashMap();
        params.put("ye", "ye666");
        params.put("fei", "fei777");
        params.put("_fei", "_fei777");

        return params;
    }

}
