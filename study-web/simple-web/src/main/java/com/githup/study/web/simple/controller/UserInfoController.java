package com.githup.study.web.simple.controller;

import com.githup.study.web.simple.domain.UserInfo;
import com.githup.study.web.simple.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author longhairen
 * @create 2017/8/21 0021 下午 4:28
 */
@Controller
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping("/find/{id}")
    public @ResponseBody
    UserInfo findUser(@PathVariable("id") int id){
        return userInfoService.findUserInfo(id);
    }

    @RequestMapping("/list")
    public @ResponseBody
    List<UserInfo> findUserList(){
        return userInfoService.findUserList();
    }

}
