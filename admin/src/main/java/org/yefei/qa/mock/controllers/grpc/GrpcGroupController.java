package org.yefei.qa.mock.controllers.grpc;

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
import org.yefei.qa.mock.bean.grpc.GrpcGroup;
import org.yefei.qa.mock.config.BeanScanner;
import org.yefei.qa.mock.config.ResponseAdapter;
import org.yefei.qa.mock.controllers.BaseController;
import org.yefei.qa.mock.exception.ServerBaseException;
import org.yefei.qa.mock.exception.UnknownDataException;
import org.yefei.qa.mock.exception.UnknownImportDataException;
import org.yefei.qa.mock.model.gen.pojo.TblGrpcRequestGroup;
import org.yefei.qa.mock.service.IGrpcGroupService;

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
@RequestMapping("/mock_server/grpc/group")
@Slf4j
public class GrpcGroupController extends BaseController {

    @Autowired
    private ResponseAdapter responseAdapter;

    @Autowired
    private IGrpcGroupService grpcGroupService;

    @Autowired
    private BeanScanner beanScanner;

    private final String horizontalNav = "grpc";


    @RequestMapping(value = "/export.htm", method = RequestMethod.GET)
    public void export(HttpServletRequest request, HttpServletResponse response, String groupIDs) throws IOException {

        List<GrpcGroup> grpcGroups = new ArrayList<>();
        if (StringUtils.isNotBlank(groupIDs)) {
            grpcGroups = grpcGroupService.makeExportGrpcGroup(groupIDs);
        }

        if (grpcGroups.size() > 0) {
            String filename;
            if (grpcGroups.size() > 1) {
                filename = MessageFormat.format("MockExport.{0}....data", grpcGroups.get(0).getTblGrpcRequestGroup().getGroupName());
            } else {
                filename = MessageFormat.format("MockExport.{0}.data", grpcGroups.get(0).getTblGrpcRequestGroup().getGroupName());
            }

            byte[] bytes = new ObjectMapper().writeValueAsBytes(grpcGroups);
            responseAdapter.writeDownloadContent(response, bytes, "", filename);
        }
    }

    @RequestMapping(value = "/import.ajax")
    @ResponseBody
    public Object importGroup(@RequestParam("file") MultipartFile file) {
        try {
            List<GrpcGroup> grpcGroupList;
            try {
                byte[] content = file.getBytes();
                String contentStr = new String(content);
                log.info(contentStr);
                grpcGroupList = new ObjectMapper().readValue(content, new TypeReference<List<GrpcGroup>>() {
                });

            } catch (IOException e) {
                log.error("解析导入数据异常", e);
                throw new UnknownImportDataException();
            }

            grpcGroupService.importGrpcGroupData(grpcGroupList);

            return responseAdapter.success();

        } catch (ServerBaseException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            return responseAdapter.failure(e);

        } catch (Exception e) {
            log.error("导入数据时异常", e);
            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
        }

    }


    @RequestMapping(value = "/list.html", method = RequestMethod.GET)
    public ModelAndView groupList(HttpServletRequest request) throws IOException {

        ModelAndView view = new ModelAndView("grpc/group/list");

        this.buildCategory(view, horizontalNav, "grpc-group-list");
        List<BeanScanner.BeanField> fields = getFields();
        view.addObject("fields", fields);

        return view;

    }


    @RequestMapping(value = "/info.html", method = RequestMethod.GET)
    public ModelAndView groupInfo(HttpServletRequest request, @RequestParam(value = "groupID", defaultValue = "0") int groupID) throws IOException {

        ModelAndView view = new ModelAndView("grpc/group/edit");

        this.buildCategory(view, horizontalNav, "grpc-group-list");

//        TblGrpcRequestGroup group = grpcGroupService.getGroup(groupID);
//        view.addObject("group", group);

        List<BeanScanner.BeanField> fields = getFields();
        view.addObject("fields", fields);
        view.addObject("groupID", groupID);

        return view;

    }

