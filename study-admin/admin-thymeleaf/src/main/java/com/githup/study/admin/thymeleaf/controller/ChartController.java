package com.githup.study.admin.thymeleaf.controller;


import com.caiwei.framework.util.date.DateUtils;
import com.githup.study.admin.thymeleaf.domain.vo.ChartDataVO;
import com.githup.study.admin.thymeleaf.domain.vo.PageResultVO;
import com.githup.study.admin.thymeleaf.domain.vo.RequestVO;
import com.githup.study.admin.thymeleaf.domain.vo.ResponseVO;
import com.githup.study.admin.thymeleaf.service.ChartService;
import com.githup.study.admin.thymeleaf.service.NewsTypeService;
import com.githup.study.admin.thymeleaf.util.BmsDateUtils;
import com.githup.study.admin.thymeleaf.util.Constants;
import com.githup.study.admin.thymeleaf.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author longhr
 * @version 2017/11/7 0007
 */
@Controller
public class ChartController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(ChartController.class);

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Autowired
    private ChartService chartService;
    @Autowired
    private NewsTypeService newsTypeService;

    @RequestMapping("/chart/speed/detail")
    public String newsSpeedDetail(Model model, String msgId, String token, String pageNo, HttpServletRequest request) {
        int currentPage = 1;
        if (!StringUtils.isEmpty(pageNo)) {
            currentPage = Integer.parseInt(pageNo);
        }
        PageResultVO resultVo =  chartService.findNewsSpeedDetaiList(msgId, token, currentPage, Constants.PAGE_SIZE);
        model.addAttribute("resultVo",resultVo);
        model.addAttribute("msgId", msgId);
        model.addAttribute("token", token);
        model.addAttribute("pageinationd", PageUtil.getPageContent(request, resultVo));
        return "/chart/news_speed_detail_list";
    }

    @RequestMapping("/chart/speed/list")
    public String newsSpeedList(Model model, String msgId, String msgType, String pageNo, HttpServletRequest request) {
        int currentPage = 1;
        if (!StringUtils.isEmpty(pageNo)) {
            currentPage = Integer.parseInt(pageNo);
        }
        PageResultVO resultVo =  chartService.findNewsSendSpeed(msgId, msgType, currentPage, Constants.PAGE_SIZE);
        model.addAttribute("resultVo",resultVo);
        model.addAttribute("msgId", msgId);
        model.addAttribute("msgType", msgType);
        model.addAttribute("pageinationd", PageUtil.getPageContent(request, resultVo));
        return "/chart/news_speed_list";
    }

    @RequestMapping("/chart/global/list")
    public String globalChartList(Model model) {
        RequestVO vo = new RequestVO();
        vo.setStartTime(BmsDateUtils.getOneDayBeforeTime());
        vo.setEndTime(DateUtils.convert(new Date(),DATE_FORMAT));

        model.addAttribute("requestVo", vo);
        return "/chart/global_chartlist";
    }

    @ResponseBody
    @RequestMapping("/chart/global/getData")
    public ResponseVO getGlobalChartData(Model model, @RequestBody RequestVO requestVO) {
        ResponseVO<ChartDataVO> responseVO = null;
        if (requestVO == null || StringUtils.isEmpty(requestVO.getStartTime()) || StringUtils.isEmpty(requestVO.getEndTime())) {
            logger.info("查询参数为空:{}",requestVO);
            return returnFailed("查询参数为空");
        }
        responseVO = returnSuccess();
        responseVO.setResult(chartService.getGlobalData(requestVO));
        return responseVO;
    }

    @RequestMapping("/chart/type/list")
    public String newTypelChartList(Model model) {
        List<String> typeList = newsTypeService.findTypeList();
        RequestVO vo = new RequestVO();
        vo.setMsgType((typeList != null && typeList.size() > 0) ? typeList.get(0) : "");
        vo.setStartTime(BmsDateUtils.getOneDayBeforeTime());
        vo.setEndTime(DateUtils.convert(new Date(),DATE_FORMAT));

        model.addAttribute("typeList", typeList);
        model.addAttribute("requestVo", vo);
        return "/chart/news_type_chartlist";
    }

    @ResponseBody
    @RequestMapping("/chart/type/getData")
    public ResponseVO getNewTypeChartData(Model model, @RequestBody RequestVO requestVO) {
        ResponseVO<ChartDataVO> responseVO = null;
        logger.info("查询参数:{}",requestVO);
        if (requestVO == null || StringUtils.isEmpty(requestVO.getStartTime())
                || StringUtils.isEmpty(requestVO.getEndTime()) || StringUtils.isEmpty(requestVO.getMsgType())) {
            logger.info("查询参数为空:{}",requestVO);
            return returnFailed("查询参数为空");
        }
        responseVO = returnSuccess();
        responseVO.setResult(chartService.getNewsTypeData(requestVO));
        return responseVO;
    }

}
