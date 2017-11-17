package com.githup.study.admin.thymeleaf.domain.vo;

import java.io.Serializable;

/**
 * @author longhr
 * @version 2017/11/8 0008
 */
public class RequestVO implements Serializable {

    private static final long serialVersionUID = 1166208023783234826L;
    private String msgType;
    private String startTime;
    private String endTime;

    @Override
    public String toString() {
        return "RequestVO{" +
                "msgType='" + msgType + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
