package com.githup.study.admin.thymeleaf.service.impl;

import com.githup.study.admin.thymeleaf.domain.po.AdminDO;
import com.githup.study.admin.thymeleaf.service.AdminService;
import org.springframework.stereotype.Service;

/**
 * @author longhr
 * @version 2017/10/31 0031
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Override
    public AdminDO findByNameAndPassword(AdminDO admin) {
        //TODO
        return admin;
    }
}
