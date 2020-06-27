package org.yefei.qa.mock.config.httpclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yefei.qa.mock.enums.HttpMethodEnum;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author: yefei
 * @date: 2018/9/26 21:46
 */
@Component
public class HttpClient {

    @Autowired
    private CloseableHttpClient httpClient;

    @Autowired
    private RequestConfig config;

    @Resource
    private ObjectMapper jacksonFormatter;

    /**
     * 不带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    public HttpResult doGet(String url) throws Exception {
        return doGet(url, null);
    }

    /**
     * 带参数的get请求，如果状态码为200，则返回body，如果不为200，则返回null
     *
     * @param url
     * @return
     * @throws Exception
     */
    public HttpResult doGet(String url, Map<String, String> headers) throws Exception {
        // 声明 http get 请求
        HttpGet httpGet = new HttpGet(url);

        // 装载配置信息
        httpGet.setConfig(config);

        if(headers != null && !headers.isEmpty()){
            headers.entrySet().stream().forEach( entry->{
                httpGet.setHeader(entry.getKey(), entry.getValue());
            });
        }

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpGet);


        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(), "UTF-8"));
    }


    public HttpResult doGet(String url, Map<String, String> headers, Map<String, Object> params) throws Exception {
        URIBuilder uriBuilder = new URIBuilder(url);

        if (params != null) {
            // 遍历map,拼接请求参数
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                uriBuilder.setParameter(entry.getKey(), entry.getValue().toString());
            }
        }

        // 调用不带参数的get请求
        return this.doGet(uriBuilder.build().toString(), headers);

    }



    /**
     * 带参数的post请求
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public HttpResult doPost(String url, Map<String, Object> params) throws Exception {
        return doPost(url, null, params);
    }


    public HttpResult doPost(String url, Map<String, String> headers, Map<String, Object> params) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);

        if(headers != null && !headers.isEmpty()){
            headers.entrySet().stream().forEach( entry->{
                httpPost.setHeader(entry.getKey(), entry.getValue());
            });
        }

        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (params != null) {
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
            }
            // 构造from表单对象
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(list, "UTF-8");

            // 把表单放到post里
            httpPost.setEntity(urlEncodedFormEntity);
        }



        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(), "UTF-8"));
    }

    // requestBody 为string， content-type为json时可以使用
    public HttpResult doPost(String url, Map<String, String> headers, String requestBody) throws Exception {
        // 声明httpPost请求
        HttpPost httpPost = new HttpPost(url);
        // 加入配置信息
        httpPost.setConfig(config);

        if(headers != null && !headers.isEmpty()){
            headers.entrySet().stream().forEach( entry->{
                httpPost.setHeader(entry.getKey(), entry.getValue());
            });
        }

        // 判断map是否为空，不为空则进行遍历，封装from表单对象
        if (requestBody != null) {
            StringEntity stringEntity = new StringEntity(requestBody);
            httpPost.setEntity(stringEntity);
        }

        // 发起请求
        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        return new HttpResult(response.getStatusLine().getStatusCode(), EntityUtils.toString(response.getEntity(), "UTF-8"));
    }


    public HttpResult send(String url, String method, Map<String, String> headers, String requestBody) throws Exception {
        Map params = null;

        if (StringUtils.isNotEmpty(requestBody) && !isJsonContentType(headers)) {
            // contentType 非 json 时，序列化成map
            params = jacksonFormatter.readValue(requestBody, Map.class);
        }

        if (StringUtils.isEmpty(method) || HttpMethodEnum.GET.getMethod().equals(method.toLowerCase())) {
            return this.doGet(url, headers, params);

        }else if (HttpMethodEnum.POST.getMethod().equals(method.toLowerCase())){
            if (params != null){
                return this.doPost(url, headers, params);
            }else{
                return this.doPost(url, headers, requestBody);
            }
        }

        return null;
    }


    private boolean isJsonContentType(Map<String, String> headers){
       AtomicBoolean flag = new AtomicBoolean(false);
        if (headers != null && headers.size() > 0){
            headers.forEach((k, v)->{
                if (k.toLowerCase().contains("content-type") && v.toLowerCase().contains("json") ){
                    flag.set(true);
                }
            });
        }
        return flag.get();
    }
}

