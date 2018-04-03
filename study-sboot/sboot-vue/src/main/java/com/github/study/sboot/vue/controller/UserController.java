package com.github.study.sboot.vue.controller;

import com.alibaba.fastjson.JSON;
import com.github.study.sboot.vue.domain.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @RequestMapping("/user")
    public User getUser(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String url = request.getRequestURL().toString();
        System.out.println("url:"+url);
        System.out.println(id);
        return new User("1", "hello");
    }

    @RequestMapping("/add")
    public String addUser(@RequestParam User user) {
        System.out.println(JSON.toJSONString(user));
        return "successfully";
    }
}
