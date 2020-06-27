package org.yefei.qa.mock.controllers;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.config.ResponseAdapter;
import org.yefei.qa.mock.enums.*;
import org.yefei.qa.mock.exception.ServerBaseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/7 09:47
 */
@Controller
@RequestMapping("/mock_server/enum/")
@Slf4j
public class EnumController {

    @Autowired
    private ResponseAdapter responseAdapter;

    @Autowired
    private BeanScanner beanScanner;

    @RequestMapping("/compare/list.ajax")
    @ResponseBody
    public Object compareWayList(){
        HashMap resp = new HashMap();
        List typeList = new ArrayList<>();
        try {
            Arrays.stream(CompareWayEnum.values()).forEach(compareWayEnum -> {
                HashMap type = new HashMap();
                type.put("value", compareWayEnum.getCode());
                type.put("display", compareWayEnum.getMemo());
                typeList.add(type);
            });

            resp.put("list", typeList);
            resp.put("total", typeList.size());

            return responseAdapter.success(resp);

        }catch (ServerBaseException e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }

    }



    @RequestMapping("/protocol/list.ajax")
    @ResponseBody
    public Object protocolList(){
        HashMap resp = new HashMap();
        List typeList = new ArrayList<>();
        try {
            Arrays.stream(ProtocolEnum.values()).forEach(item -> {
                HashMap type = new HashMap();
                type.put("value", item.getProtocol());
                type.put("display", item.getProtocol());
                typeList.add(type);
            });

            resp.put("list", typeList);
            resp.put("total", typeList.size());

            return responseAdapter.success(resp);

        }catch (ServerBaseException e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }

    }


    @RequestMapping("/variableSource/list.ajax")
    @ResponseBody
    public Object variableSourceList(){
        HashMap resp = new HashMap();
        List typeList = new ArrayList<>();
        try {
            Arrays.stream(VariableSourceEnum.values()).forEach(item -> {
                HashMap type = new HashMap();

                type.put("value", item.getCode());
                type.put("display", item.getMemo());

                typeList.add(type);
            });

            resp.put("list", typeList);
            resp.put("total", typeList.size());

            return responseAdapter.success(resp);

        }catch (ServerBaseException e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }

    }



    @RequestMapping("/operator/list.ajax")
    @ResponseBody
    public Object operatorList(){
        HashMap resp = new HashMap();
        List typeList = new ArrayList<>();
        try {
            Arrays.stream(OperatorEnum.values()).forEach(item -> {
                HashMap type = new HashMap();

                type.put("value", item.getOperator());
                type.put("display", item.getMemo());

                typeList.add(type);
            });

            resp.put("list", typeList);
            resp.put("total", typeList.size());

            return responseAdapter.success(resp);

        }catch (ServerBaseException e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }

    }



    @RequestMapping("/language/list.ajax")
    @ResponseBody
    public Object scriptLanguageTypes(){
        try {
            HashMap resp = new HashMap();
            List list = new ArrayList();
            Arrays.stream(ScriptLanguageEnum.values()).forEach(item->{
                HashMap type = new HashMap();

                type.put("value", item.getLanguage());
                type.put("display", item.getLanguage());

                list.add(type);
            });

            resp.put("list", list);
            return responseAdapter.success(resp);

        }catch (ServerBaseException e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }

    }


    @RequestMapping("/task/type/list.ajax")
    @ResponseBody
    public Object taskTypeFieldList(){
        HashMap resp = new HashMap();
        try {

            List list = new ArrayList();
            Arrays.stream(TaskTypeEnum.values()).forEach(item->{
                HashMap type = new HashMap();

                type.put("value", item.getTaskType());
                type.put("configType", item.getConfigType());
                type.put("display", item.getDesc());

                list.add(type);
            });

            resp.put("list", list);
            resp.put("total", list.size());

            return responseAdapter.success(resp);

        }catch (ServerBaseException e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }

    }

    @RequestMapping("/task/type/field.ajax")
    @ResponseBody
    public Object taskTypeField(@RequestBody JSONObject params){
        HashMap resp = new HashMap();
        try {
            int taskType = params.getInteger("type");
            List list = new ArrayList();
            Arrays.stream(TaskTypeEnum.values()).forEach(item->{
                if (item.getTaskType() == taskType){
                    List<BeanScanner.BeanField> beanFields = beanScanner.getBeanFields(item.getBeanSimpleName(), "data");
                    list.addAll(beanFields);
                }
            });

            resp.put("list", list);
            resp.put("total", list.size());

            return responseAdapter.success(resp);

        }catch (ServerBaseException e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }
    }

    @RequestMapping("/http/method/list.ajax")
    @ResponseBody
    public Object httpMethodField(@RequestBody JSONObject params){
        HashMap resp = new HashMap();
        try {

            List list = new ArrayList();
            Arrays.stream(HttpMethodEnum.values()).forEach(item->{
                HashMap type = new HashMap();

                type.put("value", item.getMethod());
                type.put("display", item.getMethod());

                list.add(type);
            });

            resp.put("list", list);
            resp.put("total", list.size());

            return responseAdapter.success(resp);

        }catch (ServerBaseException e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }
    }


}
