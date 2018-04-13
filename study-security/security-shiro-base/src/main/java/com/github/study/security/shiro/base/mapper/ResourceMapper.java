package com.github.study.security.shiro.base.mapper;


import com.github.study.security.shiro.base.domain.dto.ResourceVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 **/
@Repository
public interface ResourceMapper {

    List<ResourceVO> findByParam(ResourceVO resourceVO);

    long totalCount(ResourceVO resourceVO);

    ResourceVO findByResCode(String resCode);

    int update(ResourceVO resourceVO);

    int insert(ResourceVO resourceVO);
}
