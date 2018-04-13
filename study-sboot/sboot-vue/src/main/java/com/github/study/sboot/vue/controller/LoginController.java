package com.github.study.sboot.vue.controller;

import com.github.framework.server.shared.domain.dto.ResponseDTO;
import com.github.study.sboot.vue.domain.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *
 **/
@Controller
public class LoginController extends AbstractController{
    /**
     * 登录方法
     * @param userInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
    public ResponseDTO<String> ajaxLogin(@RequestBody UserInfo userInfo) {
        ResponseDTO<String> responseDTO = returnSuccess();
        String errorMsg = "";
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUsername(), userInfo.getPassword());
        try {
            subject.login(token);
            responseDTO.setResult((String) subject.getSession().getId());
            responseDTO.setResMsg("登录成功");
            return responseDTO;
        } catch (IncorrectCredentialsException e) {
            errorMsg = "密码错误";
        } catch (LockedAccountException e) {
            errorMsg = "登录失败，该用户已被冻结";
        } catch (AuthenticationException e) {
            errorMsg = "该用户不存在";
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseDTO = returnError(errorMsg);
        return responseDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/logout")
    public ResponseDTO<String> logout() {
        ResponseDTO responseDTO = returnSuccess();
        responseDTO.setResCode("1000000");
        responseDTO.setResMsg("注销成功");
        responseDTO.setResult("注销成功");
        return responseDTO;
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/unauth")
    public ResponseDTO<String> unauth() {
        ResponseDTO responseDTO = returnSuccess();
        responseDTO.setResCode("1000000");
        responseDTO.setResMsg("未登录");
        responseDTO.setResult("未登录");
        return responseDTO;
    }
}
