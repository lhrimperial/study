package com.github.study.security.shiro.base.service;


import com.github.study.security.shiro.base.domain.dto.ResourceVO;

import java.util.List;

/**
 *
 */
public interface IResourceService {

    List<ResourceVO> findByParam(ResourceVO resourceVO);

    ResourceVO findByResCode(String resCode);

    long totalCount(ResourceVO resourceVO);

    int delete(String resCode);

    int update(ResourceVO resourceVO);

    int insert(ResourceVO resourceVO);
}
