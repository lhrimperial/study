package com.githup.study.admin.thymeleaf.mapper;

import com.githup.study.admin.thymeleaf.domain.po.GlobalStatisticsDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author longhr
 * @version 2017/11/7 0007
 */
@Repository
public interface GlobalStatisticsMapper {

    int insert(GlobalStatisticsDO globalStatisticsDO);

    int update(GlobalStatisticsDO globalStatisticsDO);

    int delete(Long id);

    List<GlobalStatisticsDO> findByTimeSlot(@Param("startTime") Long startTime, @Param("endTime") Long endTime);
}
