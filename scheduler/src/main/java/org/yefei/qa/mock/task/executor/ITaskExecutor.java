package org.yefei.qa.mock.task.executor;

import org.yefei.qa.mock.model.bean.callback.task.AbstractTaskBean;

import java.io.IOException;

/**
 * @author: yefei
 * @date: 2018/9/26 18:26
 */
public interface ITaskExecutor {

    String exec(AbstractTaskBean taskConfig, String traceID) throws IOException;

}
