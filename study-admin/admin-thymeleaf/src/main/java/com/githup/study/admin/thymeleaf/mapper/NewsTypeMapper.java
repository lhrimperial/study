package com.githup.study.admin.thymeleaf.mapper;

import com.githup.study.admin.thymeleaf.domain.po.NewsTypeDO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author longhr
 * @version 2017/11/1 0001
 */
@Repository
public interface NewsTypeMapper {

    List<NewsTypeDO> findByPage(NewsTypeDO newsTypeDO, RowBounds rowBounds);

    long totalCount(NewsTypeDO newsTypeDO);

    List<String> findTypeList();

    NewsTypeDO findById(Long id);

    NewsTypeDO findByType(String type);

    int insert(NewsTypeDO newsTypeDO);

    int insertBatch(List<NewsTypeDO> newsTypeDOS);

    int update(NewsTypeDO newsTypeDO);

    int delete(Long id);
}
