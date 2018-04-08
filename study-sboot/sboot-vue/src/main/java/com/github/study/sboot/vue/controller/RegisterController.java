package com.github.study.sboot.vue.controller;

import com.alibaba.fastjson.JSON;
import com.github.framework.server.shared.domain.dto.ResponseDTO;
import com.github.study.sboot.vue.domain.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 **/
@RestController
public class RegisterController extends AbstractController{

    @RequestMapping("/register")
    public ResponseDTO register(@RequestBody User user) {
        System.out.println(JSON.toJSONString(user));
        return returnSuccess();
    }
}
