package org.yefei.qa.mock.model.gen.pojo;

import java.util.Date;

public class TblRulesCompareMethod {
    private Integer compareMethodID;

    private String compareMethod;

    private String compareMemo;

    private Integer sortIndex;

    private Date updateTime;

    public Integer getCompareMethodID() {
        return compareMethodID;
    }

    public void setCompareMethodID(Integer compareMethodID) {
        this.compareMethodID = compareMethodID;
    }

    public String getCompareMethod() {
        return compareMethod;
    }

    public void setCompareMethod(String compareMethod) {
        this.compareMethod = compareMethod == null ? null : compareMethod.trim();
    }

    public String getCompareMemo() {
        return compareMemo;
    }

    public void setCompareMemo(String compareMemo) {
        this.compareMemo = compareMemo == null ? null : compareMemo.trim();
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
}
