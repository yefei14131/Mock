package org.yefei.qa.mock.model.bean;

import lombok.Data;

/**
 * @author: yefei
 * @date: 2018/11/7 13:36
 */
@Data
public class GrpcMethod {

    private String serviceName;

    private String methodName;

    private Class requestClass;

    private Class responseClass;

    public GrpcMethod( String serviceName, String methodName, Class requestClass, Class responseClass){
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.requestClass = requestClass;
        this.responseClass = responseClass;
    }

    public boolean equals(Object obj){
        if (!(obj instanceof GrpcMethod)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        return this.serviceName.equals(((GrpcMethod) obj).getServiceName()) && this.methodName.equals(((GrpcMethod) obj).getMethodName());
    }


    public int hashCode(){
        return  this.serviceName.hashCode() + this.methodName.hashCode();
    }

}
