package org.yefei.qa.mock.task.executor.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;
import io.grpc.stub.AbstractStub;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.debugger.SystemDebugger;
import org.yefei.qa.mock.grpc.client.GrpcClientManager;
import org.yefei.qa.mock.grpc.client.GrpcRequestHeaderHolder;
import org.yefei.qa.mock.grpc.handler.GrpcServiceProvider;
import org.yefei.qa.mock.model.bean.callback.task.AbstractTaskBean;
import org.yefei.qa.mock.model.bean.callback.task.GrpcTaskBean;
import org.yefei.qa.mock.task.executor.ITaskExecutor;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.concurrent.Executor;

/**
 * @author: yefei
 * @date: 2018/9/26 18:30
 */
@Slf4j
@Component("grpcTaskExector")
public class GRPCRequestExector implements ITaskExecutor {

    @Autowired
    private GrpcClientManager grpcClientManager;

    @Autowired
    private GrpcServiceProvider grpcServiceProvider;

    @Autowired
    private GrpcRequestHeaderHolder grpcRequestHeaderHolder;

    @Autowired
    private SystemDebugger systemDebugger;

    @Override
    public String exec(AbstractTaskBean taskConfig, String traceID) {
        try {
            String taskConfigJson = JSONObject.toJSONString(taskConfig);
            log.info("Task -> 开始执行grpcTask，configs: {}", taskConfigJson);
            systemDebugger.addSystemLog(traceID, "开始执行grpcTask", taskConfigJson);

            GrpcTaskBean grpcTaskBean = (GrpcTaskBean) taskConfig;

            String message = null;
            GrpcServiceProvider.MethodClassBean grpcServiceClass = grpcServiceProvider.getGrpcServiceClass(grpcTaskBean.getServiceName() + "/" + grpcTaskBean.getMethodName());
            if (grpcServiceClass == null) {
                message = MessageFormat.format("Task -> grpc class 不存在: {0}/{1}", grpcTaskBean.getServiceName(), grpcTaskBean.getMethodName());
                log.error(message);
                systemDebugger.addSystemLog(traceID, "grpc 请求异常", message);
                return message;
            }

            // 使用Service类，生成对应的futureStub
            AbstractStub futureStub = (AbstractStub) grpcClientManager.getFutureStub(grpcTaskBean.getTarget(), grpcServiceClass.getServiceClass());

            //构造request的内部类Builder对象
            Class requestClass = grpcServiceClass.getRequestClass();
            Method requestBuilder = requestClass.getMethod("newBuilder", null);
            Object builderObject = requestBuilder.invoke(null);

            //把参数匹配到Builder对象
            new JsonFormat().merge(new ByteArrayInputStream(grpcTaskBean.getBody().getBytes()), (Message.Builder) builderObject);


            //构造request对象
            Object requestObject = builderObject.getClass().getDeclaredMethod("build").invoke(builderObject);

            // 设置header
            grpcRequestHeaderHolder.setHeaders(JSONObject.parseObject(grpcTaskBean.getHeaders(), HashMap.class));

            //从futureStub中反射出真实的请求方法并调用
            ListenableFuture listenableFuture = (ListenableFuture) futureStub.getClass().getDeclaredMethod(grpcServiceClass.getMethodName(), requestObject.getClass()).invoke(futureStub, requestObject);

            listenableFuture.addListener(new Runnable() {
                @Override
                public void run() {
                    try {
                        Message response = (Message) listenableFuture.get();
                        String result = new JsonFormat().printToString(response);
                        systemDebugger.addSystemLog(traceID, "grpc 请求成功", result);

                        log.info("grpc 请求成功:  {}", result);

                    } catch (Exception e) {
                        log.error("grpc 请求异常", e);
                        systemDebugger.addSystemLog(traceID, "grpc 请求异常", ExceptionUtils.getStackTrace(e));

                    }
                }
            }, new Executor() {

                @Override
                public void execute(@NotNull Runnable command) {
                    command.run();
                }
            });

            String resultMsg = MessageFormat.format("target:{0},  method：{1}/{2},   body: {3}"
                    , grpcTaskBean.getTarget()
                    , grpcTaskBean.getServiceName()
                    , grpcTaskBean.getMethodName()
                    , grpcTaskBean.getBody());

            log.info("grpc 异步请求已发送. {}", resultMsg);
            systemDebugger.addSystemLog(traceID, "grpc 异步请求已发送", resultMsg);

            return resultMsg;

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));

            String resultMsg = String.format("Task -> grpc请求执行异常：%s === config信息：%s", ExceptionUtils.getStackTrace(e), JSONObject.toJSON(taskConfig));
            systemDebugger.addSystemLog(traceID, "grpc 请求执行异常", resultMsg);
            return resultMsg;
        }
    }


}
