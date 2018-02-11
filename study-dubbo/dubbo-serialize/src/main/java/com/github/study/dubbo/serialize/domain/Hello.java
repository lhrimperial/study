package com.github.study.dubbo.serialize.domain;

import java.io.Serializable;
import java.util.List;

/**
 *
 */
public class Hello implements Serializable {
    private static final long serialVersionUID = -8960002195947809184L;

    private String userName;
    private String passWord;
    private String userInfo;
    private List<Hello> friends;

    public List<Hello> getFriends() {
        return friends;
    }

    public void setFriends(List<Hello> friends) {
        this.friends = friends;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", userInfo='" + userInfo + '\'' +
                ", friends=" + friends +
                '}';
    }
}
