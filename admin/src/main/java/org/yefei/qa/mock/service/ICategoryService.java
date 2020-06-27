package org.yefei.qa.mock.service;

import com.alibaba.fastjson.JSONArray;

import java.io.IOException;

/**
 * @author: yefei
 * @date: 2018/10/29 13:59
 */
public interface ICategoryService {
    JSONArray getCategory(String nav) throws IOException;
}
