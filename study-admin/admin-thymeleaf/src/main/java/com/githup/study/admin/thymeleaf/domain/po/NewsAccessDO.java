package com.githup.study.admin.thymeleaf.domain.po;

/**
 * @author longhr
 * @version 2017/11/2 0002
 */
public class NewsAccessDO extends BaseDO {
    private static final long serialVersionUID = -4178503870049309674L;

    private String token;
    private String name;
    private Integer receiveType;
    private Integer pushType;
    private String pushUrl;
    private Integer opened;
    private Integer converterType;
    private Integer priority;
    private Integer threshold;

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

    public Integer getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
    }

    public Integer getPushType() {
        return pushType;
    }

    public void setPushType(Integer pushType) {
        this.pushType = pushType;
    }

    public String getPushUrl() {
        return pushUrl;
    }

    public void setPushUrl(String pushUrl) {
        this.pushUrl = pushUrl;
    }

    public Integer getOpened() {
        return opened;
    }

    public void setOpened(Integer opened) {
        this.opened = opened;
    }

    public Integer getConverterType() {
        return converterType;
    }

    public void setConverterType(Integer converterType) {
        this.converterType = converterType;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }
}
