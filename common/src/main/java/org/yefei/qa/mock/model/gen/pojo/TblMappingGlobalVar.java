package org.yefei.qa.mock.model.gen.pojo;

import java.util.Date;

public class TblMappingGlobalVar {
    private Integer globalVarID;

    private Integer requestID;

    private String protocol;

    private String varName;

    private String varValue;

    private Date updateTime;

    public Integer getGlobalVarID() {
        return globalVarID;
    }

    public void setGlobalVarID(Integer globalVarID) {
        this.globalVarID = globalVarID;
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

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName == null ? null : varName.trim();
    }

    public String getVarValue() {
        return varValue;
    }

    public void setVarValue(String varValue) {
        this.varValue = varValue == null ? null : varValue.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}