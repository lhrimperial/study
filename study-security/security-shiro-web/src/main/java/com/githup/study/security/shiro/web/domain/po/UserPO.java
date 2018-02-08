package com.githup.study.security.shiro.web.domain.po;


import java.util.Set;

/**
 *
 */
public class UserPO extends BasePO {
    private static final long serialVersionUID = 4566765341157616278L;
    private String userName;
    private String password;
    private String realName;
    private String email;
    private Integer state;

    private Set<RolePO> roles;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Set<RolePO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RolePO> roles) {
        this.roles = roles;
    }
}
