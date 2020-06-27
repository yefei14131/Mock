package org.yefei.qa.mock.controllers.system;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.config.ResponseAdapter;
import org.yefei.qa.mock.controllers.BaseController;
import org.yefei.qa.mock.exception.ServerBaseException;
import org.yefei.qa.mock.model.gen.pojo.TblRequestEvent;
import org.yefei.qa.mock.service.IRequestLogQueryService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/7 09:47
 */
@Controller
@RequestMapping("/mock_server/system/log")
@Slf4j
public class SystemLogController extends BaseController {

    @Autowired
    private ResponseAdapter responseAdapter;

    @Autowired
    private IRequestLogQueryService logQueryService;

    @Autowired
    private BeanScanner beanScanner;


    private final String nav = "system";

    @RequestMapping(value = "/list.html", method = RequestMethod.GET)
    public ModelAndView mappingList() throws IOException {

        ModelAndView view = new ModelAndView("system/log_main_list");

        this.buildCategory(view, nav);
        List<BeanScanner.BeanField> fields = beanScanner.getBeanFields("TblRequestLog", "requestID", "updateTime", "responseTime");
        view.addObject("fields", fields);

        return view;
    }


    @RequestMapping("/list.ajax")
    @ResponseBody
    public Object queryList(HttpServletRequest request, @RequestBody JSONObject params){
        HashMap resp = new HashMap();
        try {

            int page = params.getInteger("page");
            int pageSize = params.getInteger("limit");

            resp.put("list", logQueryService.queryRequestLog(page, pageSize));
            resp.put("total", logQueryService.countRequestLog());

            return responseAdapter.success(resp);

        }catch (ServerBaseException e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }
    }


    @RequestMapping("/event/list.ajax")
    @ResponseBody
    public Object queryEventList(HttpServletRequest request, @RequestBody JSONObject params){
        HashMap resp = new HashMap();
        try {

            String traceID = params.getString("traceID");

            List<TblRequestEvent> list = logQueryService.queryRequestEventList(traceID);
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


    @RequestMapping("/db/field.ajax")
    @ResponseBody
    public Object dBField(){
        HashMap resp = new HashMap();
        List<BeanScanner.BeanField> beanFields = new ArrayList<>();
        try {
            beanFields.addAll( beanScanner.getBeanFields("TblRequestLog", "updateTime") );

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
