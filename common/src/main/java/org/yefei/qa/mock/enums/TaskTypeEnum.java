package org.yefei.qa.mock.enums;

/**
 * @author: yefei
 * @date: 2018/9/25 16:33
 */
public enum TaskTypeEnum {

    //任务类型: 1 http请求; 2 grpc请求； 3 rabbitmq发消息;   4 script;

    HTTP_REQUEST(1, "http请求", "httpTaskExector", "httpTaskGenerator", "HttpTaskBean", 0)
    ,GRPC_REQUEST(2, "grpc请求", "grpcTaskExector", "grpcTaskGenerator", "GrpcTaskBean", 0)
    ,RABBIT_MQ(3, "发送rabbitmq消息", "rabbitmqTaskExector", "rabbitmqTaskGenerator", "RabbitmqTaskBean", 1)
//    ,SCRIPT(4, "执行脚本", "scriptTaskExector", "scriptTaskGenerator", "ScriptTaskBean", 0)

    ;

    private int taskType;

    private String desc;

    private String executor;

    private String generator;

    private String beanSimpleName;

    private int configType;

    TaskTypeEnum(int taskType, String desc, String executor, String generator, String beanSimpleName, int configType){
        this.taskType = taskType;
        this.desc = desc;
        this.executor = executor;
        this.generator = generator;
        this.beanSimpleName = beanSimpleName;
        this.configType = configType;
    }

    public int getTaskType(){
        return this.taskType;
    }

    public String getDesc(){
        return this.desc;
    }

    public String getExecutor(){
        return this.executor;
    }

    public String getGenerator() {
        return generator;
    }


    public String getBeanSimpleName(){
        return beanSimpleName;
    }

    public int getConfigType(){return this.configType;}
}
