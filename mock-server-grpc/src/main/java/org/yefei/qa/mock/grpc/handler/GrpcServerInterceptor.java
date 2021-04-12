package org.yefei.qa.mock.grpc.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;
import io.grpc.*;
import io.grpc.internal.GrpcUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.debugger.SystemDebugger;
import org.yefei.qa.mock.enums.ProtocolEnum;
import org.yefei.qa.mock.exception.CannotMatchRequestException;
import org.yefei.qa.mock.model.bean.RecordedRequest;
import org.yefei.qa.mock.service.IMappingJobService;
import org.yefei.qa.mock.service.IRequestMacther;
import org.yefei.qa.mock.service.IRequestScriptService;
import org.yefei.qa.mock.service.IResponseWriter;
import org.yefei.qa.mock.service.impl.GlobalDataService;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author yefei
 * @date: 2020/3/31
 */
@Slf4j
@Component
public class GrpcServerInterceptor implements ServerInterceptor {

    @Autowired
    private IResponseWriter responseWriter;

    @Autowired
    private IRequestMacther requestMatcher;

    @Autowired
    private IRequestScriptService requestScriptService;

    @Autowired
    private IMappingJobService requestJobService;

    @Autowired
    private SystemDebugger systemDebugger;

    @Autowired
    private GlobalDataService globalDataService;

    private List<String> businessMethodList = Lists.newArrayList("mock.server.grpc.VariableService/PreDefine");

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        ServerCall.Listener<ReqT> listener = next.startCall(call, headers);

        String fullMethodName = call.getMethodDescriptor().getFullMethodName();
        AtomicBoolean inWhite = new AtomicBoolean();
        if (businessMethodList.contains(fullMethodName)) {
            // grpc服务的业务服务
            inWhite.set(true);
        }

        return new ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(listener) {
            @Override
            public void onMessage(ReqT message) {
                String fullMethodName = call.getMethodDescriptor().getFullMethodName();

                if (inWhite.get()) {
                    // grpc服务的业务服务
                    this.delegate().onMessage(message);
                    return;
                }

                systemDebugger.init(fullMethodName);
                try {
                    String inputJson = JsonFormat.printer().print((MessageOrBuilder) message);
                    log.info("{} 请求参数：{}", fullMethodName, inputJson);
                    // 准备 parameters
                    HashMap params = new ObjectMapper().readValue(inputJson, HashMap.class);

                    systemDebugger.addSystemLog("解析请求参数", inputJson);

                    log.info("grpc timeout: {}", headers.get(GrpcUtil.TIMEOUT_KEY));

                    systemDebugger.addSystemLog("全局变量", globalDataService.getGlobalVars());

                    // 自定义变量
                    HashMap userDefined = new HashMap();

                    // 执行用户自定义脚本
                    requestScriptService.execScripts(fullMethodName, userDefined, params);

                    // 匹配命中记录
                    List<RecordedRequest> recordedRequests = requestMatcher.loadRecordedRequestList(fullMethodName);
                    systemDebugger.addSystemLog("包含path的记录数", String.valueOf(recordedRequests.size()));

                    RecordedRequest recordedRequest = requestMatcher.matchRecordedRequest(recordedRequests, userDefined, params);

                    if (null == recordedRequest) {
                        throw new CannotMatchRequestException(fullMethodName);
                    }

                    systemDebugger.addSystemLog("path命中的记录为", recordedRequest.getTblGrpcRequestMapping());

                    // 保存全局变量
                    globalDataService.saveGlobalVars(recordedRequest.getTblGrpcRequestMapping().getRequestID(), ProtocolEnum.GRPC, userDefined, params);

                    // 发送异步任务
                    requestJobService.addJobs(recordedRequest.getTblGrpcRequestMapping(), userDefined, params);

                    // 输出返回结果
                    responseWriter.writeResponse(recordedRequest, userDefined, params, call, headers);

                } catch (CannotMatchRequestException e) {
                    call.close(Status.UNIMPLEMENTED, headers);
                    systemDebugger.addSystemLog("没有符合条件的匹配记录", "0");
                    log.info("没有符合条件的匹配记录");
                } catch (Exception e) {
                    log.error("grpc server mock error", e);
                    call.close(Status.INTERNAL, headers);
                    systemDebugger.addSystemLog("grpc执行异常", ExceptionUtils.getStackTrace(e));
                }
            }

            @Override
            public void onHalfClose() {
                if (inWhite.get()) {
                    // grpc服务的业务服务
                    this.delegate().onHalfClose();
                    return;
                }
            }
        };
    }
}
