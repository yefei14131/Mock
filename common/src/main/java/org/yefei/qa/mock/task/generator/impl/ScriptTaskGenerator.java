//package org.yefei.qa.mock.task.generator.impl;
//
//import com.alibaba.fastjson.JSONObject;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.exception.ExceptionUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.yefei.qa.mock.debugger.SystemDebugger;
//import org.yefei.qa.mock.enums.TaskTypeEnum;
//import org.yefei.qa.mock.model.bean.callback.CallbackTaskBean;
//import org.yefei.qa.mock.model.bean.callback.task.ScriptTaskBean;
//import org.yefei.qa.mock.model.gen.pojo.TblMappingTask;
//import org.yefei.qa.mock.task.generator.ITaskGenerator;
//import org.yefei.qa.mock.task.generator.TaskSourceInfo;
//
//import java.util.HashMap;
//
///**
// * @author: yefei
// * @date: 2018/9/26 18:30
// */
//@Slf4j
//@Component("scriptTaskGenerator")
//public class ScriptTaskGenerator implements ITaskGenerator {
//
//    @Autowired
//    private SystemDebugger systemDebugger;
//
//    @Override
//    public CallbackTaskBean generate(TaskSourceInfo taskSourceInfo, TblMappingTask requestTask, HashMap params, HashMap... dataPool) {
//        try {
//            String configs = requestTask.getConfigs();
//            ScriptTaskBean scriptTaskBean = JSONObject.parseObject(configs, ScriptTaskBean.class);
//
//            HashMap data = new HashMap();
////            data.put("vars", userDefined);
////            data.put("params", params);
////            data.put("headers", headers);
////            data.put("cookies", cookies);
//
//            scriptTaskBean.setData(data);
//
//            CallbackTaskBean taskBean = new CallbackTaskBean();
//
//            taskBean.setTaskType(TaskTypeEnum.SCRIPT);
//            taskBean.setTaskConfig(scriptTaskBean);
//
//            return taskBean;
//
//        }catch (Exception e){
//            log.error(ExceptionUtils.getStackTrace(e));
//            systemDebugger.addSystemLog("生成脚本任务数据时报错", ExceptionUtils.getStackTrace(e));
//
//            return null;
//        }
//
//    }
//}
