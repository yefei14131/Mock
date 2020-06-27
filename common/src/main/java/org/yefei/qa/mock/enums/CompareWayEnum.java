package org.yefei.qa.mock.enums;

/**
 * Created by yefei on 2018/8/3.
 */
public enum CompareWayEnum {
    EQUALS("equals", "字符串相等"), CONTAINS("contains", "字符串包含"), GT("gt", "数字大于"), GTE("gte", "数字大于等于"), LT("lt", "数字小于"), LTE("lte", "数字小于等于"), EQ("eq", "数字等于"), NEQ("neq", "数字不等于"), REGEX("regex", "正则匹配")


    ;

    private String code;
    private String memo;

    CompareWayEnum(String code, String memo){
        this.code = code;
        this.memo = memo;
    }

    public String getCode(){
        return code;
    }

    public String getMemo(){
        return memo;
    }

}
