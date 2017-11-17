package com.githup.study.admin.thymeleaf.service;


import com.githup.study.admin.thymeleaf.domain.po.NewsTypeDO;
import com.githup.study.admin.thymeleaf.domain.vo.PageResultVO;

import java.util.List;

/**
 * @author longhr
 * @version 2017/11/1 0001
 */
public interface NewsTypeService {

    PageResultVO<List<NewsTypeDO>> findByPage(NewsTypeDO newsTypeDO, int pageNo, int pageSize);

    List<String> findTypeList();

    NewsTypeDO findById(Long id);

    int insert(NewsTypeDO newsTypeDO);

    int update(NewsTypeDO newsTypeDO);

    int delete(Long id);
}
