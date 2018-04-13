package com.github.study.security.shiro.base.mapper;


import com.github.study.security.shiro.base.domain.dto.UserInfoVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 **/
@Repository
public interface UserMapper {

    List<UserInfoVO> findByParam(UserInfoVO userInfoVO);

    long totalCount(UserInfoVO userInfoVO);

    UserInfoVO findByUserName(String userName);

    int update(UserInfoVO userInfoVO);

    int insert(UserInfoVO userInfoVO);
}
