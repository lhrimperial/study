package com.githup.study.security.shiro.web.domain.po;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class BasePO implements Serializable {
    private static final long serialVersionUID = 4518625607078232965L;
    private Integer id;
    private Date createTime;
    private Date modifyTime;

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

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
