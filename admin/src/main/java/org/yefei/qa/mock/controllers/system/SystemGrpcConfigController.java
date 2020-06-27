package org.yefei.qa.mock.controllers.system;

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
import org.yefei.qa.mock.exception.ServerBaseException;
import org.yefei.qa.mock.exception.UnknownDataException;
import org.yefei.qa.mock.grpc.handler.GrpcServiceProvider;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcInterfaceJar;
import org.yefei.qa.mock.service.ISystemGrpcConfigService;

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
@RequestMapping("/mock_server/system/grpc/interface")
@Slf4j
public class SystemGrpcConfigController extends BaseController {

    @Autowired
    private ResponseAdapter responseAdapter;

    @Autowired
    private BeanScanner beanScanner;

    @Autowired
    private ISystemGrpcConfigService systemGrpcConfigService;

    @Autowired
    private GrpcServiceProvider grpcServiceProvider;

    private final String nav = "grpc";

    @RequestMapping(value = "/list.html", method = RequestMethod.GET)
    public ModelAndView list() throws IOException {

        ModelAndView view = new ModelAndView("grpc/interface/interface_list");

        this.buildCategory(view, nav);
        List<BeanScanner.BeanField> fields = beanScanner.getBeanFields("TblGrpcInterfaceJar", "updateTime");
        view.addObject("fields", fields);

        return view;
    }


    @RequestMapping("/list.ajax")
    @ResponseBody
    public Object queryList(HttpServletRequest request, @RequestBody JSONObject params) {
        HashMap resp = new HashMap();
        try {

            int page = params.getInteger("page");
            int pageSize = params.getInteger("limit");

            resp.put("list", systemGrpcConfigService.queryGrpcInterfaceList(page, pageSize));
            resp.put("total", systemGrpcConfigService.countGrpcInterfaceList());

            return responseAdapter.success(resp);

        } catch (ServerBaseException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }
    }

    @RequestMapping("/db/field.ajax")
    @ResponseBody
    public Object dBField() {
        HashMap resp = new HashMap();
        List<BeanScanner.BeanField> beanFields = new ArrayList<>();
        try {
            beanFields.addAll(beanScanner.getBeanFields("TblGrpcInterfaceJar", "updateTime"));

            resp.put("list", beanFields);
            resp.put("total", beanFields.size());

            return responseAdapter.success(resp);

        } catch (ServerBaseException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }
    }


    @RequestMapping(value = "/info.html", method = RequestMethod.GET)
    public ModelAndView item(@RequestParam(name = "itemID", defaultValue = "0") int itemID) throws IOException {

        ModelAndView view = new ModelAndView("grpc/interface/interface_edit");

        this.buildCategory(view, nav);
        List<BeanScanner.BeanField> fields = beanScanner.getBeanFields("TblGrpcInterfaceJar", "updateTime");
        view.addObject("fields", fields);
        view.addObject("itemID", itemID);

        return view;
    }


    @RequestMapping("/get.ajax")
    @ResponseBody
    public Object ajaxItem(@RequestBody JSONObject param) {
        int ID = param.getInteger("itemID");

        try {
            if (ID <= 0) {
                throw new Exception("ID不能小于0");
            }

            return responseAdapter.success(systemGrpcConfigService.get(ID));

        } catch (ServerBaseException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }
    }


    @RequestMapping("/save.ajax")
    @ResponseBody
    public Object save(@RequestBody TblGrpcInterfaceJar interfaceJar) {
        try {

            if (systemGrpcConfigService.saveGrpcInterface(interfaceJar)) {
                return responseAdapter.success();
            } else {
                throw new UnknownDataException();
            }
        } catch (ServerBaseException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }

    }

    @RequestMapping("/delete.ajax")
    @ResponseBody
    public Object delete(@RequestBody JSONObject params) {
        try {
            int ID = params.getInteger("itemID");

            if (systemGrpcConfigService.delete(ID)) {
                return responseAdapter.success();
            } else {
                throw new UnknownDataException();
            }

        } catch (ServerBaseException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }
    }


    @RequestMapping("/all_method.ajax")
    @ResponseBody
    public Object getAllGrpcMethod() {
        try {
            return responseAdapter.success(grpcServiceProvider.getGrpcAllMethod());

        } catch (ServerBaseException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }
    }

}
