package org.yefei.qa.mock.enums;

/**
 * Created by yefei on 2018/8/3.
 */
public enum ResponseTypeEnum {

    JSON("json", "application/json;charset=UTF-8")
    , HTML("html", "text/html;charset=utf-8")
    , JS("js", "application/javascript;charset=UTF-8")
    , JSONP("jsonP", "text/html;charset=utf-8")
    ;

    private String type;
    private String contentType;

    ResponseTypeEnum(String type, String contentType){
        this.type = type;
        this.contentType = contentType;
    }

    public String getType(){
        return this.type;
    }

    public String getContentType(){
        return this.contentType;
    }
}
