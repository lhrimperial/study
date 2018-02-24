package com.githup.study.security.shiro.web.mapper;

import com.githup.study.security.shiro.web.domain.po.ResourcePO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface ResourceMapper {

    int update(ResourcePO resourcePO);

    @Select("select * from t_resources where id = #{id}")
    ResourcePO findById(Integer id);

    @Insert("insert into t_resources(parent_id,title,url,type,order,desc) values(#{parent_id},#{title},#{url},#{type},#{order},#{desc})")
    int save(ResourcePO resourcePO);

    @Delete("delete from t_resources where id = #{id}")
    int delete(Integer id);
}
