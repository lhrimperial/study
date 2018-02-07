package com.githup.study.security.shiro.web.service;

import com.githup.study.security.shiro.web.domain.PermsPO;

import java.util.List;

/**
 *
 */
public interface IPermissionService {

    List<PermsPO> getByUserId(Integer userId);
}
