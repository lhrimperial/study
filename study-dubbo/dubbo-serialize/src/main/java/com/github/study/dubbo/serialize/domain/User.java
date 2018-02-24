package com.github.study.dubbo.serialize.domain;

import java.io.*;
import java.util.List;

/**
 *
 */
public class User implements Serializable{
    private static final long serialVersionUID = -1924926110132298452L;
    private String userName;
    private String passWord;
    private String userInfo;
    private List<User> friends;

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
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
        return "User{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", userInfo='" + userInfo + '\'' +
                ", friends=" + friends +
                '}';
    }
}
