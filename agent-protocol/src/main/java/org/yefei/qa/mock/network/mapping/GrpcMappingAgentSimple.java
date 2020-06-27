package org.yefei.qa.mock.network.mapping;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author yefei
 * @date: 2020/4/27
 */
@Data
public class GrpcMappingAgentSimple implements Serializable {
    private String serviceName;
    private String methodName;

    public GrpcMappingAgentSimple() {

    }

    public GrpcMappingAgentSimple(String serviceName, String methodName) {
        this.serviceName = serviceName;
        this.methodName = methodName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GrpcMappingAgentSimple)) return false;
        if (!super.equals(o)) return false;
        GrpcMappingAgentSimple that = (GrpcMappingAgentSimple) o;
        return Objects.equals(serviceName, that.serviceName) &&
                Objects.equals(methodName, that.methodName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), serviceName, methodName);
    }
}
