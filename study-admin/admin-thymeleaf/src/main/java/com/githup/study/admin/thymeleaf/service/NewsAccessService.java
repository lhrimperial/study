package com.githup.study.admin.thymeleaf.service;


import com.githup.study.admin.thymeleaf.domain.po.NewsAccessDO;

import java.util.List;

/**
 * @author longhr
 * @version 2017/11/2 0002
 */
public interface NewsAccessService {

    NewsAccessDO findById(Long id);

    NewsAccessDO findByParam(NewsAccessDO newsAccessDO);

    List<NewsAccessDO> findByPage(NewsAccessDO newsAccessDO, int pageNo, int pageSize);

    long totalCount(NewsAccessDO accessDO);

    int insert(NewsAccessDO newsAccessDO);

    int update(NewsAccessDO newsAccessDO);

    int delete(Long id);
}
