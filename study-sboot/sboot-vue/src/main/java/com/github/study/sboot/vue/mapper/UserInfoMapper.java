package com.github.study.sboot.vue.mapper;

import com.github.study.sboot.vue.domain.UserInfo;
import org.springframework.stereotype.Repository;

/**
 *
 **/
@Repository
public interface UserInfoMapper {

    UserInfo findUserInfoByUserName(String name);

}
