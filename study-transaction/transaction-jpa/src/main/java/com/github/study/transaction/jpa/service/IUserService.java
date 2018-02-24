package com.github.study.transaction.jpa.service;


import com.github.study.transaction.jpa.domain.User;

/**
 *
 */
public interface IUserService {

    User getByLoginName(String username, boolean isok);
}
