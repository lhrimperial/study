package com.githup.study.security.shiro.web.domain;

/**
 *
 */
public class RolePermsPO extends BasePO{
    private static final long serialVersionUID = 8034789334801053348L;
    private Integer roleId;
    private Integer treeId;
    private Integer parentId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getTreeId() {
        return treeId;
    }

    public void setTreeId(Integer treeId) {
        this.treeId = treeId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
