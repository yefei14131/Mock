package org.yefei.qa.mock.utils;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Stack;

/**
 * @author: yefei
 * @date: 2018/9/28 10:06
 */
@Slf4j
public class VariableManager {

    private static void cleanStack(Stack stack) {
        while (!stack.isEmpty()) {
            stack.pop();
        }
    }

    private static boolean variableCharValidate(String charAt) {
        return charAt.matches("[\\w\\$\\{\\}\\.]");
    }

    public static String getVariableValue(String variableName, HashMap... dataPools) {
        return innerGetVariableValue(variableName, null, dataPools);
    }


    /**
     * 获取变量的值
     *
     * @param variableName   变量名
     * @param cachedDataPool 缓存map，遇到变量值，优先从这个缓存里取。如果是从外部变量池里获取的，会更新到这个缓存里。减少变量的查找次数
     * @param dataPools      变量池数组
     * @return
     */
    private static String innerGetVariableValue(String variableName, HashMap<String, String> cachedDataPool, HashMap... dataPools) {
        if (cachedDataPool == null) {
            cachedDataPool = new HashMap<>();
        } else if (cachedDataPool.containsKey(variableName)) {
            return cachedDataPool.get(variableName);
        }

        String val = null;
        for (HashMap dataPool : dataPools) {
            val = getValue(variableName, dataPool);
            if (StringUtils.isNotEmpty(val)) {
                break;
            }
        }
        cachedDataPool.put(variableName, val);
        return val;
    }


    /**
     * 递归替换文本，文本里可能包含变量，本方法会从变量池里取出变量并替换掉
     * 1、没有变量的情况，原样返回。输入  this is an apple.  输出 this is an apple.
     * 2、有变量的时候，变量池里有对应的变量，就会替换变量并返回。 输入 this is an ${fruit}. 输出 this is an apple.
     * 3、有变量的时候，变量池里有对应的变量，变量会被替换成空字符串并返回。 输入 this is an ${fruit}. 输出 this is an .
     * 4、支持嵌套变量，变量池里存在的变量 user=yefei， yefei_fruit=apple。 输入 this is an ${${user}_fruit}. 输出 this is an apple.
     *
     * @param content        需要替换的原文本
     * @param cachedVariable 缓存map，遇到变量值，优先从这个缓存里取。如果是从外部变量池里获取的，会更新到这个缓存里。减少变量的查找次数
     * @param dataPools      变量池数组
     * @return
     */
    private static String innerDeepReplaceContent(String content, HashMap<String, String> cachedVariable, HashMap... dataPools) {
        if (cachedVariable == null) {
            cachedVariable = new HashMap<>();
        }
        String contentCopy = content;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0, len = content.length(); i < len; i++) {
            String charAt = content.substring(i, i + 1);
            if (charAt.equals("$")) {
                // 变量开始
                if (i + 3 < len && content.substring(i + 1, i + 2).equals("{") && variableCharValidate(content.substring(i + 2, i + 3))) {
                    // start
                    stack.push(i + 2);
                    i = i + 1;
                }
            } else if (charAt.equals("}")) {
                // 变量结束
                if (!stack.isEmpty()) {
                    // 获取变量名
                    String variableName = content.substring(stack.pop(), i);
                    if (!variableName.contains("${")) {
                        // 非嵌套变量
                        String variableValue = innerGetVariableValue(variableName, cachedVariable, dataPools);
                        cachedVariable.put(variableName, variableValue);
                        contentCopy = replace(contentCopy, variableName, variableValue);
                    } else {
                        // 嵌套变量
                        String replacedVariableName = innerDeepReplaceContent(variableName, cachedVariable, dataPools);
                        String variableValue = innerGetVariableValue(replacedVariableName, cachedVariable, dataPools);

                        cachedVariable.put(variableName, variableValue);
                        cachedVariable.put(replacedVariableName, variableValue);

                        contentCopy = replace(contentCopy, replacedVariableName, variableValue);
                    }
                }

            } else if (!stack.empty() && !variableCharValidate(charAt)) {
                cleanStack(stack);
            }
        }
        return contentCopy;
    }

    private static String replace(String input, String variableName, String variableValue) {

        String varReg = "${" + variableName + "}";
        return input.replace(varReg, variableValue);
    }

    /**
     *  替换文本，文本里可能包含变量，本方法会从变量池里取出变量并替换掉
     * @param content   需要替换的文本
     * @param dataPools 变量池
     * @return 替换后的文本
     */
    public static String replaceContent(String content, HashMap... dataPools) {
        HashMap<String, String> cachedVariable = new HashMap<>();
        return innerDeepReplaceContent(content, cachedVariable, dataPools);
    }


    private static Object getValue( Object container, String key) {
        if (container instanceof ScriptObjectMirror) {
           return ((ScriptObjectMirror)container).getMember(key);
        } else {
            return ((HashMap)container).get(key);
        }
    }

    private static String getValue(String variableName, HashMap dataPool){
        Object value = null;

        if (variableName.contains(".")){
            // 变量 json嵌套
            String[] varArr = variableName.split("\\.");
            try {
                Object p = dataPool.clone();
                for (int i=0, len=varArr.length; i < len; i++){
                    if (p == null) {
                        break;
                    }
                    if (i < len - 1){
                        p = getValue(p, varArr[i]);

                    }else{
                        value = getValue(p, varArr[i]);
                    }
                }

            }catch (Exception e){
//                log.info("变量{}没有匹配,异常 -> {}", variableName, ExceptionUtils.getStackTrace(e));
            }

        }else{
            // 一级变量
            value = dataPool.containsKey(variableName) ? dataPool.get(variableName) : null;
        }

        String finalValue;
        try {
            // 兼容对象的序列化
            if (value == null ) {
                finalValue = "";

            } else if ( value instanceof String
                    || value instanceof Integer
                    || value instanceof Long
                    || value instanceof Double
                    || value instanceof Float
                    || value instanceof BigDecimal
                    || value instanceof Byte
                    || value instanceof Char
                ){

                finalValue = String.valueOf(value);
            } else {
                finalValue = new ObjectMapper().writeValueAsString(value);
            }
        } catch (Exception e) {
            finalValue = String.valueOf(value);
        }

        return finalValue;
    }

}
