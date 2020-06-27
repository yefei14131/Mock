package org.yefei.qa.mock.controllers;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.config.ResponseAdapter;
import org.yefei.qa.mock.enums.TaskTypeEnum;
import org.yefei.qa.mock.exception.ServerBaseException;
import org.yefei.qa.mock.exception.UnknownDataException;
import org.yefei.qa.mock.model.gen.pojo.TblMappingTask;
import org.yefei.qa.mock.model.gen.pojo.TblSystemConfig;
import org.yefei.qa.mock.service.IMappingTaskService;
import org.yefei.qa.mock.service.ISystemConfigService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/7 09:47
 */
@Controller
@RequestMapping("/mock_server/mapping/task")
@Slf4j
public class MappingTaskController {

    @Autowired
    private ResponseAdapter responseAdapter;

    @Autowired
    private IMappingTaskService taskService;

    @Autowired
    private BeanScanner beanScanner;

    @Autowired
    private ISystemConfigService systemConfigService;

    @Autowired
    private ObjectMapper jacksonFormater;

    @RequestMapping(value = "/list.html", method = RequestMethod.GET)
    public ModelAndView groupInfo(@RequestParam(value = "jobID", defaultValue = "0" ) int jobID ) throws IOException {

        ModelAndView view = new ModelAndView("common/task_list");

        view.addObject("jobID", jobID);

        HashMap taskTypeAndConfigList = new HashMap();

        Arrays.stream(TaskTypeEnum.values()).forEach(item->{

            List configs = new ArrayList();
            if (item.getConfigType() > 0){
                List<TblSystemConfig> systemConfigs = systemConfigService.queryTblSystemConfigList(item.getConfigType());
                systemConfigs.forEach(configItem->{
                    HashMap field = new HashMap();
                    field.put("value", configItem.getConfigID());
                    field.put("display", configItem.getConfigTitle());
                    configs.add(field);
                });
            }

            taskTypeAndConfigList.put(item.getTaskType(), configs);
        });

        view.addObject("taskTypeAndConfigList", jacksonFormater.writeValueAsString(taskTypeAndConfigList));

        return view;

    }

    @RequestMapping("/save.ajax")
    @ResponseBody
    public Object save(HttpServletRequest request, @RequestBody TblMappingTask requestTask){
        try {

            int result = taskService.saveMappingTask(requestTask);
            if (result > 0){
                return responseAdapter.success(result);
            }else{
                throw new UnknownDataException();
            }

        }catch (ServerBaseException e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }

    }

    @RequestMapping("/delete.ajax")
    @ResponseBody
    public Object delete(HttpServletRequest request, @RequestBody JSONObject params){
        try {

            int taskID = params.getInteger("taskID");

            if (taskService.deleteMappingTask(taskID)){
                return responseAdapter.success();
            }else{
                throw new UnknownDataException();
            }


        }catch (ServerBaseException e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }

    }

    @RequestMapping("/list.ajax")
    @ResponseBody
    public Object queryList(HttpServletRequest request, @RequestBody JSONObject params){
        HashMap resp = new HashMap();
        try {

            int jobID = params.getInteger("jobID");
            resp.put("list", taskService.queryMappingTaskList(jobID));
            resp.put("total", taskService.countMappingTask(jobID));

            return responseAdapter.success(resp);

        }catch (ServerBaseException e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }

    }



    @RequestMapping("/db/field.ajax")
    @ResponseBody
    public Object dBField(){
        HashMap resp = new HashMap();
        List<BeanScanner.BeanField> beanFields = new ArrayList<>();
        try {
            beanFields.addAll( beanScanner.getBeanFields("TblMappingTask") );

            resp.put("list", beanFields);
            resp.put("total", beanFields.size());

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
