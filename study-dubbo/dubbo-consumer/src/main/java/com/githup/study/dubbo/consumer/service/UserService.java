package com.githup.study.dubbo.consumer.service;

import com.github.study.domain.User;
import com.github.study.service.IUserService;

/**
 * @author longhairen
 * @create 2017/8/11 0011 上午 9:12
 */
public class UserService implements IUserService {
    @Override
    public User findUser(String userName) {
        User user = new User();
        user.setPassword("1234");
        user.setUserName("andy");
        return user;
    }
}
