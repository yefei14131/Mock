package org.yefei.qa.mock.controllers.rest;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.config.ResponseAdapter;
import org.yefei.qa.mock.controllers.BaseController;
import org.yefei.qa.mock.enums.ProtocolEnum;
import org.yefei.qa.mock.enums.ResponseTypeEnum;
import org.yefei.qa.mock.exception.ServerBaseException;
import org.yefei.qa.mock.exception.UnknownDataException;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMapping;
import org.yefei.qa.mock.service.IRestMappingService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/7 09:47
 */
@Controller
@RequestMapping("/mock_server/rest/mapping")
@Slf4j
public class RestMappingController extends BaseController {

    @Autowired
    private ResponseAdapter responseAdapter;

    @Autowired
    private IRestMappingService restMappingService;

    @Autowired
    private BeanScanner beanScanner;

    private final String horizontalNav = "rest";


    @RequestMapping(value = "/list.html", method = RequestMethod.GET)
    public ModelAndView mappingList(@RequestParam("groupID") int groupID, @RequestParam("groupCode") String groupCode) throws IOException {

        ModelAndView view = new ModelAndView("rest/mapping/list");

        this.buildCategory(view, horizontalNav, "rest-group-list");
        List<BeanScanner.BeanField> fields = beanScanner.getBeanFields("TblRestRequestMapping", "updateTime", "responseBody");
        view.addObject("fields", fields);
        view.addObject("groupID", groupID);
        view.addObject("groupCode", groupCode);

        return view;

    }


    @RequestMapping(value = "/info.html", method = RequestMethod.GET)
    public ModelAndView groupInfo(@RequestParam(value = "requestID", defaultValue = "0" ) int requestID
    ,@RequestParam(value = "groupID", defaultValue = "0" ) int groupID, @RequestParam(value = "groupCode", defaultValue = "" ) String groupCode) throws IOException {

        ModelAndView view = new ModelAndView("rest/mapping/edit");

        this.buildCategory(view, horizontalNav, "rest-group-list");

        List<BeanScanner.BeanField> fields = getFields();
        view.addObject("fields", fields);
        view.addObject("requestID", requestID);
        view.addObject("responseTypes", ResponseTypeEnum.values());

        view.addObject("groupID", groupID);
        view.addObject("groupCode", groupCode);

        view.addObject("protocol", ProtocolEnum.HTTP.getProtocol());



        return view;

    }

    @RequestMapping("/get.ajax")
    @ResponseBody
    public Object get(HttpServletRequest request, @RequestBody JSONObject params){
        try {
            Integer requestID = params.getInteger("requestID");
            if (requestID == 0){
                throw new UnknownDataException();
            }

            TblRestRequestMapping mapping = restMappingService.getMapping(requestID);
            if (mapping != null){
                return responseAdapter.success(mapping);
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


    @RequestMapping("/save.ajax")
    @ResponseBody
    public Object save(HttpServletRequest request, @RequestBody TblRestRequestMapping restRequestMapping){
        try {

            int result = restMappingService.saveRestMapping(restRequestMapping);
            if (result > 0){
                return responseAdapter.success(Integer.valueOf(result));
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
            int requestID = params.getInteger("requestID");

            if (restMappingService.deleteRestMapping(requestID)){
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

            int groupID = params.getInteger("groupID");
            resp.put("list", restMappingService.queryRestMappingList(groupID));
            resp.put("total", restMappingService.countRestMapping(groupID));

            return responseAdapter.success(resp);

        }catch (ServerBaseException e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }
    }

    @RequestMapping("/clone.ajax")
    @ResponseBody
    public Object clone(HttpServletRequest request, @RequestBody JSONObject params){
        HashMap resp = new HashMap();
        try {

            int requestID = params.getInteger("requestID");
            resp.put("success", restMappingService.clone(requestID));
            return responseAdapter.success(resp);

        }catch (ServerBaseException e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }
    }

    private List<BeanScanner.BeanField> getFields(){
         return beanScanner.getBeanFields("TblRestRequestMapping", "requestID", "updateTime");
    }

}
