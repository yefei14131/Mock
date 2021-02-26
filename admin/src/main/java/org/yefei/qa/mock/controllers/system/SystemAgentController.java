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
import org.yefei.qa.mock.service.IAgentManagerService;
import org.yefei.qa.mock.service.IRequestLogQueryService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author: yefei
 * @date: 2018/10/7 09:47
 */
@Controller
@RequestMapping("/mock_server/system/agent")
@Slf4j
public class SystemAgentController extends BaseController {

    @Autowired
    private ResponseAdapter responseAdapter;

    @Autowired
    private IRequestLogQueryService logQueryService;

    @Autowired
    private BeanScanner beanScanner;

    @Autowired
    private IAgentManagerService agentManagerService;

    private final String horizontalNav = "system";

    @RequestMapping(value = "/list.html", method = RequestMethod.GET)
    public ModelAndView agentList() throws IOException {

        ModelAndView view = new ModelAndView("system/agent_list");

        this.buildCategory(view, horizontalNav, "agent-list");

        return view;
    }


    @RequestMapping("/list.ajax")
    @ResponseBody
    public Object queryList(HttpServletRequest request, @RequestBody JSONObject params) {
        HashMap resp = new HashMap();
        try {

            int page = params.getInteger("page");
            int pageSize = params.getInteger("limit");

            resp.put("list", agentManagerService.queryActiveAgentList());
            resp.put("total", agentManagerService.countActiveAgentList());

            return responseAdapter.success(resp);

        } catch (ServerBaseException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }
    }


    @RequestMapping("/active.ajax")
    @ResponseBody
    public Object saveActive(HttpServletRequest request, @RequestBody JSONObject params) {
        try {

            int ID = params.getInteger("ID");
            boolean isActive = params.getBoolean("isActive");
            agentManagerService.setActive(ID, isActive);

            return responseAdapter.success();

        } catch (ServerBaseException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }
    }


}
