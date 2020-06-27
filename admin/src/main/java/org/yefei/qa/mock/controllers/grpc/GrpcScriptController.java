package org.yefei.qa.mock.controllers.grpc;

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
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestScript;
import org.yefei.qa.mock.service.IGrpcRequestScriptService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/7 09:47
 */
@Controller
@RequestMapping("/mock_server/grpc/script")
@Slf4j
public class GrpcScriptController {

    @Autowired
    private ResponseAdapter responseAdapter;

    @Autowired
    private IGrpcRequestScriptService grpcScriptService;

    @Autowired
    private BeanScanner beanScanner;


    @RequestMapping("/get.ajax")
    @ResponseBody
    public Object get(@RequestBody JSONObject params) {
        try {

            int scriptID = params.getInteger("scriptID");

            if (scriptID <= 0) {
                throw new UnknownDataException();
            }

            TblGrpcRequestScript script = grpcScriptService.getScript(scriptID);
            if (script != null) {
                return responseAdapter.success(script);
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

    @RequestMapping("/save.ajax")
    @ResponseBody
    public Object save(@RequestBody TblGrpcRequestScript grpcRequestScript) {
        try {

            if (grpcScriptService.saveGrpcScript(grpcRequestScript)) {
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
    public Object delete(HttpServletRequest request, @RequestBody JSONObject params) {
        try {
            int scriptID = params.getInteger("scriptID");

            if (grpcScriptService.deleteGrpcScript(scriptID)) {
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

    @RequestMapping("/list.ajax")
    @ResponseBody
    public Object queryList(@RequestBody JSONObject params) {
        HashMap resp = new HashMap();
        try {

            String serviceName = params.getString("serviceName");
            String methodName = params.getString("methodName");

            resp.put("list", grpcScriptService.queryGrpcScriptList(serviceName, methodName));
            resp.put("total", grpcScriptService.countGrpcScript(serviceName, methodName));

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
            beanFields.addAll(getFields());

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

    private List<BeanScanner.BeanField> getFields() {
        return beanScanner.getBeanFields("TblGrpcRequestScript", "scriptID", "updateTime");
    }


}


