package org.yefei.qa.mock.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.yefei.qa.mock.config.ResponseAdapter;
import org.yefei.qa.mock.network.mapping.GrpcMappingAgentSimple;
import org.yefei.qa.mock.network.mapping.MappingFullMessage;
import org.yefei.qa.mock.network.mapping.RestMappingAgentSimple;
import org.yefei.qa.mock.service.IGrpcMappingService;
import org.yefei.qa.mock.service.IRestMappingService;

import java.util.List;

/**
 * @author: yefei
 * @date: 2018/10/7 09:47
 */
@Controller
@Slf4j
@RequestMapping(value = "/api")
public class APIController {


    @Autowired
    private ResponseAdapter responseAdapter;

    @Autowired
    private IRestMappingService restMappingService;

    @Autowired
    private IGrpcMappingService grpcMappingService;

    @ResponseBody
    @RequestMapping("/mapping/list")
    public Object test() {

        try {
            List<RestMappingAgentSimple> restMappings = restMappingService.queryAllActiveMapping();
            List<GrpcMappingAgentSimple> grpcMappings = grpcMappingService.queryAllActiveMapping();

            MappingFullMessage mappingFullMessage = new MappingFullMessage();
            mappingFullMessage.setGrpc(grpcMappings);
            mappingFullMessage.setRest(restMappings);

            return responseAdapter.success(mappingFullMessage);
        } catch (Exception e) {
            return responseAdapter.failure(e);
        }
    }

}
