package org.yefei.qa.mock.model.bean;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.yefei.qa.mock.debugger.SystemDebugger;
import org.yefei.qa.mock.enums.CompareWayEnum;
import org.yefei.qa.mock.enums.OperatorEnum;
import org.yefei.qa.mock.exception.UnknownOperatorException;
import org.yefei.qa.mock.model.gen.pojo.TblMappingRulesDetail;
import org.yefei.qa.mock.utils.VariableManager;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;


/**
 * Created by yefei on 2018/8/1.
 */
@Data
@Slf4j
public class HitCondition {

    private TblMappingRulesDetail rules;

    private List<HitCondition> subConditions;

    private SystemDebugger systemDebugger = null;

    public HitCondition(SystemDebugger systemDebugger) {
        this.systemDebugger = systemDebugger;
    }

    public boolean isMatch(HashMap... dataPools) {
        if(subConditions != null && subConditions.size() > 0){
            return isMultMatch(dataPools);
        }else{
            return isSingleMatch(dataPools);
        }
    }

    private boolean isSingleMatch(HashMap... dataPools) {
        if ( rules == null ){
            return false;
        }

        // 生成真实的变量名。支持变量名是一个变量的情况
        String realVariableName = VariableManager.replaceContent(rules.getVariableName(), dataPools);

        // 获取请求中实际的变量值
        String realVariableValue = VariableManager.getVariableValue(realVariableName, dataPools);

        // 生成期望值。需要比较的变量值是一个变量的情况
        String expectValue = VariableManager.replaceContent(rules.getVariableValue(), dataPools);

        StringBuilder matchDetails = new StringBuilder();
        matchDetails.append("{");

        matchDetails.append("\"变量名\":");
        matchDetails.append("\"");
        matchDetails.append(realVariableName);
        matchDetails.append("\"");

        matchDetails.append("\"期望值\":");
        matchDetails.append("\"");
        matchDetails.append(expectValue);
        matchDetails.append("\"");

        matchDetails.append("\"实际值\":");
        matchDetails.append("\"");
        matchDetails.append(realVariableValue);
        matchDetails.append("\"");

        boolean isHit = isHit(realVariableValue, expectValue);
        matchDetails.append("\"匹配结果\":");
        matchDetails.append("\"");
        matchDetails.append(isHit ? "匹配成功" : "匹配失败");
        matchDetails.append("\"");
        matchDetails.append("}");
        systemDebugger.addSystemLog(
                String.format("匹配规则执行情况, requestId: %d, rulesId: %d", rules.getRequestID(), rules.getRulesDetailID())
                , matchDetails.toString());
        return isHit;
    }

    /**
     * 是否命中记录
     *
     * @param requestValue 根据变量名获得的值
     * @param recordValue  根据需要比对的值或者的值（支持变量）
     * @return
     */
    private boolean isHit(String requestValue, String recordValue) {
        String compareWay = rules.getCompareWay();

        if (CompareWayEnum.EQUALS.getCode().equals(compareWay)) {
            //字符串相等
            return recordValue.equals(requestValue);
        } else if (CompareWayEnum.CONTAINS.getCode().equals(compareWay)) {
            // 字符串包含
            return requestValue.contains(recordValue);
        } else if (CompareWayEnum.GT.getCode().equals(compareWay)) {
            // 大于
            if ( new BigDecimal(requestValue).compareTo(new BigDecimal(recordValue)) > 0 ){
                return true;
            }else{
                return false;
            }

        } else if (CompareWayEnum.GTE.getCode().equals(compareWay)) {
            // 大于等于
            if ( new BigDecimal(requestValue).compareTo(new BigDecimal(recordValue)) >= 0 ){
                return true;
            }else{
                return false;
            }

        } else if (CompareWayEnum.LT.getCode().equals(compareWay)) {
            // 小于
            if ( new BigDecimal(requestValue).compareTo(new BigDecimal(recordValue)) < 0 ){
                return true;
            }else{
                return false;
            }

        } else if (CompareWayEnum.LTE.getCode().equals(compareWay)) {
            // 小于等于
            if ( new BigDecimal(requestValue).compareTo(new BigDecimal(recordValue)) <= 0 ){
                return true;
            }else{
                return false;
            }

        } else if (CompareWayEnum.EQ.getCode().equals(compareWay)) {
            // 数字等于
            if ( new BigDecimal(requestValue).compareTo(new BigDecimal(recordValue)) == 0 ){
                return true;
            }else{
                return false;
            }

        } else if (CompareWayEnum.NEQ.getCode().equals(compareWay)) {
            // 数字等于
            if ( new BigDecimal(requestValue).compareTo(new BigDecimal(recordValue)) != 0 ){
                return true;
            }else{
                return false;
            }

        } else if (CompareWayEnum.REGEX.getCode().equals(compareWay)) {
            return requestValue.matches(recordValue);
        }

        return false;
    }


    /**
     *  组合条件， 相当于 ( cond1 && cond2  )
     */
    private boolean isMultMatch(HashMap... dataPools) {
        Boolean flag = null;
        for(int i = 0; i < subConditions.size(); i++){
            HitCondition subCondition = subConditions.get(i);

            if (i == 0){
                // 第一个必须为 &&
                subCondition.rules.setOperator(OperatorEnum.AND.getOperator());
            }

            String operator = subCondition.rules.getOperator();
            if (OperatorEnum.OR.getOperator().equals(operator) && flag == true){
                // 当前为 || ，且 前面为 true， 则不执行后面的
                return true;
            }

            boolean subFlag = subCondition.isMatch(dataPools);

            if(flag == null){
                flag = subFlag;
            }else{

                if(OperatorEnum.AND.getOperator().equals(operator)){
                    flag &= subFlag;
                    if(!flag){
                        break;
                    }

                }else if(OperatorEnum.OR.getOperator().equals(operator)){
                    flag |= subFlag;
                    if(flag){
                        break;
                    }
                }else{
                    throw new UnknownOperatorException(subCondition.toString());
                }
            }
        }

        return flag;
    }


}
