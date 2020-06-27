package org.yefei.qa.mock.model.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/11/7 13:35
 */
@Data
public class GrpcService {

    private String serviceName;

    private List<GrpcMethod> methodList = new ArrayList<>();


    public boolean equals(Object obj){
        if (!(obj instanceof GrpcMethod)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        return this.serviceName.equals(((GrpcMethod) obj).getServiceName());
    }

    public int hashCode(){
        return  this.serviceName.hashCode();
    }

}
