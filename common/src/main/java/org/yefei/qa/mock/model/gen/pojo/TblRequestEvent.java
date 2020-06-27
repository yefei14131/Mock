package org.yefei.qa.mock.model.gen.pojo;

import java.util.Date;

public class TblRequestEvent {
    private Integer requestEventID;

    private String traceID;

    private String eventName;

    private Date updateTime;

    private String eventDesc;

    public Integer getRequestEventID() {
        return requestEventID;
    }

    public void setRequestEventID(Integer requestEventID) {
        this.requestEventID = requestEventID;
    }

    public String getTraceID() {
        return traceID;
    }

    public void setTraceID(String traceID) {
        this.traceID = traceID == null ? null : traceID.trim();
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName == null ? null : eventName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc == null ? null : eventDesc.trim();
    }
}
