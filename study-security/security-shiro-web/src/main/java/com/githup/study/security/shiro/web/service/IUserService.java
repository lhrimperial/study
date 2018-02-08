package com.githup.study.security.shiro.web.service;

import com.githup.study.security.shiro.web.domain.po.UserPO;

/**
 *
 */
public interface IUserService {

    UserPO getByUserName(String userName);

}
