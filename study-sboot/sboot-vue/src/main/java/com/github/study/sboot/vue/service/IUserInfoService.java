package com.github.study.sboot.vue.service;

import com.github.study.sboot.vue.domain.UserInfo;

/**
 *
 **/
public interface IUserInfoService {

    UserInfo findByUsername(String userName);
}
