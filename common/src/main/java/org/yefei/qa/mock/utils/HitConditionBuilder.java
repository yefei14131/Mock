package org.yefei.qa.mock.utils;

import org.yefei.qa.mock.model.bean.HitCondition;
import org.yefei.qa.mock.model.gen.pojo.TblMappingRulesDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yefei
 * @date: 2018/12/3 11:56
 */
public class HitConditionBuilder {

    public static HitCondition buildHitCondition(List<TblMappingRulesDetail> tblMappingRulesDetails){

        //TODO 嵌套构建。 现在先只匹配一级。
        HitCondition hitCondition = new HitCondition();

        if (tblMappingRulesDetails != null && tblMappingRulesDetails.size() > 0){
            ArrayList<HitCondition> subConditions = new ArrayList<HitCondition>();
            for (TblMappingRulesDetail tblMappingRulesDetail : tblMappingRulesDetails){
                HitCondition subHitCondition = new HitCondition();
                subHitCondition.setRules(tblMappingRulesDetail);
                subConditions.add(subHitCondition);
            }

            hitCondition.setSubConditions(subConditions);
        }
        return hitCondition;
    }
}
