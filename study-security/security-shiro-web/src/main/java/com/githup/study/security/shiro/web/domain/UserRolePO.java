package com.githup.study.security.shiro.web.domain;

/**
 *
 */
public class UserRolePO extends BasePO{
    private static final long serialVersionUID = -6421676335661447110L;
    private Integer userId;
    private Integer roleId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
