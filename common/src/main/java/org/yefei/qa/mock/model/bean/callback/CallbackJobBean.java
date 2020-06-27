package org.yefei.qa.mock.model.bean.callback;

import lombok.Data;
import org.yefei.qa.mock.model.bean.AbstractSerializable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/9/9 12:42
 */
@Data
public class CallbackJobBean extends AbstractSerializable {

    private String sourceServer;    //来源服务，http grpc  admin

    private boolean needNotify;     //执行完成是否需要通知，mq实现

    private String notifyKey;       //执行完成之后的通知信息关键字，可以是uuid。admin配置时可能需要测试配置是否可用，通过notifyKey关联结果

    private int delay; //  延时执行的时间，单位 ms

    private List<CallbackTaskBean> taskList = new ArrayList<CallbackTaskBean>();

    private String traceID;

    private String memo; // 任务备注

}
