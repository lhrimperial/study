package com.github.study.security.shiro.base.mapper;


import com.github.study.security.shiro.base.domain.dto.RoleInfoVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 **/
@Repository
public interface RoleMapper {

    List<RoleInfoVO> findByParam(RoleInfoVO roleInfoVO);

    long totalCount(RoleInfoVO roleInfoVO);

    RoleInfoVO findByRoleCode(String roleCode);

    int update(RoleInfoVO roleInfoVO);

    int insert(RoleInfoVO roleInfoVO);
}
