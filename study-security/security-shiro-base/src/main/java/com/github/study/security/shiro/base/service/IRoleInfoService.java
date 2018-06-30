package com.github.study.security.shiro.base.service;


import com.github.study.security.shiro.base.domain.dto.RoleInfoVO;

import java.util.List;

/**
 *
 */
public interface IRoleInfoService {

    List<RoleInfoVO> findByParam(RoleInfoVO roleInfoVO);

    RoleInfoVO findByRoleCode(String roleCode);

    long totalCount(RoleInfoVO roleInfoVO);

    int delete(String roleCode);

    int update(RoleInfoVO roleInfoVO);

    int insert(RoleInfoVO roleInfoVO);
}
