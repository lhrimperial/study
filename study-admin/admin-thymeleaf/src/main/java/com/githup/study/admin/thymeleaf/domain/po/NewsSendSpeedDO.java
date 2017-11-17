package com.githup.study.admin.thymeleaf.domain.po;

/**
 * @author longhr
 * @version 2017/11/7 0007
 */
public class NewsSendSpeedDO extends BaseDO{

    private static final long serialVersionUID = 6301002006003592442L;

    private String msgId;
    private String msgType;
    private Integer reachCount;
    private Integer sendCount;
    private Integer failCount;

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

    public Integer getReachCount() {
        return reachCount;
    }

    public void setReachCount(Integer reachCount) {
        this.reachCount = reachCount;
    }

    public Integer getSendCount() {
        return sendCount;
    }

    public void setSendCount(Integer sendCount) {
        this.sendCount = sendCount;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }
}
