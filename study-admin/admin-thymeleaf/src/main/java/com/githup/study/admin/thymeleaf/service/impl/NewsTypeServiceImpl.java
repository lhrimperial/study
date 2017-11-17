package com.githup.study.admin.thymeleaf.service.impl;

import com.githup.study.admin.thymeleaf.domain.exception.BusinessException;
import com.githup.study.admin.thymeleaf.domain.po.NewsTypeDO;
import com.githup.study.admin.thymeleaf.domain.vo.PageResultVO;
import com.githup.study.admin.thymeleaf.mapper.NewsTypeMapper;
import com.githup.study.admin.thymeleaf.service.NewsTypeService;
import com.githup.study.admin.thymeleaf.util.PageUtil;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author longhr
 * @version 2017/11/1 0001
 */
@Service
public class NewsTypeServiceImpl implements NewsTypeService {

    @Autowired
    private NewsTypeMapper newsTypeMapper;

    @Override
    public PageResultVO<List<NewsTypeDO>> findByPage(NewsTypeDO newsTypeDO, int pageNo, int pageSize) {
        PageResultVO<List<NewsTypeDO>> resultVo = new PageResultVO<List<NewsTypeDO>>();
        long totalCount = newsTypeMapper.totalCount(newsTypeDO);
        long totalPage = PageUtil.calculateTotalPage(totalCount, pageSize);
        RowBounds rowBounds = new RowBounds((pageNo - 1) * pageSize, pageSize);
        resultVo.setResult(newsTypeMapper.findByPage(newsTypeDO, rowBounds));
        resultVo.setTotalCount(totalCount);
        resultVo.setTotalPage(totalPage);
        resultVo.setPageNo(pageNo);
        return resultVo;
    }

    @Override
    public List<String> findTypeList() {
        return newsTypeMapper.findTypeList();
    }

    @Override
    public NewsTypeDO findById(Long id) {
        return newsTypeMapper.findById(id);
    }

    @Override
    public int insert(NewsTypeDO newsTypeDO) {
        NewsTypeDO existDO = newsTypeMapper.findByType(newsTypeDO.getType());
        if (existDO != null) {
            throw new BusinessException("改消息类型已存在！");
        }
        if (newsTypeDO.getOpened() == -1) {
            newsTypeDO.setOpened(1);
        }
        newsTypeDO.setModifyTime(new Date());
        newsTypeDO.setCreateTime(new Date());
        return newsTypeMapper.insert(newsTypeDO);
    }

    @Override
    public int update(NewsTypeDO newsTypeDO) {
        if (newsTypeDO != null) {
            newsTypeDO.setModifyTime(new Date());
            if (newsTypeDO.getOpened() == -1) {
                newsTypeDO.setOpened(1);
            }
        }
        return newsTypeMapper.update(newsTypeDO);
    }

    @Override
    public int delete(Long id) {
        return newsTypeMapper.delete(id);
    }
}
