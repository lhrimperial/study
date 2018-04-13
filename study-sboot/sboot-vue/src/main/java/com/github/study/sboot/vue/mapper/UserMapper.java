package com.github.study.sboot.vue.mapper;

import com.github.study.sboot.vue.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 **/
@Repository
public interface UserMapper {

    @Insert("insert into t_user(user_id,user_name,password,mobile_no,email) values(#{userId},#{userName},#{password},#{mobileNo},#{email})")
    int save(User user);

    int update(User user);

    @Delete("delete from t_user where user_id = #{userId}")
    int delete(String userId);

    @Select("select user_id as userId,user_name as userName,password,mobile_no as mobileNo,email from t_user where user_id = #{userId}")
    User findById(String userId);

    List<User> findUsers();
}
