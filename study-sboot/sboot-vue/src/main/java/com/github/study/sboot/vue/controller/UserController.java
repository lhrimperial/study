package com.github.study.sboot.vue.controller;

import com.github.study.sboot.vue.domain.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    @ResponseBody
    @RequestMapping("/user")
    public User getUser(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String url = request.getRequestURL().toString();
        System.out.println("url:"+url);
        System.out.println(id);
        return new User("1", "hello");
    }
}
