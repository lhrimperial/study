package com.github.study.sboot.vue.service;

import com.github.study.sboot.vue.domain.User;

import java.util.List;

/**
 *
 **/
public interface IUserService {

    int save(User user);

    int update(User user);

    int delete(String userId);

    User findById(String userId);

    List<User> findUsers();
}
