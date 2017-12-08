package com.githup.study.web.simple.domain;

import java.util.Date;

/**
 * @author longhairen
 * @create 2017/10/11 0011 上午 10:24
 */
public class NotesDate {
    private int id;
    private String notes;
    private Date createDatetime;
    private Date createDate;
    private Date createTime;
    private Date createTimestrap;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getCreateTimestrap() {
        return createTimestrap;
    }

    public void setCreateTimestrap(Date createTimestrap) {
        this.createTimestrap = createTimestrap;
    }
}
