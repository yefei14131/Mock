package org.yefei.qa.mock.enums;

/**
 * Created by yefei on 2018/8/3.
 */
public enum VariableSourceEnum {
    HEADER("header", "header"),
    COOKIE("cookie", "cookie"),
    PARAMS("parameter", "请求参数");

    private String code;
    private String memo;

    VariableSourceEnum(String code, String memo){
        this.code = code;
        this.memo = memo;
    }

    public String getCode(){
        return this.code;
    }

    public String getMemo(){
        return this.memo;
    }
}
