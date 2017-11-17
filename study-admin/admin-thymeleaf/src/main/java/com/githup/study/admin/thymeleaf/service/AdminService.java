package com.githup.study.admin.thymeleaf.service;


import com.githup.study.admin.thymeleaf.domain.po.AdminDO;

/**
 * @author longhr
 * @version 2017/10/31 0031
 */
public interface AdminService {

    AdminDO findByNameAndPassword(AdminDO admin);
}
