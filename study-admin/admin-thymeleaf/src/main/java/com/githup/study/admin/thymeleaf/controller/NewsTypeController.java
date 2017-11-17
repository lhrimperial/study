package com.githup.study.admin.thymeleaf.controller;

import com.caiwei.framework.util.string.StringUtils;
import com.githup.study.admin.thymeleaf.domain.exception.BusinessException;
import com.githup.study.admin.thymeleaf.domain.po.NewsTypeDO;
import com.githup.study.admin.thymeleaf.domain.vo.PageResultVO;
import com.githup.study.admin.thymeleaf.domain.vo.ResponseVO;
import com.githup.study.admin.thymeleaf.service.NewsTypeService;
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
 * @version 2017/11/1 0001
 */
@Controller
public class NewsTypeController extends BaseController{

    @Autowired
    private NewsTypeService newsTypeService;

    @RequestMapping("/news/type/toAdd")
    public String newsTypeToAdd(Model model, HttpServletRequest request) {
        return "news/news_type_add";
    }

    @ResponseBody
    @RequestMapping(value = "/news/type/addNews", method = RequestMethod.POST)
    public ResponseVO newsTypeAdd(@RequestBody NewsTypeDO newsTypeDO) {
        try {
            newsTypeService.insert(newsTypeDO);
        } catch (BusinessException e) {
            return returnFailed(e.getMessage());
        }
        return returnSuccess("保存成功");
    }

    @RequestMapping("/news/type/list")
    public String newsTypeList(Model model, NewsTypeDO newsTypeDO, HttpServletRequest request) {
        String pageNoStr = request.getParameter("pageNo");
        PageResultVO<List<NewsTypeDO>> resultVo = newsTypeService.findByPage(newsTypeDO,
                StringUtils.isNotEmpty(pageNoStr) ? Integer.parseInt(pageNoStr) : 1, Constants.PAGE_SIZE);
        model.addAttribute("resultVo", resultVo);
        model.addAttribute("pageinationd", PageUtil.getPageContent(request, resultVo));
        return "news/news_type_list";
    }

    @RequestMapping("/news/type/toEdit_{id}")
    public String newsTypeToEidt(Model model, @PathVariable("id") Long id) {
        model.addAttribute("newsTypeDO", newsTypeService.findById(id));
        return "news/news_type_edit";
    }

    @ResponseBody
    @RequestMapping(value = "/news/type/editNews", method = RequestMethod.POST)
    public ResponseVO newsTypeEdit(@RequestBody NewsTypeDO newsTypeDO) {
        try {
            newsTypeService.update(newsTypeDO);
        } catch (BusinessException e) {
            return returnFailed(e.getMessage());
        }
        return returnSuccess("修改成功");
    }

    @RequestMapping("/news/type/delete_{id}")
    public String deleteNesType(Model model, @PathVariable("id") Long id) {
        newsTypeService.delete(id);
        return "redirect:list";
    }

}
