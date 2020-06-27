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
import org.yefei.qa.mock.exception.ServerBaseException;
import org.yefei.qa.mock.exception.UnknownDataException;
import org.yefei.qa.mock.model.gen.pojo.TblMappingJob;
import org.yefei.qa.mock.service.IMappingJobService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/7 09:47
 */
@Controller
@RequestMapping("/mock_server/mapping/job")
@Slf4j
public class MappingJobController {

    @Autowired
    private ResponseAdapter responseAdapter;


    @Autowired
    private IMappingJobService mappingJobService;


    @Autowired
    private BeanScanner beanScanner;

    @RequestMapping("/save.ajax")
    @ResponseBody
    public Object save(HttpServletRequest request, @RequestBody TblMappingJob requestJob){
        try {
            int result = mappingJobService.saveMappingJob(requestJob);
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
            int jobID = params.getInteger("jobID");

            if (mappingJobService.deleteMappingJob(jobID)){
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

            int requestID = params.getInteger("requestID");
            String protocol = params.getString("protocol");

            resp.put("list", mappingJobService.queryMappingJobList(requestID, protocol));
            resp.put("total", mappingJobService.countMappingJob(requestID, protocol));

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
            beanFields.addAll( beanScanner.getBeanFields("TblMappingJob", "updateTime") );

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
