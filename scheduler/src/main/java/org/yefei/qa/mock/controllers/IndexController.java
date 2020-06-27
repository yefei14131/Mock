package org.yefei.qa.mock.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @author: yefei
 * @date: 2018/10/7 09:47
 */
@Controller
@Slf4j
public class IndexController {

    @RequestMapping("/health")
    @ResponseBody
    public Object health(){
       // 探活接口
        HashMap resp = new HashMap<>();
        resp.put("code", 200);
        resp.put("message", "SUCCESS");

        return resp;
    }

}
