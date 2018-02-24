package com.githup.study.security.shiro.web.mapper;

import com.githup.study.security.shiro.web.domain.po.RolePO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface RoleMapper {

    int update(RolePO rolePO);

    @Select("select * from t_role where id = #{roleId}")
    RolePO findById(Integer roleId);

    @Insert("insert into t_role(role_name,role_desc) values(#{roleName},#{roleDesc})")
    int save(RolePO rolePO);

    @Delete("delete from t_role where id = #{roleId}")
    int delete(Integer roleId);
}
