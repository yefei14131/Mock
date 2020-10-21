package org.yefei.qa.mock.controllers;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yefei.qa.mock.config.ResponseAdapter;
import org.yefei.qa.mock.debugger.SystemDebugger;
import org.yefei.qa.mock.enums.ProtocolEnum;
import org.yefei.qa.mock.exception.CannotMatchRequestException;
import org.yefei.qa.mock.exception.ServerBaseException;
import org.yefei.qa.mock.exception.WelcomeException;
import org.yefei.qa.mock.model.bean.RecordedRequest;
import org.yefei.qa.mock.plugin.core.handler.RestPluginExecutor;
import org.yefei.qa.mock.service.IMappingJobService;
import org.yefei.qa.mock.service.IRequestMacther;
import org.yefei.qa.mock.service.IRequestScriptService;
import org.yefei.qa.mock.service.IResponseWriter;
import org.yefei.qa.mock.service.impl.GlobalDataService;
import org.yefei.qa.mock.utils.RequestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class IndexController {

    @Autowired
    private IRequestMacther requestMatcher;

    @Autowired
    private IResponseWriter responseWriter;

    @Autowired
    private IRequestScriptService requestScriptService;

    @Autowired
    private IMappingJobService requestJobService;

    @Autowired
    private RequestUtils requestUtils;

    @Autowired
    private SystemDebugger systemDebugger;

    @Autowired
    private ResponseAdapter responseAdapter;

    @Autowired
    private RestPluginExecutor restPluginExecutor;

    @Autowired
    private GlobalDataService globalDataService;

    @RequestMapping("/**")
    public void index(HttpServletRequest request, HttpServletResponse response) {
        try {

            String uri = request.getRequestURI();

            //兼容多个/的情况
            uri = uri.replaceAll("/+", "/");

            if( ! uri.matches("^/.+?/.*") ){
                throw new WelcomeException();
            }

            systemDebugger.init(uri);

            String[] pathArr = uri.split("/");
            String groupCode = pathArr[1];
            String path = uri.substring(groupCode.length() + 1);


            // 准备 parameters
            HashMap params = new HashMap<>();
            String contentType = request.getContentType();

            if (org.apache.commons.lang3.StringUtils.isNotBlank(contentType) && contentType.contains("json")){
                // application/json;charset=UTF-8
                params = requestUtils.getRequestBody(request);

            }

            for (Map.Entry entry : request.getParameterMap().entrySet()) {
                String key = entry.getKey().toString();
                String[] valueArr = (String[]) entry.getValue();
                params.put(key, valueArr.length == 1 ? valueArr[0] : valueArr);
            }

            // TODO 后序列化
            systemDebugger.addSystemLog("解析请求参数", params);

            restPluginExecutor.parseRequestParams(groupCode, path, params);
            systemDebugger.addSystemLog("执行插件后的参数", params);

            // 准备 headers
            HashMap<String, String> headers = new HashMap<>();
            Enumeration<String> headerNames = request.getHeaderNames();
            while (headerNames.hasMoreElements()){
                String key = headerNames.nextElement();
                String value = request.getHeader(key);
                headers.put(key, value);
            }

            systemDebugger.addSystemLog("解析请求header", headers);

            // 准备 cookies
            HashMap<String, String> cookies = new HashMap<>();
            Cookie[] cookie = request.getCookies();
            if( null != cookie) {
                for (Cookie c : cookie) {
                    cookies.put(c.getName(), c.getValue());
                }
            }
            systemDebugger.addSystemLog("解析请求cookie", cookies);

            //TODO  restful接口，path中包含变量  /order/${orderKey}/detail

            HashMap userDefined = new HashMap();

            // 执行用户自定义脚本
            requestScriptService.execScripts(groupCode, path, userDefined, params, headers, cookies);


            // 匹配命中记录
            List<RecordedRequest> recordedRequests = requestMatcher.loadRecordedRequestList(groupCode, path);
            systemDebugger.addSystemLog("包含path的记录数", String.valueOf(recordedRequests.size()));

            RecordedRequest recordedRequest = requestMatcher.matchRecordedRequest(recordedRequests, userDefined, params, headers, cookies);

            if (null == recordedRequest){
                systemDebugger.addSystemLog("没有符合条件的匹配记录", "0");
                throw new CannotMatchRequestException(uri);
            }

            systemDebugger.addSystemLog("path命中的记录为", recordedRequest.getTblRestRequestMapping());

            // 保存全局变量
            globalDataService.saveGlobalVars(recordedRequest.getTblRestRequestMapping().getRequestID(), ProtocolEnum.HTTP, userDefined, params);

            // 发送异步任务
            requestJobService.addJobs(recordedRequest.getTblRestRequestMapping(), userDefined, params, headers, cookies);

            // 输出返回结果
            responseWriter.writeResponse(recordedRequest, userDefined, params, response);

        }catch (WelcomeException e){
            responseAdapter.failure(response, e);

        }catch (ServerBaseException e){
            log.error(e.getMessage());
            systemDebugger.addSystemLog("业务异常", e.getMessage());
            responseAdapter.failure(response, e);
        } catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            systemDebugger.addSystemLog("程序处理异常", ExceptionUtils.getStackTrace(e));
            responseAdapter.failure(response, ExceptionUtils.getStackTrace(e));
        }
    }


}
