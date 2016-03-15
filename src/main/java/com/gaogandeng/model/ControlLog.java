package com.gaogandeng.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by lanxing on 16-3-15.
 */
public class ControlLog implements Serializable {
    private static final long serialVersionUID = 3513256236216809910L;

    private Integer id;             //控制编号
    private Date openTime;          //开灯时间，为null表示没有开灯动作
    private Date closeTime;         //关灯时间，为null表示无关灯动作
    private User user;              //执行人员
    private List<Light> lights;     //灯具列表
    private Date infoTime;          //执行时间
    private Integer status;         //执行状态（0：未执行，1：执行）

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Light> getLights() {
        return lights;
    }

    public void setLights(List<Light> lights) {
        this.lights = lights;
    }

    public Date getInfoTime() {
        return infoTime;
    }

    public void setInfoTime(Date infoTime) {
        this.infoTime = infoTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ControlLog{" +
                "id=" + id +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                ", user=" + user +
                ", lights=" + lights +
                ", infoTime=" + infoTime +
                ", status=" + status +
                '}';
    }
}
