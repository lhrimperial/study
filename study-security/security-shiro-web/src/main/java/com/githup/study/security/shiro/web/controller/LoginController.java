package com.githup.study.security.shiro.web.controller;

import com.githup.study.security.shiro.web.domain.vo.ResponseVO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class LoginController {

    public ResponseVO<String> login(@RequestParam String userName, @RequestParam String password) {
        ResponseVO<String> responseVO = new ResponseVO<>();

        return responseVO;
    }
}
