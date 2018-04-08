package com.github.study.sboot.vue.controller;

import com.github.framework.server.shared.domain.dto.ResponseDTO;
import com.github.study.sboot.vue.domain.User;
import com.github.study.sboot.vue.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
public class UserController extends AbstractController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/user/save")
    public ResponseDTO<String> save(@RequestBody User user){
        ResponseDTO responseDTO = returnSuccess();
        try {
            userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO = returnError(e.getMessage());
        }
        return responseDTO;
    }

    @RequestMapping("/user/update")
    public ResponseDTO<String>  update(@RequestBody User user){
        ResponseDTO responseDTO = returnSuccess();
        try {
            userService.update(user);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO = returnError(e.getMessage());
        }
        return responseDTO;
    }

    @RequestMapping("/user/delete/{userId}")
    public ResponseDTO<String>  delete(@PathVariable String userId){
        ResponseDTO responseDTO = returnSuccess();
        try {
            userService.delete(userId);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO = returnError(e.getMessage());
        }
        return responseDTO;
    }

    @RequestMapping("/user/findById/{userId}")
    public ResponseDTO<User> findById(@PathVariable String userId){
        ResponseDTO responseDTO = returnSuccess();
        try {
            responseDTO.setResult(userService.findById(userId));
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO = returnError(e.getMessage());
        }
        return responseDTO;
    }

    @RequestMapping("/user/findUsers")
    public ResponseDTO<List<User>> findUsers(){
        ResponseDTO responseDTO = returnSuccess();
        try {
            responseDTO.setResult(userService.findUsers());
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO = returnError(e.getMessage());
        }
        return responseDTO;
    }
}
