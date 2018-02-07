package com.githup.study.security.shiro.web.mapper;

import com.githup.study.security.shiro.web.domain.PermsPO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface PermsMapper {

    int update(PermsPO treePO);

    @Select("select * from t_tree where treeId = #{treeId}")
    PermsPO findById(Integer treeId);

    @Insert("insert into t_tree(treeId,title,desc,parentId,url,perms,type,order) values(#{treeId},#{title},#{desc},#{parentId},#{url},#{perms},#{type},#{order})")
    int save(PermsPO treePO);

    @Delete("delete from t_tree where treeId = #{treeId}")
    int delete(Integer treeId);
}
