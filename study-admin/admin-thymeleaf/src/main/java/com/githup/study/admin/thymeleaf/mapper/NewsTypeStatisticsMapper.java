package com.githup.study.admin.thymeleaf.mapper;

import com.githup.study.admin.thymeleaf.domain.po.NewsTypeStatisticsDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author longhr
 * @version 2017/11/8 0008
 */
@Repository
public interface NewsTypeStatisticsMapper {

    int insert(NewsTypeStatisticsDO newsTypeStatisticsDO);

    int update(NewsTypeStatisticsDO newsTypeStatisticsDO);

    int delete(Long id);

    List<NewsTypeStatisticsDO> findNewsTypeStatis(@Param("msgType") String msgType, @Param("startTime") Long startTime, @Param("endTime") Long endTime);
}
