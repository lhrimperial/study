package com.githup.study.security.shiro.web.mapper;

import com.githup.study.security.shiro.web.domain.UserPO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface UserMapper {

    int update(UserPO userPO);

    @Select("select * from t_user where userId = #{userId}")
    UserPO findById(Integer userId);

    @Insert("insert into t_user(userId,userName,password,realName,email,state) values(#{userId},#{userName},#{password},#{realName},#{email},#{state})")
    int save(UserPO userPO);

    @Delete("delete from t_user where userId = #{userId}")
    int delete(Integer userId);
}
