package org.yefei.qa.mock.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.cache.GlobalDataPool;
import org.yefei.qa.mock.debugger.SystemDebugger;
import org.yefei.qa.mock.enums.ResponseTypeEnum;
import org.yefei.qa.mock.model.bean.RecordedRequest;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMapping;
import org.yefei.qa.mock.plugin.core.handler.RestPluginExecutor;
import org.yefei.qa.mock.service.IResponseWriter;
import org.yefei.qa.mock.utils.VariableManager;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by yefei on 2018/8/2.
 */
@Service
@Slf4j
public class ResponseWriterImpl implements IResponseWriter{

    @Autowired
    private SystemDebugger systemDebugger;

    @Autowired
    private RestPluginExecutor restPluginExecutor;

    @Autowired
    private GlobalDataPool globalDataPool;


    @Override
    public void writeResponse(RecordedRequest recordedRequest, HashMap userDefined, HashMap params, HttpServletResponse response) throws IOException {
        TblRestRequestMapping recordedRequestMapping = recordedRequest.getTblRestRequestMapping();

        if (recordedRequestMapping != null){
            String responseType = recordedRequestMapping.getResponseType();

            for ( ResponseTypeEnum e :  ResponseTypeEnum.values()){
                if(e.getType().equals(responseType)){
                    response.setContentType(e.getContentType());

                    systemDebugger.addSystemLog("延迟输出响应结果", String.format("延迟时间 %d 毫秒", recordedRequestMapping.getResponseDelay()));
                    if (recordedRequestMapping.getResponseDelay() > 0) {
                        try {
                            Thread.sleep(recordedRequestMapping.getResponseDelay());
                        } catch (InterruptedException e1) {
                            log.error("response delay sleep error", e1);
                            systemDebugger.addSystemLog("延迟输出响应结果异常", ExceptionUtils.getStackTrace(e1));
                        }
                    }

                    writeContent(response, recordedRequestMapping.getResponseBody(), recordedRequestMapping, userDefined, params);
                    log.info("request uri: {}, response:{} ", recordedRequestMapping.getPath(), recordedRequestMapping.getResponseBody());
                }
            }
        }

    }

    private void writeContent(HttpServletResponse response, String content, TblRestRequestMapping recordedRequestMapping, HashMap userDefined, HashMap params) throws IOException {
        systemDebugger.addSystemLog("准备输出响应结果", content);
        HashMap<String, Object> preDefine = globalDataPool.getAll();
        systemDebugger.addSystemLog("预定义变量", preDefine);

        String outputContent = VariableManager.replaceContent(content, userDefined, params, preDefine);
        outputContent = restPluginExecutor.buildFinalResponseContent(recordedRequestMapping.getGroupCode(), recordedRequestMapping.getPath(), params, outputContent);
        log.info("response:{} ", outputContent);
        systemDebugger.addSystemLog("最终输出响应结果", outputContent);
        response.getWriter().write(outputContent);
        response.flushBuffer();

    }

}
