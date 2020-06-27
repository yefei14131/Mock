package org.yefei.qa.mock.service;

import org.yefei.qa.mock.model.bean.RecordedRequest;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yefei on 2018/7/31.
 */
public interface IRequestMacther {

    List<RecordedRequest> loadRecordedRequestList(String groupCode, String path);

    RecordedRequest matchRecordedRequest(List<RecordedRequest> recordedRequests, HashMap userDefined, HashMap params, HashMap<String, String> headers, HashMap<String, String> cookies);





}
