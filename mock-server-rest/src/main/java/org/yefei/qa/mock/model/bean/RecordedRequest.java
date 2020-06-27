package org.yefei.qa.mock.model.bean;

import lombok.Data;
import org.yefei.qa.mock.model.gen.pojo.TblRestRequestMapping;

import java.util.HashMap;

/**
 * Created by yefei on 2018/8/2.
 */
@Data
public class RecordedRequest {

    private TblRestRequestMapping tblRestRequestMapping;

    private HitCondition hitCondition;

    public boolean isMatch(HashMap... dataPools) {
        //TODO ognl 表达式

        if ( hitCondition == null || (hitCondition.getRules() == null && (hitCondition.getSubConditions() == null || hitCondition.getSubConditions().size() == 0))){
            // 只有路径，没有命中条件，直接返回
            return true;
        }

        return hitCondition.isMatch(dataPools);

    }

}
