package org.yefei.qa.mock.enums;

/**
 * @author: yefei
 * @date: 2018/11/1 16:41
 */
public enum OperatorEnum {
    AND("&&", "且")
    ,OR("||", "或");

    private String operator;
    private String memo;

    OperatorEnum(String operator, String memo){
        this.operator = operator;
        this.memo = memo;
    }

    public String getOperator() {
        return operator;
    }

    public String getMemo(){
        return this.memo;
    }
}
