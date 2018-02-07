package com.githup.study.security.shiro.web.domain;


/**
 *
 */
public class RolePO extends BasePO{
    private static final long serialVersionUID = -6986326459899546626L;
    private Integer roleId;
    private String roleName;
    private String roleDesc;
    private String permission;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
