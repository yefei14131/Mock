package org.yefei.qa.mock.plugin.core.define;

import org.yefei.qa.mock.plugin.core.handler.PluginContainer;

import java.util.HashMap;

/**
 * @author yefei
 * @date: 2020/6/27
 */
public abstract class AbstractRestServerPlugin {

    /**
     * 解析请求参数。
     * @param groupCode     分组
     * @param requestUri    真实的uri（不包含分组部分）
     * @param params        请求参数，同时包含body和parameters
     */
    public abstract void parseRequestParams(String groupCode, String requestUri, HashMap params);

    /**
     * 生成最终的响应结果
     * @param groupCode     分组
     * @param requestUri    真实的uri（不包含分组部分）
     * @param params        请求参数，同时包含body和parameters
     * @param respBody      准备输出的结果，已经替换过变量
     * @return  最终输出的结果，直接输出到output流
     */
    public abstract String buildFinalResponseContent(String groupCode, String requestUri, HashMap params, String respBody);

    /**
     * 生成异步回调的body
     * @param groupCode     分组
     * @param requestUri    真实的uri（不包含分组部分）
     * @param params        请求参数，同时包含body和parameters
     * @param content       回调的body，已经替换过变量
     * @return
     */
    public abstract String buildCallbackContent(String groupCode, String requestUri, HashMap params, String content);

    public AbstractRestServerPlugin() {
        PluginContainer.regist(this);
    }

}
