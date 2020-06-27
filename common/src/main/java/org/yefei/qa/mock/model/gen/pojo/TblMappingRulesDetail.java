package org.yefei.qa.mock.model.gen.pojo;

import java.util.Date;

public class TblMappingRulesDetail {
    private Integer rulesDetailID;

    private Integer requestID;

    private String protocol;

    private String variableName;

    private String variableSource;

    private String variableValue;

    private String compareWay;

    private Integer parentID;

    private Integer sortIndex;

    private String operator;

    private Date updateTime;

    public Integer getRulesDetailID() {
        return rulesDetailID;
    }

    public void setRulesDetailID(Integer rulesDetailID) {
        this.rulesDetailID = rulesDetailID;
    }

    public Integer getRequestID() {
        return requestID;
    }

    public void setRequestID(Integer requestID) {
        this.requestID = requestID;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol == null ? null : protocol.trim();
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName == null ? null : variableName.trim();
    }

    public String getVariableSource() {
        return variableSource;
    }

    public void setVariableSource(String variableSource) {
        this.variableSource = variableSource == null ? null : variableSource.trim();
    }

    public String getVariableValue() {
        return variableValue;
    }

    public void setVariableValue(String variableValue) {
        this.variableValue = variableValue == null ? null : variableValue.trim();
    }

    public String getCompareWay() {
        return compareWay;
    }

    public void setCompareWay(String compareWay) {
        this.compareWay = compareWay == null ? null : compareWay.trim();
    }

    public Integer getParentID() {
        return parentID;
    }

    public void setParentID(Integer parentID) {
        this.parentID = parentID;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
