package com.githup.study.admin.thymeleaf.service;


import com.githup.study.admin.thymeleaf.domain.vo.ChartDataVO;
import com.githup.study.admin.thymeleaf.domain.vo.PageResultVO;
import com.githup.study.admin.thymeleaf.domain.vo.RequestVO;

/**
 * @author longhr
 * @version 2017/11/8 0008
 */
public interface ChartService {

    ChartDataVO getGlobalData(RequestVO requestVO);

    ChartDataVO getNewsTypeData(RequestVO requestVO);

    PageResultVO findNewsSendSpeed(String msgId, String msgType, int pageNo, int pageSize);

    PageResultVO findNewsSpeedDetaiList(String msgId, String token, int pageNo, int pageSize);
}
