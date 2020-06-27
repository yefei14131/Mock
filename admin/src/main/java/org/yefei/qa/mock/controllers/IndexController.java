package org.yefei.qa.mock.controllers;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.yefei.qa.mock.config.ResponseAdapter;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author: yefei
 * @date: 2018/10/7 09:47
 */
@Controller
@Slf4j
public class IndexController {


    @Autowired
    private ResponseAdapter responseAdapter;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index()  {

        ModelAndView view = new ModelAndView("mock_server/rest/group/list.html");
        return view;

    }

    @RequestMapping("/health")
    @ResponseBody
    public Object health(){
       // 探活接口
        HashMap resp = new HashMap<>();
        resp.put("code", 200);
        resp.put("message", "SUCCESS");

        return resp;
    }

    @ResponseBody
    @RequestMapping("/test")
    public Object test(HttpServletRequest request) {

        try {

            HttpPost httpPost = new HttpPost(request.getParameter("url"));
            HttpGet httpGet = new HttpGet(request.getParameter("url"));

            Enumeration<String> h = request.getHeaderNames();
            if (h.hasMoreElements()) {
                String headerName = h.nextElement();
                httpPost.setHeader(headerName, request.getHeader(headerName));
            }

//            url = "http://dohko.rest.mock.tp.hualala.com/test/callback";


            Set<Map.Entry<String, String[]>> bodyEntries = request.getParameterMap().entrySet();
            if (bodyEntries != null) {

                HashMap params = new HashMap();

                for (Map.Entry entry : bodyEntries) {
                    String key = entry.getKey().toString();
                    String[] valueArr = (String[]) entry.getValue();
                    params.put(key, valueArr.length == 1 ? valueArr[0] : valueArr);
                }

                String content = JSONObject.toJSONString(params);
                BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
                basicHttpEntity.setContent(new ByteArrayInputStream(content.getBytes()));
                httpPost.setEntity(basicHttpEntity);
                httpPost.setHeader("Content-Type", "application/json");
            }

            CloseableHttpResponse response = HttpClientBuilder.create().build().execute(httpGet);

            String result = EntityUtils.toString(response.getEntity(), "UTF-8");

            return result;
        } catch (Exception e) {
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }
    }

}
