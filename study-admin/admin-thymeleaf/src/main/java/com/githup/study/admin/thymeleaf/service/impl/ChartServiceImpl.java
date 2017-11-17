package com.githup.study.admin.thymeleaf.service.impl;

import com.githup.study.admin.thymeleaf.domain.po.GlobalStatisticsDO;
import com.githup.study.admin.thymeleaf.domain.po.NewsTypeStatisticsDO;
import com.githup.study.admin.thymeleaf.domain.vo.ChartDataVO;
import com.githup.study.admin.thymeleaf.domain.vo.PageResultVO;
import com.githup.study.admin.thymeleaf.domain.vo.RequestVO;
import com.githup.study.admin.thymeleaf.mapper.GlobalStatisticsMapper;
import com.githup.study.admin.thymeleaf.mapper.NewsSendSpeedMapper;
import com.githup.study.admin.thymeleaf.mapper.NewsTypeStatisticsMapper;
import com.githup.study.admin.thymeleaf.service.ChartService;
import com.githup.study.admin.thymeleaf.util.BmsDateUtils;
import com.githup.study.admin.thymeleaf.util.Constants;
import com.githup.study.admin.thymeleaf.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author longhr
 * @version 2017/11/8 0008
 */
@Service
public class ChartServiceImpl implements ChartService {
    private Logger logger = LoggerFactory.getLogger(ChartServiceImpl.class);

    @Autowired
    private GlobalStatisticsMapper globalStatisticsMapper;
    @Autowired
    private NewsTypeStatisticsMapper newsTypeStatisticsMapper;
    @Autowired
    private NewsSendSpeedMapper newsSendSpeedMapper;

    private int getStep(int size) {
        return size % Constants.SHOW_SIZE == 0 ? size / Constants.SHOW_SIZE : size / Constants.SHOW_SIZE + 1;
    }
    @Override
    public ChartDataVO getGlobalData(RequestVO requestVO) {
        ChartDataVO chartDataVO = new ChartDataVO();
        if (requestVO != null) {
            Long startTime = BmsDateUtils.getTimeMillis(requestVO.getStartTime());
            Long endTime = BmsDateUtils.getTimeMillis(requestVO.getEndTime());
            List<GlobalStatisticsDO> list = globalStatisticsMapper.findByTimeSlot(startTime, endTime);
            int size = list.size();
            int step = 1;
            if (size > Constants.SHOW_SIZE) {
                step = getStep(size);
            }
            GlobalStatisticsDO statisticsDO = null;
            List<String> lables = new ArrayList<>();
            List<String> reachCounts = new ArrayList<>();
            List<String> receiveCounts = new ArrayList<>();
            List<String> failCounts = new ArrayList<>();
            List<String> sendCounts = new ArrayList<>();
            for (int i = 0; i < size; i = i + step) {
                statisticsDO = list.get(i);
                lables.add(BmsDateUtils.getTimeFormat(statisticsDO.getTimestamp(),"MM-dd HH:mm"));
                reachCounts.add(String.valueOf(statisticsDO.getReachCount()));
                receiveCounts.add(String.valueOf(statisticsDO.getReceiveCount()));
                failCounts.add(String.valueOf(statisticsDO.getFailCount()));
                sendCounts.add(String.valueOf(statisticsDO.getSendCount()));
            }

            Map map = new HashMap();
            map.put("reachCounts", reachCounts);
            map.put("receiveCounts", receiveCounts);
            map.put("failCounts", failCounts);
            map.put("sendCounts", sendCounts);

            chartDataVO.setLabels(lables);
            chartDataVO.setDatasetsMap(map);

        }
        return chartDataVO;
    }

    @Override
    public ChartDataVO getNewsTypeData(RequestVO requestVO) {
        ChartDataVO chartDataVO = new ChartDataVO();
        if (requestVO != null) {
            Long startTime = BmsDateUtils.getTimeMillis(requestVO.getStartTime());
            Long endTime = BmsDateUtils.getTimeMillis(requestVO.getEndTime());
            List<NewsTypeStatisticsDO> list = newsTypeStatisticsMapper.findNewsTypeStatis(requestVO.getMsgType(), startTime, endTime);
            int size = list.size();
            int step = 1;
            if (size > Constants.SHOW_SIZE) {
                step = getStep(size);
            }
            NewsTypeStatisticsDO statisticsDO = null;
            List<String> lables = new ArrayList<>();
            List<String> reachCounts = new ArrayList<>();
            List<String> receiveCounts = new ArrayList<>();
            List<String> failCounts = new ArrayList<>();
            List<String> sendCounts = new ArrayList<>();
            for (int i = 0; i < size; i = i + step) {
                statisticsDO = list.get(i);
                lables.add(BmsDateUtils.getTimeFormat(statisticsDO.getTimestamp(),"MM-dd HH:mm"));
                reachCounts.add(String.valueOf(statisticsDO.getReachCount()));
                receiveCounts.add(String.valueOf(statisticsDO.getReceiveCount()));
                failCounts.add(String.valueOf(statisticsDO.getFailCount()));
                sendCounts.add(String.valueOf(statisticsDO.getSendCount()));
            }

            Map map = new HashMap();
            map.put("reachCounts", reachCounts);
            map.put("receiveCounts", receiveCounts);
            map.put("failCounts", failCounts);
            map.put("sendCounts", sendCounts);

            chartDataVO.setLabels(lables);
            chartDataVO.setDatasetsMap(map);

        }
        return chartDataVO;
    }

    @Override
    public PageResultVO findNewsSendSpeed(String msgId, String msgType, int pageNo, int pageSize) {
        long totalCount = newsSendSpeedMapper.totalCount(msgId, msgType);
        long totalPage = PageUtil.calculateTotalPage(totalCount, pageSize);
        PageResultVO responseVO = new PageResultVO();
        responseVO.setPageNo(pageNo);
        responseVO.setTotalCount(totalCount);
        responseVO.setTotalPage(totalPage);
        responseVO.setPageSize(pageSize);
        responseVO.setResult(newsSendSpeedMapper.findByPage(msgId, msgType,PageUtil.getRowBounds(pageNo, pageSize)));
        return responseVO;
    }

    @Override
    public PageResultVO findNewsSpeedDetaiList(String msgId, String token, int pageNo, int pageSize) {
        logger.info("查询参数:{},{}",msgId,token);
        long totalCount = newsSendSpeedMapper.totalSpeedDetailCount(msgId, token);
        long totalPage = PageUtil.calculateTotalPage(totalCount, pageSize);
        PageResultVO responseVO = new PageResultVO();
        responseVO.setPageNo(pageNo);
        responseVO.setTotalCount(totalCount);
        responseVO.setTotalPage(totalPage);
        responseVO.setPageSize(pageSize);
        responseVO.setResult(newsSendSpeedMapper.findSpeedDetailByPage(msgId, token,PageUtil.getRowBounds(pageNo, pageSize)));
        return responseVO;
    }
}
