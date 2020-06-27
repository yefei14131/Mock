package org.yefei.qa.mock.controllers.rest;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.yefei.qa.mock.bean.rest.RestGroup;
import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.config.ResponseAdapter;
import org.yefei.qa.mock.controllers.BaseController;
import org.yefei.qa.mock.exception.ServerBaseException;
import org.yefei.qa.mock.exception.UnknownDataException;
import org.yefei.qa.mock.exception.UnknownImportDataException;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestGroup;
import org.yefei.qa.mock.service.IRestGroupService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/7 09:47
 */
@Controller
@RequestMapping("/mock_server/rest/group")
@Slf4j
public class RestGroupController extends BaseController{

    @Autowired
    private ResponseAdapter responseAdapter;

    @Autowired
    private IRestGroupService restGroupService;

    @Autowired
    private BeanScanner beanScanner;

    private final String nav = "rest";


    @RequestMapping(value = "/export.htm", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response, String groupIDs) throws IOException {

        List<RestGroup> restGroups = new ArrayList<>();
        if (StringUtils.isNotBlank(groupIDs)) {
            restGroups = restGroupService.makeExportRestGroup(groupIDs);
        }

        if (restGroups.size() > 0) {
            String filename;
            if (restGroups.size() > 1) {
                filename = MessageFormat.format("MockExport.{0}....data", new String[]{restGroups.get(0).getTblRestRequestGroup().getGroupCode()});
            } else {
                filename = MessageFormat.format("MockExport.{0}.data", new String[]{restGroups.get(0).getTblRestRequestGroup().getGroupCode()});
            }

            byte[] bytes = new ObjectMapper().writeValueAsBytes(restGroups);
            responseAdapter.writeDownloadContent(response, bytes, "", filename);
        }
    }

    @RequestMapping(value = "/import.ajax")
    @ResponseBody
    public Object importGroup(@RequestParam("file") MultipartFile file) {
        try {
            List<RestGroup> restGroupList;
            try {
                byte[] content = file.getBytes();
                String contentStr = new String(content);
                log.info(contentStr);
                restGroupList = new ObjectMapper().readValue(content, new TypeReference<List<RestGroup>>(){});

            } catch (IOException e) {
                log.error("解析导入数据异常", e);
                throw new UnknownImportDataException();
            }

            restGroupService.importRestGroupData(restGroupList);

                return responseAdapter.success();

        }catch (ServerBaseException e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        } catch (Exception e) {
            log.error("导入数据时异常", e);
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }

    }


    @RequestMapping(value = "/list.html", method = RequestMethod.GET)
    public ModelAndView groupList( HttpServletRequest request) throws IOException {

        ModelAndView view = new ModelAndView("rest/group/list");

        this.buildCategory(view, nav);
        List<BeanScanner.BeanField> fields = getFields(

        );
        view.addObject("fields", fields);

        return view;

    }


    @RequestMapping(value = "/info.html", method = RequestMethod.GET)
    public ModelAndView groupInfo(HttpServletRequest request, @RequestParam(value = "groupID", defaultValue = "0" ) int groupID) throws IOException {

        ModelAndView view = new ModelAndView("rest/group/edit");

        this.buildCategory(view, nav);

//        TblRestRequestGroup group = restGroupService.getGroup(groupID);
//        view.addObject("group", group);

        List<BeanScanner.BeanField> fields = getFields();
        view.addObject("fields", fields);
        view.addObject("groupID", groupID);

        return view;

    }

