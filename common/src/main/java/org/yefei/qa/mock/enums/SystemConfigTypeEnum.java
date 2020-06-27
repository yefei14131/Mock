package org.yefei.qa.mock.enums;


/**
 * @author: yefei
 * @date: 2018/9/25 20:25
 */
public enum SystemConfigTypeEnum {

    RABBITMQ(1, "RabbitMQ配置", "RabbitmqConfigBean")
    ,REDIS(2, "Redis配置", "RedisConfigBean")
    ,SERVER_CACHE(3, "Server缓存时间配置", "ServerCacheConfigBean")
    ,SERVER_HOST(10001, "ServerHost信息", "MockServerHostConfigBean")

    ;

    private int typeID;

    private String typeName;

    private String beanSimpleName;


    SystemConfigTypeEnum(int typeID, String typeName, String beanSimpleName){
        this.typeID = typeID;
        this.typeName = typeName;
        this.beanSimpleName = beanSimpleName;
    }

    public int getTypeID(){
        return this.typeID;
    }

    public String getTypeName(){
        return this.typeName;
    }

    public String getBeanSimpleName(){
        return this.beanSimpleName;
    }
}
