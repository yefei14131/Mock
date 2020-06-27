package org.yefei.qa.mock.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.service.ICategoryService;
import org.yefei.qa.mock.utils.FileUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: yefei
 * @date: 2018/10/4 22:16
 */
@Service
public class CategoryServiceImpl implements ICategoryService {

    private JSONObject category;

    @Autowired
    private ObjectMapper jacksonFormatter;

    private synchronized void genCategory() throws IOException {
        if (category == null) {
            InputStream stream = getClass().getClassLoader().getResourceAsStream("static/json/category.json");

            JSONObject jsonObject = jacksonFormatter.readValue(FileUtil.readContent(stream), JSONObject.class);
            this.category = jsonObject;
        }
    }

    @Override
    public JSONArray getCategory(String nav) throws IOException {
        if (this.category == null){
            genCategory();
        }

        return category.getJSONArray(nav);
    }
}
