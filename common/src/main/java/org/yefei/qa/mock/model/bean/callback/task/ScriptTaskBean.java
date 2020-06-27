package org.yefei.qa.mock.model.bean.callback.task;

import lombok.Data;

import java.util.HashMap;

/**
 * @author: yefei
 * @date: 2018/9/25 16:07
 */
@Data
public class ScriptTaskBean extends AbstractTaskBean {

    private String scriptContent;   //脚本内容

    private String scriptLanguage;  //脚本语言

    private HashMap data;    //脚本运行时的全局数据

}
