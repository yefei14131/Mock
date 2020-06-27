package org.yefei.qa.mock.grpc.impl;

import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.cache.GlobalDataPool;
import org.yefei.qa.mock.grpc.idl.Variable;
import org.yefei.qa.mock.grpc.idl.VariableDefineRequest;
import org.yefei.qa.mock.grpc.idl.VariableDefineResp;
import org.yefei.qa.mock.grpc.idl.VariableServiceGrpc;

import java.util.HashMap;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author yefei
 * @date: 2020/5/17
 */
@Component
public class MockServerVariableServiceImpl extends VariableServiceGrpc.VariableServiceImplBase {

    @Autowired
    private ThreadPoolExecutor multiThreadPool;

    @Autowired
    private GlobalDataPool globalDataPool;

    /**
     * 预定义变量，目前只支持单机，TODO 分布式以后再支持
     *
     * @param request
     * @param responseObserver
     */

    @Override
    public void preDefine(VariableDefineRequest request, StreamObserver<VariableDefineResp> responseObserver) {
        HashMap<String, Object> params = new HashMap();
        request.getVariableListList().forEach(variable -> {
            params.put(variable.getName(), variable.getValue());
        });

        multiThreadPool.submit(() -> {
            globalDataPool.updateCache(params);
            HashMap<String, Object> all = globalDataPool.getAll();
            VariableDefineResp.Builder builder = VariableDefineResp.newBuilder().setSuccess(true);
            all.entrySet().forEach(entry -> {
                builder.addVariableList(Variable.newBuilder().setName(entry.getKey()).setValue(entry.getValue().toString()).build());
            });
            responseObserver.onNext(builder.build());
            responseObserver.onCompleted();
        });
    }
}
