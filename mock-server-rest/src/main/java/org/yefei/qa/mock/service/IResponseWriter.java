package org.yefei.qa.mock.service;

import org.yefei.qa.mock.model.bean.RecordedRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by yefei on 2018/8/2.
 */
public interface IResponseWriter {

    void writeResponse(RecordedRequest recordedRequest, HashMap userDefined, HashMap params, HttpServletResponse response) throws IOException;
}
