package org.yefei.qa.mock.plugin.core.handler;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author yefei
 * @date: 2020/6/27
 */
@Component
public class RestPluginExecutor {

    /**
     * 解析请求参数。
     * @param groupCode     分组
     * @param requestUri    真实的uri（不包含分组部分）
     * @param params        请求参数，同时包含body和parameters
     */
    public void parseRequestParams(String groupCode, String requestUri, HashMap params) {
        PluginContainer.plugins().forEach(plugin -> {
            plugin.parseRequestParams(groupCode, requestUri, params);
        });
    }

    /**
     * 生成最终的响应结果
     * @param groupCode     分组
     * @param requestUri    真实的uri（不包含分组部分）
     * @param params        请求参数，同时包含body和parameters
     * @param respBody      准备输出的结果，已经替换过变量
     * @return  最终输出的结果，直接输出到output流
     */
    public String buildFinalResponseContent(String groupCode, String requestUri, HashMap params, String respBody) {
        AtomicReference<String> finalResult = new AtomicReference<>(respBody);
        PluginContainer.plugins().forEach(plugin -> {
            finalResult.set(plugin.buildFinalResponseContent(groupCode, requestUri, params, finalResult.get()));
        });
        return finalResult.get();
    }

    /**
     * 生成异步回调的body
     * @param groupCode     分组
     * @param requestUri    真实的uri（不包含分组部分）
     * @param params        请求参数，同时包含body和parameters
     * @param content       回调的body，已经替换过变量
     * @return
     */
    public String buildCallbackContent(String groupCode, String requestUri, HashMap params, String content) {
        AtomicReference<String> finalResult = new AtomicReference<>(content);
        PluginContainer.plugins().forEach(plugin -> {
            finalResult.set(plugin.buildCallbackContent(groupCode, requestUri, params, finalResult.get()));
        });
        return finalResult.get();
    }

}
