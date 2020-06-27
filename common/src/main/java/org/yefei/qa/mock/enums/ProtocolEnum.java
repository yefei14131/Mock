package org.yefei.qa.mock.enums;

/**
 * @author: yefei
 * @date: 2018/11/1 16:19
 */
public enum ProtocolEnum {
    HTTP("http")
    ,GRPC("grpc");

    private String protocol;

    ProtocolEnum(String protocol){
        this.protocol = protocol;
    }

    public String getProtocol(){
        return protocol;
    }

}
