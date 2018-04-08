package com.github.study.sboot.vue.service.impl;

import com.github.study.sboot.vue.domain.User;
import com.github.study.sboot.vue.mapper.UserMapper;
import com.github.study.sboot.vue.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public int save(User user) {
        return userMapper.save(user);
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }

    @Override
    public int delete(String userId) {
        return userMapper.delete(userId);
    }

    @Override
    public User findById(String userId) {
        return userMapper.findById(userId);
    }

    @Override
    public List<User> findUsers() {
        return userMapper.findUsers();
    }
}
