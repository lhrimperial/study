package com.githup.study.admin.thymeleaf.domain.vo;

import java.io.Serializable;

/**
 * @author longhr
 * @version 2017/11/10 0010
 */
public class NewsSpeedDetailVO implements Serializable {
    private static final long serialVersionUID = -3297070985638157879L;

    private String msgId;
    private String msgType;
    private Integer status;
    private String token;
    private String name;
    private String pushType;
    private String pushUrl;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType;
    }

    public String getPushUrl() {
        return pushUrl;
    }

    public void setPushUrl(String pushUrl) {
        this.pushUrl = pushUrl;
    }
}
