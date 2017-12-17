package com.github.study.domain;

import java.io.Serializable;

/**
 * @author longhairen
 * @create 2017/8/11 0011 上午 9:10
 */
public class User implements Serializable{
    private static final long serialVersionUID = 5893783430203011361L;
    private String userName;
    private String password;

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
}
