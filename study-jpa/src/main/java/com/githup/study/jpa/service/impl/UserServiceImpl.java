package com.githup.study.jpa.service.impl;

import com.githup.study.jpa.dao.BaseDao;
import com.githup.study.jpa.entity.UserPO;
import com.githup.study.jpa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BaseDao<UserPO,String> baseDao;

    @Override
    @Transactional
    public void save(UserPO userPO) {
        baseDao.add(userPO);
    }

    @Transactional
    public UserPO find(String uuid) {
        return (UserPO) baseDao.getByKey(uuid);
    }
}
