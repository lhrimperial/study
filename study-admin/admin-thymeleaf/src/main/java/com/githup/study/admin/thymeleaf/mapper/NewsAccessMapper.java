package com.githup.study.admin.thymeleaf.mapper;

import com.githup.study.admin.thymeleaf.domain.po.NewsAccessDO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author longhr
 * @version 2017/11/2 0002
 */
@Repository
public interface NewsAccessMapper {

    NewsAccessDO findById(Long id);

    NewsAccessDO findByParam(NewsAccessDO newsAccessDO);

    List<NewsAccessDO> findByPage(NewsAccessDO newsAccessDO, RowBounds rowBounds);

    long totalCount(NewsAccessDO newsAccessDO);

    int insert(NewsAccessDO newsAccessDO);

    int update(NewsAccessDO newsAccessDO);

    int delete(Long id);
}
