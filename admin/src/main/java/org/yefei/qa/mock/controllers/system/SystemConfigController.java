package org.yefei.qa.mock.controllers.system;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.yefei.qa.mock.enums.SystemConfigTypeEnum;
import org.yefei.qa.mock.exception.ServerBaseException;
import org.yefei.qa.mock.exception.UnknownDataException;
import org.yefei.qa.mock.model.bean.config.MockServerHostConfigBean;
import org.yefei.qa.mock.model.gen.pojo.TblSystemConfig;
import org.yefei.qa.mock.service.ISystemConfigService;

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
@RequestMapping("/mock_server/system")
@Slf4j
public class SystemConfigController extends BaseController{

    @Autowired
    private ResponseAdapter responseAdapter;

    @Autowired
    private ISystemConfigService systemConfigService;

    @Autowired
    private BeanScanner beanScanner;

    @Autowired
    private ObjectMapper jacksonFormater;

    private final String horizontalNav = "system";

    @RequestMapping(value = "/config.html", method = RequestMethod.GET)
    public ModelAndView config() throws IOException {

        ModelAndView view = new ModelAndView("system/system_config_edit");
        view.addObject("configs", SystemConfigTypeEnum.values());
        for (SystemConfigTypeEnum item : SystemConfigTypeEnum.values()){
            item.getTypeID();
            item.getTypeName();
        }
        this.buildCategory(view, horizontalNav, "system-config");
        return view;

    }
    @RequestMapping("/save.ajax")
    @ResponseBody
    public Object save(@RequestBody TblSystemConfig systemConfig) {
        try {

            if (systemConfigService.saveSystemConfig(systemConfig)) {
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
    public Object delete( @RequestBody JSONObject params) {
        try {

            int configID = params.getInteger("configID");

            if (systemConfigService.deleteSystemConfig(configID)) {
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

            int configType = params.getInteger("type");
            resp.put("list", systemConfigService.queryTblSystemConfigList(configType));
            resp.put("total", systemConfigService.countTblSystemConfig(configType));

            return responseAdapter.success(resp);

        } catch (ServerBaseException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }

    }

//
//    @RequestMapping("/list_by_task_type.ajax")
//    @ResponseBody
//    public Object queryListByTaskType(@RequestBody JSONObject params) {
//        HashMap resp = new HashMap();
//        try {
//
//            int configType = params.getInteger("taskType");
//            resp.put("list", systemConfigService.queryTblSystemConfigList(configType));
//            resp.put("total", systemConfigService.countTblSystemConfig(configType));
//
//            return responseAdapter.success(resp);
//
//        } catch (ServerBaseException e) {
//            log.error(ExceptionUtils.getStackTrace(e));
//            return responseAdapter.failure(e);
//
//        } catch (Exception e) {
//            log.error(ExceptionUtils.getStackTrace(e));
//            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
//        }
//
//    }


    @RequestMapping("/type/list.ajax")
    @ResponseBody
    public Object configTypeList() {
        HashMap resp = new HashMap();
        List typeList = new ArrayList<>();
        try {
            Arrays.stream(SystemConfigTypeEnum.values()).forEach(systemConfigTypeEnum -> {
                HashMap type = new HashMap();
                type.put("type", systemConfigTypeEnum.getTypeID());
                type.put("typeName", systemConfigTypeEnum.getTypeName());
                typeList.add(type);
            });

            resp.put("list", typeList);
            resp.put("total", typeList.size());

            return responseAdapter.success(resp);

        } catch (ServerBaseException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }

    }

    @RequestMapping("/type/field.ajax")
    @ResponseBody
    public Object configTypeField(@RequestBody JSONObject params) {
        HashMap resp = new HashMap();
        List<BeanScanner.BeanField> beanFields = new ArrayList<>();
        try {
            int type = params.getInteger("type");

            Arrays.stream(SystemConfigTypeEnum.values()).forEach(systemConfigTypeEnum -> {

                if (systemConfigTypeEnum.getTypeID() == type) {
                    String beanSimpleName = systemConfigTypeEnum.getBeanSimpleName();
                    beanFields.addAll(beanScanner.getBeanFields(beanSimpleName));
                }
            });

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


    @RequestMapping("/db/field.ajax")
    @ResponseBody
    public Object dBField() {
        HashMap resp = new HashMap();
        List<BeanScanner.BeanField> beanFields = new ArrayList<>();
        try {
            beanFields.addAll(beanScanner.getBeanFields("TblSystemConfig", "configID", "updateTime"));

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


    @RequestMapping("/server/get.ajax")
    @ResponseBody
    public Object getServer(@RequestBody JSONObject params) {
        try {

            String protocol = params.getString("protocol");
            List<TblSystemConfig> configs = systemConfigService.queryTblSystemConfigList(SystemConfigTypeEnum.SERVER_HOST.getTypeID());
            for (TblSystemConfig config : configs){
                String configData = config.getConfigData();
                MockServerHostConfigBean configBean = jacksonFormater.readValue(configData, MockServerHostConfigBean.class);
                if (protocol.equals(configBean.getProtocol())) {
                    return responseAdapter.success(configBean);
                }
            }

            return responseAdapter.failure("查不到对应的配置");


        } catch (ServerBaseException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }

    }


}
