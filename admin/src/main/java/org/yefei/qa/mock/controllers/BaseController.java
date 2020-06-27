package org.yefei.qa.mock.controllers;

import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.yefei.qa.mock.service.ICategoryService;

import java.io.IOException;

/**
 * @author: yefei
 * @date: 2018/10/29 14:15
 */
public class BaseController {

    @Autowired
    protected ICategoryService categoryService;

    public void buildCategory(ModelAndView modelAndView, String nav) throws IOException {
        JSONArray category = this.categoryService.getCategory(nav);
        modelAndView.addObject("category", category);

    }
}
