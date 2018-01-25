package com.githup.study.dubbo.provider.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.study.domain.User;
import com.github.study.service.IUserService;
import com.githup.study.dubbo.provider.service.IUserInfoServiceRef;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UserInfoServiceRefImpl implements IUserInfoServiceRef {

    @Reference
    public IUserService userService;

    @Override
    public User findUser() {
        return userService.findUser("");
    }
}
