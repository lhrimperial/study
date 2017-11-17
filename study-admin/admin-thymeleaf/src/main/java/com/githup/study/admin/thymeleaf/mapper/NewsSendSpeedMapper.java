package com.githup.study.admin.thymeleaf.mapper;

import com.githup.study.admin.thymeleaf.domain.po.NewsSendSpeedDO;
import com.githup.study.admin.thymeleaf.domain.vo.NewsSpeedDetailVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author longhr
 * @version 2017/11/8 0008
 */
@Repository
public interface NewsSendSpeedMapper {

    List<NewsSendSpeedDO> findByPage(@Param("msgId") String msgId, @Param("msgType") String msgType, RowBounds rowBounds);

    long totalCount(@Param("msgId") String msgId, @Param("msgType") String msgType);

    List<NewsSpeedDetailVO> findSpeedDetailByPage(@Param("msgId") String msgId, @Param("token") String token, RowBounds rowBounds);

    long totalSpeedDetailCount(@Param("msgId") String msgId, @Param("token") String token);

    int insert(NewsSendSpeedDO newsSendSpeedDO);

    int update(NewsSendSpeedDO newsSendSpeedDO);

    int delete(Long id);


}
