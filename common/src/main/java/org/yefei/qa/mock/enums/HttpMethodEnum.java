package org.yefei.qa.mock.enums;

/**
 * @author: yefei
 * @date: 2018/11/1 16:24
 */
public enum HttpMethodEnum {
    POST("post")
    ,GET("get");

    private String method;

    HttpMethodEnum(String method){
        this.method = method;
    }

    public String getMethod(){
        return this.method;
    }
}
