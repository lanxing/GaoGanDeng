package com.gaogandeng.QueryCondition;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lanxing on 16-3-16.
 */
public class ControlLogQuery implements Serializable{

    private static final long serialVersionUID = -951015664799740534L;
    private Date startTime;
    private Date endTime;
    private Integer userId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
