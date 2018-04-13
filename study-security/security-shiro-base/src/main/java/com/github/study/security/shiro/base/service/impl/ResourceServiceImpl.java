package com.github.study.security.shiro.base.service.impl;


import com.github.framework.util.string.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.study.security.shiro.base.domain.define.IFarmConstants;
import com.github.study.security.shiro.base.domain.dto.ResourceVO;
import com.github.study.security.shiro.base.mapper.ResourceMapper;
import com.github.study.security.shiro.base.service.IResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class ResourceServiceImpl implements IResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public List<ResourceVO> findByParam(ResourceVO resourceVO) {
        if (resourceVO == null) {
            throw new IllegalArgumentException("参数不能为空！");
        }
        PageHelper.startPage(resourceVO.getPage(), resourceVO.getLimit());
        return resourceMapper.findByParam(resourceVO);
    }

    @Override
    public ResourceVO findByResCode(String resCode) {
        if (StringUtils.isBlank(resCode)){
            throw new IllegalArgumentException("参数不能为空！");
        }
        return resourceMapper.findByResCode(resCode);
    }

    @Override
    public long totalCount(ResourceVO resourceVO) {
        if (resourceVO == null) {
            throw new IllegalArgumentException("参数不能为空！");
        }
        return resourceMapper.totalCount(resourceVO);
    }

    @Override
    public int delete(String resCode) {
        if (StringUtils.isBlank(resCode)){
            throw new IllegalArgumentException("参数不能为空！");
        }
        ResourceVO resourceVO = new ResourceVO();
        resourceVO.setResourceCode(resCode);
        resourceVO.setModifyTime(new Date());
        resourceVO.setActive(IFarmConstants.INACTIVE);
        return resourceMapper.update(resourceVO);
    }

    @Override
    public int update(ResourceVO resourceVO) {
        if (resourceVO == null) {
            throw new IllegalArgumentException("参数不能为空！");
        }
        resourceVO.setModifyTime(new Date());
        return resourceMapper.update(resourceVO);
    }

    @Override
    public int insert(ResourceVO resourceVO) {
        if (resourceVO == null) {
            throw new IllegalArgumentException("参数不能为空！");
        }
        resourceVO.setActive(IFarmConstants.ACTIVE);
        resourceVO.setCreateTime(new Date());
        resourceVO.setModifyTime(new Date());
        return resourceMapper.insert(resourceVO);
    }
}
