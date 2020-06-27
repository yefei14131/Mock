package org.yefei.qa.mock.task.generator;

import org.yefei.qa.mock.model.bean.callback.CallbackTaskBean;
import org.yefei.qa.mock.model.gen.pojo.TblMappingTask;

import java.io.IOException;
import java.util.HashMap;

/**
 * @author: yefei
 * @date: 2018/9/26 18:26
 */
public interface ITaskGenerator {

    CallbackTaskBean generate(TaskSourceInfo taskSourceInfo, TblMappingTask requestTask, HashMap params, HashMap... dataPool) throws IOException;

}
