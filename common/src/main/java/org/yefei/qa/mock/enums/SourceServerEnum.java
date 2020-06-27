package org.yefei.qa.mock.enums;

/**
 * @author: yefei
 * @date: 2018/11/1 16:28
 */
public enum SourceServerEnum {

    ADMIN("admin")
    ,GRPC("grpc")
    ,REST("rest");

    private String src;

    SourceServerEnum(String src){
        this.src = src;
    }

    public String getSrc() {
        return src;
    }
}
