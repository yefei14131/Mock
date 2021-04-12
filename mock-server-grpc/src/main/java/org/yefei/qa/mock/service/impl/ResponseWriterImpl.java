package org.yefei.qa.mock.service.impl;

import com.google.protobuf.Message;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.ServerCall;
import io.grpc.Status;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.cache.GlobalDataPool;
import org.yefei.qa.mock.debugger.SystemDebugger;
import org.yefei.qa.mock.model.bean.RecordedRequest;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestMapping;
import org.yefei.qa.mock.service.IResponseWriter;
import org.yefei.qa.mock.utils.VariableManager;

import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by yefei on 2018/8/2.
 */
@Service
@Slf4j
public class ResponseWriterImpl implements IResponseWriter {

    @Autowired
    private SystemDebugger systemDebugger;

    @Autowired
    private GlobalDataPool globalDataPool;

    @Override
    public void writeResponse(RecordedRequest recordedRequest, HashMap userDefined, HashMap params, ServerCall serverCall, Metadata headers) {
        TblGrpcRequestMapping recordedRequestMapping = recordedRequest.getTblGrpcRequestMapping();


        if (headers == null)
            headers = new Metadata();

        if (recordedRequestMapping != null){
            try {

                log.info("request {}/{}, response:{} ", recordedRequestMapping.getServiceName(), recordedRequestMapping.getMethodName(), recordedRequestMapping.getResponseBody());
                systemDebugger.addSystemLog("匹配前的输出结果", recordedRequestMapping.getResponseBody());

                HashMap<String, Object> globalData = globalDataPool.getAll();
                systemDebugger.addSystemLog("全局变量", globalData);

                String outputContent = VariableManager.replaceContent(recordedRequest.getTblGrpcRequestMapping().getResponseBody(), userDefined, params, globalData);

                Class respClass = ((MethodDescriptor.PrototypeMarshaller) serverCall.getMethodDescriptor().getResponseMarshaller()).getMessagePrototype().getClass();
                Method newBuilderMethod = respClass.getDeclaredMethod("newBuilder");

                Message.Builder messageBuilder = (Message.Builder) newBuilderMethod.invoke(null);

                log.info("final response:{} ", outputContent);
                systemDebugger.addSystemLog("准备输出结果", outputContent);

                com.google.protobuf.util.JsonFormat.parser().merge(outputContent, messageBuilder);


                systemDebugger.addSystemLog("延迟输出响应结果", String.format("延迟时间 %d 毫秒", recordedRequestMapping.getResponseDelay()));
                if (recordedRequestMapping.getResponseDelay() > 0) {
                    try {
                        Thread.sleep(recordedRequestMapping.getResponseDelay());
                    } catch (InterruptedException e1) {
                        log.error("response delay sleep error ", e1);
                        systemDebugger.addSystemLog("延迟输出响应结果异常", ExceptionUtils.getStackTrace(e1));
                    }
                }

                serverCall.sendHeaders(headers);
                serverCall.sendMessage(messageBuilder.build());
                serverCall.close(Status.OK, headers);

                systemDebugger.addSystemLog("输出结果完成", outputContent);

            } catch (Exception e) {
                log.error("grpc 输出结果异常", e);
                systemDebugger.addSystemLog("输出结果异常", ExceptionUtils.getStackTrace(e));
                serverCall.close(Status.INTERNAL, headers);
            }
        }

    }


}
