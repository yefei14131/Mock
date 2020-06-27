package org.yefei.qa.mock.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.yefei.qa.mock.grpc.handler.GrpcProtocolService;

import javax.annotation.PostConstruct;


/**
 * @author: yefei
 * @date: 2018/12/2 23:03
 */
@Service
@Slf4j
public class GrpcBizServiceImpl extends GrpcProtocolService {

    @PostConstruct
    public void init() {
        super.init();
    }

}
