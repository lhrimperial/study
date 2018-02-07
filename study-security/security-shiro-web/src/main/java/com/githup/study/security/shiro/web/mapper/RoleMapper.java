package com.githup.study.security.shiro.web.mapper;

import com.githup.study.security.shiro.web.domain.RolePO;
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

    @Select("select * from t_role where roleId = #{roleId}")
    RolePO findById(Integer roleId);

    @Insert("insert into t_role(roleId,roleName,roleDesc,permission) values(#{roleId},#{roleName},#{roleDesc},#{permission})")
    int save(RolePO rolePO);

    @Delete("delete from t_role where roleId = #{roleId}")
    int delete(Integer roleId);
}