    @RequestMapping("/get.ajax")
    @ResponseBody
    public Object get(HttpServletRequest request, @RequestBody JSONObject params){
        try {
            Integer groupID = params.getInteger("groupID");
            if (groupID == 0){
                throw new UnknownDataException();
            }

            TblRestRequestGroup group = restGroupService.getGroup(groupID);
            if (group != null){
                return responseAdapter.success(group);
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
    public Object save(HttpServletRequest request, @RequestBody TblRestRequestGroup restRequestGroup){
        try {

            if (restGroupService.saveRestGroup(restRequestGroup)){
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

    @RequestMapping("/delete.ajax")
    @ResponseBody
    public Object delete(HttpServletRequest request, @RequestBody JSONObject params){
        try {
            int groupID = params.getInteger("groupID");

            if (restGroupService.deleteRestGroup(groupID)){
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

    @RequestMapping(value = "/list.ajax", method = RequestMethod.POST)
    @ResponseBody
    public Object queryList(){
        HashMap resp = new HashMap();
        try {

            resp.put("list", restGroupService.queryRestGroupList());
            resp.put("total", restGroupService.countRestGroup());


            String result = "{\"code\":0,\"msg\":\"success\",\"count\":7,\"data\":[{\"groupID\":3,\"groupCode\":\"sre\",\"groupName\":null,\"sourceHost\":\"dohko.sre.hualala.com\",\"sourcePort\":80,\"memo\":\"pre sre\",\"sortIndex\":95,\"proxyIP\":\"172.16.0.15\",\"isActive\":true,\"updateTime\":1538053383000},{\"groupID\":5,\"groupCode\":\"test-1001\",\"groupName\":\"null\",\"sourceHost\":\"webyefei.com\",\"sourcePort\":80,\"memo\":\"mockserver测试\",\"sortIndex\":99,\"proxyIP\":\"172.16.0.15\",\"isActive\":true,\"updateTime\":1538993641000},{\"groupID\":6,\"groupCode\":\"test-2003\",\"groupName\":\"null\",\"sourceHost\":\"webyefei.com\",\"sourcePort\":80,\"memo\":\"mockserver测试\",\"sortIndex\":99,\"proxyIP\":\"172.16.0.15\",\"isActive\":true,\"updateTime\":1538964437000},{\"groupID\":9,\"groupCode\":\"test-2001\",\"groupName\":null,\"sourceHost\":\"webyefei.com\",\"sourcePort\":80,\"memo\":\"mockserver测试2\",\"sortIndex\":99,\"proxyIP\":\"172.16.0.15\",\"isActive\":true,\"updateTime\":1538964432000},{\"groupID\":1,\"groupCode\":\"supplychain\",\"groupName\":null,\"sourceHost\":\"pre.shop.hualala.com\",\"sourcePort\":80,\"memo\":\"商户中心mock供应链\",\"sortIndex\":99,\"proxyIP\":\"172.16.0.15\",\"isActive\":true,\"updateTime\":1538053383000},{\"groupID\":2,\"groupCode\":\"test\",\"groupName\":null,\"sourceHost\":\"webyefei.com\",\"sourcePort\":80,\"memo\":\"mockserver测试\",\"sortIndex\":99,\"proxyIP\":\"172.16.0.15\",\"isActive\":true,\"updateTime\":1538053383000},{\"groupID\":4,\"groupCode\":\"cs\",\"groupName\":null,\"sourceHost\":\"dohko.mis.tiaofangzi.com\",\"sourcePort\":443,\"memo\":\"tiaofangzi\",\"sortIndex\":99,\"proxyIP\":\"172.16.0.15\",\"isActive\":true,\"updateTime\":1538053383000}]}";

            return responseAdapter.success(resp);

        }catch (ServerBaseException e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        }catch (Exception e){
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }

    }


    @RequestMapping("/match.ajax")
    @ResponseBody
    /**
     * 根据groupCode字段查询group
     */
    public Object search(@RequestBody JSONObject params){
        HashMap resp = new HashMap();
        try {
            String groupCode = params.getString("groupCode");
            resp.put("list", restGroupService.queryRestGroupByCode(groupCode));
            resp.put("total", restGroupService.countRestGroupByCode(groupCode));

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
            beanFields.addAll( getFields());

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

    private List<BeanScanner.BeanField> getFields(){
        return beanScanner.getBeanFields("TblRestRequestGroup", "groupID", "updateTime");
    }

}
