package com.githup.study.security.shiro.web.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class BasePO implements Serializable {
    private static final long serialVersionUID = 4518625607078232965L;
    private Integer id;
    private Date createTime;
    private Date modifyime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyime() {
        return modifyime;
    }

    public void setModifyime(Date modifyime) {
        this.modifyime = modifyime;
    }
}
