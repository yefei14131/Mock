package org.yefei.qa.mock.bean.rest;

import lombok.Data;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestScript;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yefei
 * @date: 2019/12/18
 */
@Data
public class RestRequestPath {
    private String path;
    private List<TblRestRequestScript> tblRestRequestScriptList = new ArrayList<>();
    private List<RestRequestMapping> restRequestMappingList = new ArrayList<>();
}
