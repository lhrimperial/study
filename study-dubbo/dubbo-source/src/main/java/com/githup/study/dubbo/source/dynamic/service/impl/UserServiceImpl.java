package com.githup.study.dubbo.source.dynamic.service.impl;

import com.githup.study.dubbo.source.dynamic.service.IUserService;

/**
 * 实现
 */
public class UserServiceImpl implements IUserService {
    @Override
    public String getName(int id) {
        System.out.println("------getName------");
        return "Tom";
    }

    @Override
    public Integer getAge(int id) {
        System.out.println("------getAge------");
        return 10;
    }
}
