package com.githup.study.admin.thymeleaf.service.impl;

import com.githup.study.admin.thymeleaf.domain.po.NewsAccessDO;
import com.githup.study.admin.thymeleaf.mapper.NewsAccessMapper;
import com.githup.study.admin.thymeleaf.service.NewsAccessService;
import com.githup.study.admin.thymeleaf.util.BmsUtil;
import com.githup.study.admin.thymeleaf.util.Constants;
import com.githup.study.admin.thymeleaf.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author longhr
 * @version 2017/11/2 0002
 */
@Service
public class NewsAccessServiceImpl implements NewsAccessService {

    @Autowired
    private NewsAccessMapper newsAccessMapper;

    @Override
    public NewsAccessDO findById(Long id) {
        return newsAccessMapper.findById(id);
    }

    @Override
    public NewsAccessDO findByParam(NewsAccessDO newsAccessDO) {
        return newsAccessMapper.findByParam(newsAccessDO);
    }

    @Override
    public List<NewsAccessDO> findByPage(NewsAccessDO newsAccessDO, int pageNo, int pageSize) {
        return newsAccessMapper.findByPage(newsAccessDO, PageUtil.getRowBounds(pageNo, pageSize));
    }

    @Override
    public long totalCount(NewsAccessDO accessDO) {
        return newsAccessMapper.totalCount(accessDO);
    }

    @Override
    public int insert(NewsAccessDO newsAccessDO) {
        if (newsAccessDO != null) {
            newsAccessDO.setToken(BmsUtil.getUUID());
            if (newsAccessDO.getOpened() == null) {
                newsAccessDO.setOpened(Constants.OPENED);
            }
            if (newsAccessDO.getPriority() == null) {
                newsAccessDO.setPriority(Constants.NORM_PRIORITY);
            }
            newsAccessDO.setModifyTime(new Date());
            newsAccessDO.setCreateTime(new Date());
        }
        return newsAccessMapper.insert(newsAccessDO);
    }

    @Override
    public int update(NewsAccessDO newsAccessDO) {
        if (newsAccessDO != null) {
            newsAccessDO.setModifyTime(new Date());
        }
        return newsAccessMapper.update(newsAccessDO);
    }

    @Override
    public int delete(Long id) {
        return newsAccessMapper.delete(id);
    }
}
