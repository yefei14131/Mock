package org.yefei.qa.mock.bean.rest;

import lombok.Data;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yefei
 * @date: 2019/12/18
 */
@Data
public class RestGroup {
    private TblRestRequestGroup tblRestRequestGroup;
    private List<RestRequestPath> restRequestPathList = new ArrayList<>();
}
