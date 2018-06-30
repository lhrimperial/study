package com.github.study.security.shiro.base.service.impl;


import com.github.framework.util.string.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.study.security.shiro.base.domain.define.IFarmConstants;
import com.github.study.security.shiro.base.domain.dto.RoleInfoVO;
import com.github.study.security.shiro.base.mapper.RoleMapper;
import com.github.study.security.shiro.base.service.IRoleInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class RoleInfoServiceImpl implements IRoleInfoService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleInfoVO> findByParam(RoleInfoVO roleInfoVO) {
        if (roleInfoVO == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        PageHelper.startPage(roleInfoVO.getPage(), roleInfoVO.getLimit());
        return roleMapper.findByParam(roleInfoVO);
    }

    @Override
    public RoleInfoVO findByRoleCode(String roleCode) {
        if (StringUtils.isBlank(roleCode)) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        return roleMapper.findByRoleCode(roleCode);
    }

    @Override
    public long totalCount(RoleInfoVO roleInfoVO) {
        if (roleInfoVO == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        return roleMapper.totalCount(roleInfoVO);
    }

    @Override
    public int delete(String roleCode) {
        if (StringUtils.isBlank(roleCode)) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        RoleInfoVO roleInfoVO = new RoleInfoVO();
        roleInfoVO.setActive(IFarmConstants.INACTIVE);
        roleInfoVO.setRoleCode(roleCode);
        roleInfoVO.setModifyTime(new Date());
        return roleMapper.update(roleInfoVO);
    }

    @Override
    public int update(RoleInfoVO roleInfoVO) {
        if (roleInfoVO == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        roleInfoVO.setModifyTime(new Date());
        return roleMapper.update(roleInfoVO);
    }

    @Override
    public int insert(RoleInfoVO roleInfoVO) {
        if (roleInfoVO == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        roleInfoVO.setCreateTime(new Date());
        roleInfoVO.setModifyTime(new Date());
        roleInfoVO.setActive(IFarmConstants.ACTIVE);
        return roleMapper.insert(roleInfoVO);
    }
}
