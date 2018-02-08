package com.githup.study.security.shiro.web.mapper;

import com.githup.study.security.shiro.web.domain.po.UserPO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface UserMapper {

    int update(UserPO userPO);

    @Select("select * from t_user where user_name = #{userName}")
    UserPO findByUserName(String userName);

    @Insert("insert into t_user(user_name,password,real_name,email,state) values(#{userName},#{password},#{realName},#{email},#{state})")
    int save(UserPO userPO);
}
