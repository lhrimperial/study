package com.githup.study.admin.thymeleaf.controller;

import com.caiwei.framework.util.string.StringUtils;
import com.githup.study.admin.thymeleaf.domain.exception.BusinessException;
import com.githup.study.admin.thymeleaf.domain.po.NewsAccessDO;
import com.githup.study.admin.thymeleaf.domain.vo.PageResultVO;
import com.githup.study.admin.thymeleaf.domain.vo.ResponseVO;
import com.githup.study.admin.thymeleaf.service.NewsAccessService;
import com.githup.study.admin.thymeleaf.util.Constants;
import com.githup.study.admin.thymeleaf.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author longhr
 * @version 2017/11/2 0002
 */
@Controller
public class NewsAccessController extends BaseController {

    @Autowired
    private NewsAccessService newsAccessService;

    @RequestMapping("/news/access/list")
    public String list(Model model, NewsAccessDO newsAccessDO, HttpServletRequest request) {
        String pageNoStr = request.getParameter("pageNo");
        int pageNo = StringUtils.isNotEmpty(pageNoStr) ? Integer.parseInt(pageNoStr) : 1;
        List<NewsAccessDO> list = newsAccessService.findByPage(newsAccessDO, pageNo, Constants.PAGE_SIZE);
        long totalCount = newsAccessService.totalCount(newsAccessDO);

        PageResultVO<List<NewsAccessDO>> resultVo = new PageResultVO<List<NewsAccessDO>>();
        resultVo.setPageNo(pageNo);
        resultVo.setTotalCount(totalCount);
        resultVo.setTotalPage(PageUtil.calculateTotalPage(totalCount,Constants.PAGE_SIZE));
        resultVo.setResult(list);

        model.addAttribute("resultVo", resultVo);
        model.addAttribute("pageinationd", PageUtil.getPageContent(request, resultVo));
        return "news/news_access_list";
    }

    @RequestMapping("/news/access/toAdd")
    public String newsTypeToAdd(Model model, HttpServletRequest request) {
        return "news/news_access_add";
    }

    @RequestMapping("/news/access/toEdit_{id}")
    public String newsTypeToEidt(Model model, @PathVariable("id") Long id) {
        model.addAttribute("newsAccessDO", newsAccessService.findById(id));
        return "news/news_access_edit";
    }

    @RequestMapping("/news/access/delete_{id}")
    public String deleteNesType(Model model, @PathVariable("id") Long id) {
        newsAccessService.delete(id);
        return "redirect:list";
    }

    @ResponseBody
    @RequestMapping(value = "/news/access/addNews", method = RequestMethod.POST)
    public ResponseVO newsTypeAdd(@RequestBody NewsAccessDO newsAccessDO) {
        String token = null;
        try {
            checkParams(newsAccessDO);
            newsAccessService.insert(newsAccessDO);
            token = newsAccessDO.getToken();
        } catch (BusinessException e) {
            return returnFailed(e.getMessage());
        }
        return returnSuccess("接入成功", token);
    }

    @ResponseBody
    @RequestMapping(value = "/news/access/editNews", method = RequestMethod.POST)
    public ResponseVO newsTypeEdit(@RequestBody NewsAccessDO newsAccessDO) {
        try {
            newsAccessService.update(newsAccessDO);
        } catch (BusinessException e) {
            return returnFailed(e.getMessage());
        }
        return returnSuccess("修改成功");
    }

    private void checkParams(NewsAccessDO newsAccessDO) throws BusinessException {
        if (StringUtils.isEmpty(newsAccessDO.getName())) {
            throw new BusinessException("分销商名称不能为空！");
        }
        if (StringUtils.isEmpty(newsAccessDO.getPushUrl())) {
            throw new BusinessException("推送地址不能为空！");
        }
        if (StringUtils.isEmpty(newsAccessDO.getReceiveType()+"")) {
            throw new BusinessException("接收类型不能为空！");
        }
        if (StringUtils.isEmpty(newsAccessDO.getPushType()+"")) {
            throw new BusinessException("推送类型不能为空！");
        }
        if (StringUtils.isEmpty(newsAccessDO.getConverterType()+"")) {
            throw new BusinessException("转换类型不能为空！");
        }
        if (StringUtils.isEmpty(newsAccessDO.getThreshold()+"")) {
            throw new BusinessException("推送或拉取频率不能为空！");
        }
    }

}
