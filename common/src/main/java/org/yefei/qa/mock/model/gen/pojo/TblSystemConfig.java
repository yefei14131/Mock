package org.yefei.qa.mock.model.gen.pojo;

import java.util.Date;

public class TblSystemConfig {
    private Integer configID;

    private Integer configType;

    private String configTitle;

    private String configData;

    private Date updateTime;

    public Integer getConfigID() {
        return configID;
    }

    public void setConfigID(Integer configID) {
        this.configID = configID;
    }

    public Integer getConfigType() {
        return configType;
    }

    public void setConfigType(Integer configType) {
        this.configType = configType;
    }

    public String getConfigTitle() {
        return configTitle;
    }

    public void setConfigTitle(String configTitle) {
        this.configTitle = configTitle == null ? null : configTitle.trim();
    }

    public String getConfigData() {
        return configData;
    }

    public void setConfigData(String configData) {
        this.configData = configData == null ? null : configData.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