    @RequestMapping("/get.ajax")
    @ResponseBody
    public Object get(HttpServletRequest request, @RequestBody JSONObject params) {
        try {
            Integer groupID = params.getInteger("groupID");
            if (groupID == 0) {
                throw new UnknownDataException();
            }

            TblGrpcRequestGroup group = grpcGroupService.getGroup(groupID);
            if (group != null) {
                return responseAdapter.success(group);
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
    public Object save(HttpServletRequest request, @RequestBody TblGrpcRequestGroup grpcRequestGroup) {
        try {

            if (grpcGroupService.saveGrpcGroup(grpcRequestGroup)) {
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
            int groupID = params.getInteger("groupID");

            if (grpcGroupService.deleteGrpcGroup(groupID)) {
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

    @RequestMapping(value = "/list.ajax", method = RequestMethod.POST)
    @ResponseBody
    public Object queryList() {
        HashMap resp = new HashMap();
        try {

            resp.put("list", grpcGroupService.queryGrpcGroupList());
            resp.put("total", grpcGroupService.countGrpcGroup());


            String result = "{\"code\":0,\"msg\":\"success\",\"count\":7,\"data\":[{\"groupID\":3,\"groupCode\":\"sre\",\"groupName\":null,\"sourceHost\":\"dohko.sre.hualala.com\",\"sourcePort\":80,\"memo\":\"pre sre\",\"sortIndex\":95,\"proxyIP\":\"172.16.0.15\",\"isActive\":true,\"updateTime\":1538053383000},{\"groupID\":5,\"groupCode\":\"test-1001\",\"groupName\":\"null\",\"sourceHost\":\"webyefei.com\",\"sourcePort\":80,\"memo\":\"mockserver测试\",\"sortIndex\":99,\"proxyIP\":\"172.16.0.15\",\"isActive\":true,\"updateTime\":1538993641000},{\"groupID\":6,\"groupCode\":\"test-2003\",\"groupName\":\"null\",\"sourceHost\":\"webyefei.com\",\"sourcePort\":80,\"memo\":\"mockserver测试\",\"sortIndex\":99,\"proxyIP\":\"172.16.0.15\",\"isActive\":true,\"updateTime\":1538964437000},{\"groupID\":9,\"groupCode\":\"test-2001\",\"groupName\":null,\"sourceHost\":\"webyefei.com\",\"sourcePort\":80,\"memo\":\"mockserver测试2\",\"sortIndex\":99,\"proxyIP\":\"172.16.0.15\",\"isActive\":true,\"updateTime\":1538964432000},{\"groupID\":1,\"groupCode\":\"supplychain\",\"groupName\":null,\"sourceHost\":\"pre.shop.hualala.com\",\"sourcePort\":80,\"memo\":\"商户中心mock供应链\",\"sortIndex\":99,\"proxyIP\":\"172.16.0.15\",\"isActive\":true,\"updateTime\":1538053383000},{\"groupID\":2,\"groupCode\":\"test\",\"groupName\":null,\"sourceHost\":\"webyefei.com\",\"sourcePort\":80,\"memo\":\"mockserver测试\",\"sortIndex\":99,\"proxyIP\":\"172.16.0.15\",\"isActive\":true,\"updateTime\":1538053383000},{\"groupID\":4,\"groupCode\":\"cs\",\"groupName\":null,\"sourceHost\":\"dohko.mis.tiaofangzi.com\",\"sourcePort\":443,\"memo\":\"tiaofangzi\",\"sortIndex\":99,\"proxyIP\":\"172.16.0.15\",\"isActive\":true,\"updateTime\":1538053383000}]}";

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
//    @RequestMapping("/match.ajax")
//    @ResponseBody
//    /**
//     * 根据groupCode字段查询group
//     */
//    public Object search(@RequestBody JSONObject params){
//        HashMap resp = new HashMap();
//        try {
//            String groupCode = params.getString("groupCode");
//            resp.put("list", grpcGroupService.queryGrpcGroupByCode(groupCode));
//            resp.put("total", grpcGroupService.countGrpcGroupByCode(groupCode));
//
//            return responseAdapter.success(resp);
//
//        }catch (ServerBaseException e){
//            log.error(ExceptionUtils.getStackTrace(e));
//            return responseAdapter.failure(e);
//
//        }catch (Exception e){
//            log.error(ExceptionUtils.getStackTrace(e));
//            return responseAdapter.failure(ExceptionUtils.getStackTrace(e));
//        }
//
//    }


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
        return beanScanner.getBeanFields("TblGrpcRequestGroup", "groupID", "updateTime");
    }

}
