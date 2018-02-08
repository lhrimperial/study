package com.githup.study.security.shiro.web.domain.po;


/**
 *
 */
public class RolePO extends BasePO{
    private static final long serialVersionUID = -6986326459899546626L;
    private String roleName;
    private String roleDesc;

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
}
