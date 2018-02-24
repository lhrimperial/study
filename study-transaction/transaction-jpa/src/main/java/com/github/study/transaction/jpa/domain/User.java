package com.github.study.transaction.jpa.domain;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer userId;
    private String userName;
    private String password;
    private String realName;
    private String email;
    private Integer state;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name="t_user_role",//中间表的名称
            joinColumns={@JoinColumn(name="userId",referencedColumnName="userId") },//中间表userId字段关联t_user的userId
            inverseJoinColumns={@JoinColumn(name="roleId",referencedColumnName="roleId")})//中间表roleId字段关联t_role的roleId
    private Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", email='" + email + '\'' +
                ", state=" + state +
                '}';
    }
}
