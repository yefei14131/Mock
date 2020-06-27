package org.yefei.qa.mock.model.bean.callback;

import lombok.Data;
import org.yefei.qa.mock.enums.TaskTypeEnum;
import org.yefei.qa.mock.model.bean.AbstractSerializable;
import org.yefei.qa.mock.model.bean.callback.task.AbstractTaskBean;

/**
 * @author: yefei
 * @date: 2018/9/25 14:53
 */
@Data
public class CallbackTaskBean extends AbstractSerializable {

    private TaskTypeEnum taskType;    //任务类型

    private AbstractTaskBean taskConfig; //任务对应的配置数据


}
