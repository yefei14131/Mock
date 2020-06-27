package org.yefei.qa.mock.model.gen.pojo;

import java.util.Date;

public class TblRestRequestScript {
    private Integer scriptID;

    private String scriptLanguage;

    private Integer groupID;

    private String groupCode;

    private String path;

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

    public String getScriptLanguage() {
        return scriptLanguage;
    }

    public void setScriptLanguage(String scriptLanguage) {
        this.scriptLanguage = scriptLanguage == null ? null : scriptLanguage.trim();
    }

    public Integer getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode == null ? null : groupCode.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
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
