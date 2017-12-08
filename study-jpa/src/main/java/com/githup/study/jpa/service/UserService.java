package com.githup.study.jpa.service;

import com.githup.study.jpa.entity.UserPO;

/**
 *
 */
public interface UserService {
    void save(UserPO userPO);

    UserPO find(String uuid);
}
