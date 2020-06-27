package org.yefei.qa.mock.service;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import org.yefei.qa.mock.model.bean.RecordedRequest;

import java.util.HashMap;

/**
 * Created by yefei on 2018/8/2.
 */
public interface IResponseWriter {

    void writeResponse(RecordedRequest recordedRequest, HashMap userDefined, HashMap params, ServerCall serverCall, Metadata headers);

}
