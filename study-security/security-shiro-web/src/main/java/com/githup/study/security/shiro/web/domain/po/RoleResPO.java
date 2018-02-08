package com.githup.study.security.shiro.web.domain.po;

/**
 *
 */
public class RoleResPO extends BasePO {
    private static final long serialVersionUID = 3505750351283084519L;

    private Integer roleId;
    private Integer resId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }
}
