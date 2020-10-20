package org.yefei.qa.mock.controllers;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yefei.qa.mock.config.ResponseAdapter;
import org.yefei.qa.mock.exception.ServerBaseException;
import org.yefei.qa.mock.exception.UnknownDataException;
import org.yefei.qa.mock.model.gen.pojo.TblMappingGlobalVar;
import org.yefei.qa.mock.service.IMappingGlobalVarService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author: yefei
 * @date: 2018/10/7 09:47
 */
@Controller
@RequestMapping("/mock_server/mapping/global_var")
@Slf4j
public class MappingGlobalVarController {

    @Autowired
    private ResponseAdapter responseAdapter;

    @Autowired
    private IMappingGlobalVarService mappingGlobalVarService;

    @RequestMapping("/save.ajax")
    @ResponseBody
    public Object save(HttpServletRequest request, @RequestBody TblMappingGlobalVar tblMappingGlobalVar) {
        try {

            if (mappingGlobalVarService.saveGlobalVar(tblMappingGlobalVar)) {
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
            int globalVarID = params.getInteger("globalVarID");

            if (mappingGlobalVarService.deleteGlobalVar(globalVarID)) {
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
    public Object queryList(HttpServletRequest request, @RequestBody JSONObject params) {
        HashMap resp = new HashMap();
        try {

            int requestID = params.getInteger("requestID");
            String protocol = params.getString("protocol");

            resp.put("list", mappingGlobalVarService.queryGlobalVarList(requestID, protocol));
            resp.put("total", 10);

            return responseAdapter.success(resp);

        } catch (ServerBaseException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }
    }

}
