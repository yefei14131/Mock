package org.yefei.qa.mock.model.gen.pojo;

import java.util.Date;

public class TblGrpcRequestScript {
    private Integer scriptID;

    private Integer groupID;

    private String scriptLanguage;

    private String serviceName;

    private String methodName;

    private Boolean isActive;

    private Integer sortIndex;

    private Date updateTime;

    private String scriptContent;

    public Integer getScriptID() {
        return scriptID;
    }

    public void setScriptID(Integer scriptID) {
        this.scriptID = scriptID;
    }

    public Integer getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public String getScriptLanguage() {
        return scriptLanguage;
    }

    public void setScriptLanguage(String scriptLanguage) {
        this.scriptLanguage = scriptLanguage == null ? null : scriptLanguage.trim();
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Integer getSortIndex() {
        return sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getScriptContent() {
        return scriptContent;
    }

    public void setScriptContent(String scriptContent) {
        this.scriptContent = scriptContent == null ? null : scriptContent.trim();
    }
}
