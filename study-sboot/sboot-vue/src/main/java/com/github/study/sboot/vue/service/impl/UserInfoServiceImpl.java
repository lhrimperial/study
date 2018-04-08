package com.github.study.sboot.vue.service.impl;

import com.github.study.sboot.vue.domain.UserInfo;
import com.github.study.sboot.vue.mapper.UserInfoMapper;
import com.github.study.sboot.vue.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 **/
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo findByUsername(String userName) {
        return userInfoMapper.findUserInfoByUserName(userName);
    }
}
