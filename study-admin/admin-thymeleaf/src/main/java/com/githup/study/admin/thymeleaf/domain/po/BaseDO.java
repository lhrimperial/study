package com.githup.study.admin.thymeleaf.domain.po;

import java.io.Serializable;
import java.util.Date;

/**
 * @author longhr
 * @version 2017/11/1 0001
 */
public class BaseDO implements Serializable {

    private Long id;
    private Date createTime;
    private Date modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
