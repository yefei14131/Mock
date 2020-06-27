package org.yefei.qa.mock.grpc.handler;

import io.grpc.util.MutableHandlerRegistry;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 动态grpc服务注册器
 *
 * @author yefei
 * @date: 2020/5/12
 */
@Component
@Data
public class GrpcRegistry {

    private MutableHandlerRegistry privateRegistry;

    GrpcRegistry() {
        this.privateRegistry = genMutableRegistry();
    }

    public MutableHandlerRegistry genMutableRegistry() {
        return new MutableHandlerRegistry();
    }


}
