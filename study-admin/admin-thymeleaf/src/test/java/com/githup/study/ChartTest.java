package com.githup.study;

import com.alibaba.fastjson.JSON;
import com.caiwei.framework.util.date.DateUtils;
import com.githup.study.admin.thymeleaf.Main;
import com.githup.study.admin.thymeleaf.domain.po.*;
import com.githup.study.admin.thymeleaf.domain.vo.ChartDataVO;
import com.githup.study.admin.thymeleaf.domain.vo.NewsSpeedDetailVO;
import com.githup.study.admin.thymeleaf.domain.vo.RequestVO;
import com.githup.study.admin.thymeleaf.mapper.GlobalStatisticsMapper;
import com.githup.study.admin.thymeleaf.mapper.NewsSendSpeedMapper;
import com.githup.study.admin.thymeleaf.mapper.NewsTypeStatisticsMapper;
import com.githup.study.admin.thymeleaf.service.ChartService;
import com.githup.study.admin.thymeleaf.service.NewsAccessService;
import com.githup.study.admin.thymeleaf.service.NewsTypeService;
import com.githup.study.admin.thymeleaf.util.BmsDateUtils;
import com.githup.study.admin.thymeleaf.util.FastJsonUtil;
import org.apache.ibatis.session.RowBounds;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author longhr
 * @version 2017/11/8 0008
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
public class ChartTest {

    @Autowired
    private GlobalStatisticsMapper mapper;
    @Autowired
    private NewsTypeStatisticsMapper newsTypeStatisticsMapper;
    @Autowired
    private NewsTypeService newsTypeService;

    @Autowired
    private ChartService chartService;

    @Autowired
    private NewsAccessService newsAccessService;

    @Autowired
    private NewsSendSpeedMapper sendSpeedMapper;

    @Test
    public void speedDetailTest() {
        List<NewsSpeedDetailVO> vos = sendSpeedMapper.findSpeedDetailByPage("9AEF438A3AC047A78B964DB31BEDAF28","", new RowBounds(0, 20));
        long count = sendSpeedMapper.totalSpeedDetailCount("9AEF438A3AC047A78B964DB31BEDAF28","");
        System.out.println(JSON.toJSONString(vos));
        System.out.println(count);
    }

//    @Test
    public void sendSpeedTest() {
        List<NewsAccessDO> list = newsAccessService.findByPage(new NewsAccessDO(), 1, 50);
        List<MessageDO> messageDOS = null;//messageDOMapper.findMessage();
        int size = list.size()*messageDOS.size();
        List<MessageTokenDO> tokenDOS = new ArrayList<>(size);
        MessageTokenDO tokenDO = null;
        String msgId = null;
        String token = null;
        for (int i = 0,len1 = messageDOS.size(); i < len1; i++) {
            msgId = messageDOS.get(i).getId();
            for (int j = 0,len2 = list.size(); j < len2; j++) {
                tokenDO = new MessageTokenDO();
                tokenDO.setMsgId(msgId);
                tokenDO.setToken(list.get(j).getToken());
                tokenDO.setNowRetry(Short.valueOf("3"));
                tokenDO.setModifyTime(new Date());
                tokenDO.setStatus(Byte.valueOf(""+j%4));
                tokenDOS.add(tokenDO);
            }
        }
      //  messageTokenDOMapper.batchInsert(tokenDOS);
    }

    @Test
    public void newsTypestatisticsFindTest() {
        /*List<NewsTypeStatisticsDO> list = newsTypeStatisticsMapper.findNewsTypeStatis("type1", 1510105487000L,1510191887000L);
        Assert.assertNotNull(list.size());*/
        RequestVO vo = new RequestVO();
        vo.setMsgType("type1");
        vo.setStartTime(BmsDateUtils.getOneDayBeforeTime());
        vo.setEndTime(DateUtils.convert(new Date(),BmsDateUtils.DATE_FORMAT));
        ChartDataVO chartDataVO = chartService.getNewsTypeData(vo);
        System.out.println(FastJsonUtil.toJsonString(chartDataVO));
    }

//    @Test
    public void insertNewsTypeStatistics() {
        Random random = new Random(1000);
        NewsTypeStatisticsDO statisticsDO = null;
        List types = newsTypeService.findTypeList();
        for (int i = 0; i < 200; i++) {
            statisticsDO = new NewsTypeStatisticsDO();
            statisticsDO.setMsgType((String) types.get(random.nextInt(types.size())));
            statisticsDO.setReachCount(random.nextInt(500));
            statisticsDO.setReceiveCount(random.nextInt(500));
            statisticsDO.setSendCount(random.nextInt(1000));
            statisticsDO.setFailCount(random.nextInt(100));
            statisticsDO.setTimestamp(System.currentTimeMillis());
            newsTypeStatisticsMapper.insert(statisticsDO);
        }
    }

    @Test
    public void getDateTest() {
        RequestVO reqvo = new RequestVO();
        reqvo.setStartTime(BmsDateUtils.getOneDayBeforeTime());
        reqvo.setEndTime(DateUtils.convert(new Date(),BmsDateUtils.DATE_FORMAT));
        ChartDataVO chartDataVO = chartService.getGlobalData(reqvo);
        System.out.println(FastJsonUtil.toJsonString(chartDataVO));

    }

//    @Test
    public void insertGlobalTest() {
        Random random = new Random(1000);
        GlobalStatisticsDO statisticsDO = null;
        for (int i = 0; i < 200; i++) {
            statisticsDO = new GlobalStatisticsDO();
            statisticsDO.setReachCount(random.nextInt(500));
            statisticsDO.setReceiveCount(random.nextInt(500));
            statisticsDO.setSendCount(random.nextInt(1000));
            statisticsDO.setFailCount(random.nextInt(100));
            statisticsDO.setTimestamp(System.currentTimeMillis());
            mapper.insert(statisticsDO);
        }

    }
}
