package org.yefei.qa.mock.controllers.tools;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.yefei.qa.mock.config.ResponseAdapter;
import org.yefei.qa.mock.controllers.BaseController;
import org.yefei.qa.mock.exception.ServerBaseException;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author: yefei
 * @date: 2018/10/7 09:47
 */
@Controller
@Slf4j
@RequestMapping("/mock_server/tools")
public class ToolsController extends BaseController {

    private final String nav = "tools";

    @Autowired
    private ResponseAdapter responseAdapter;

    @RequestMapping(value = "/{tool}.html", method = RequestMethod.GET)
    public ModelAndView allM(@PathVariable String tool) throws IOException {
        ModelAndView view = new ModelAndView("tools/" + tool);
        this.buildCategory(view, nav);
        return view;

    }

    @RequestMapping("/shard.action")
    @ResponseBody
    public Object shard(@RequestParam(name = "orderKey", defaultValue = "0") long orderKey) {

        try {
            HashMap result = new HashMap();
            result.put("dbModOnline", orderKey % 64);
            result.put("tblModOnline", orderKey / 64 % 64);
            result.put("dbModDohko", orderKey % 2);
            result.put("tblModDohko", orderKey / 2 % 2);
            result.put("orderKey", "" + orderKey);

            return responseAdapter.success(result);
        } catch (ServerBaseException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }
    }

}
