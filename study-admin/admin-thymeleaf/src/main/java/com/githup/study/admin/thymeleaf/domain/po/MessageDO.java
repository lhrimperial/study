package com.githup.study.admin.thymeleaf.domain.po;

import java.util.Date;

public class MessageDO {
    /**
    tnt_bms_msg*id
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     */
    private String id;

    /**
    tnt_bms_msg*msg_type
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     */
    private String msgType;

    /**
    tnt_bms_msg*msg_content
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     */
    private String msgContent;

    /**
    tnt_bms_msg*receive_time
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     */
    private Date receiveTime;

    /**
    tnt_bms_msg*modify_time
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     */
    private Date modifyTime;

    /**
    tnt_bms_msg*pusher_id
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     */
    private String pusherId;

    /**
    tnt_bms_msg*msg_status
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     */
    private Byte msgStatus;

    /**
    tnt_bms_msg*msg_id
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     */
    private String msgId;

    /**
    tnt_bms_msg*max_retry
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     */
    private Short maxRetry;

    /**
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     * @return the value of tnt_bms_msg.id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     * @param id the value for tnt_bms_msg.id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     * @return the value of tnt_bms_msg.msg_type
     */
    public String getMsgType() {
        return msgType;
    }

    /**
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     * @param msgType the value for tnt_bms_msg.msg_type
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    /**
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     * @return the value of tnt_bms_msg.msg_content
     */
    public String getMsgContent() {
        return msgContent;
    }

    /**
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     * @param msgContent the value for tnt_bms_msg.msg_content
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }

    /**
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     * @return the value of tnt_bms_msg.receive_time
     */
    public Date getReceiveTime() {
        return receiveTime;
    }

    /**
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     * @param receiveTime the value for tnt_bms_msg.receive_time
     */
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }

    /**
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     * @return the value of tnt_bms_msg.modify_time
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     * @param modifyTime the value for tnt_bms_msg.modify_time
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     * @return the value of tnt_bms_msg.pusher_id
     */
    public String getPusherId() {
        return pusherId;
    }

    /**
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     * @param pusherId the value for tnt_bms_msg.pusher_id
     */
    public void setPusherId(String pusherId) {
        this.pusherId = pusherId == null ? null : pusherId.trim();
    }

    /**
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     * @return the value of tnt_bms_msg.msg_status
     */
    public Byte getMsgStatus() {
        return msgStatus;
    }

    /**
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     * @param msgStatus the value for tnt_bms_msg.msg_status
     */
    public void setMsgStatus(Byte msgStatus) {
        this.msgStatus = msgStatus;
    }

    /**
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     * @return the value of tnt_bms_msg.msg_id
     */
    public String getMsgId() {
        return msgId;
    }

    /**
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     * @param msgId the value for tnt_bms_msg.msg_id
     */
    public void setMsgId(String msgId) {
        this.msgId = msgId == null ? null : msgId.trim();
    }

    /**
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     * @return the value of tnt_bms_msg.max_retry
     */
    public Short getMaxRetry() {
        return maxRetry;
    }

    /**
     *
     * @author Administrator
     * @date 2017-11-09 16:01:47
     * @param maxRetry the value for tnt_bms_msg.max_retry
     */
    public void setMaxRetry(Short maxRetry) {
        this.maxRetry = maxRetry;
    }
}