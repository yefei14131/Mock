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

    /**
     * 导航
     *
     * @param modelAndView  model对象
     * @param horizontalNav 当前水平的key
     * @throws IOException
     */
    public void buildCategory(ModelAndView modelAndView, String horizontalNav) throws IOException {
        this.buildCategory(modelAndView, horizontalNav, "");
    }

    /**
     * 导航
     *
     * @param modelAndView  model对象
     * @param horizontalNav 当前水平的key
     * @param verticalNav   当前垂直导航的key
     * @throws IOException
     */
    public void buildCategory(ModelAndView modelAndView, String horizontalNav, String verticalNav) throws IOException {
        JSONArray category = this.categoryService.getCategory(horizontalNav);
        modelAndView.addObject("category", category);
        modelAndView.addObject("horizontalNav", horizontalNav);
        modelAndView.addObject("verticalNav", verticalNav);
    }
}
