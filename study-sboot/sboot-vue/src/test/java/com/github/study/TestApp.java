package com.github.study;

import com.alibaba.fastjson.JSON;
import com.github.study.sboot.vue.Application;
import com.github.study.sboot.vue.domain.UserInfo;
import com.github.study.sboot.vue.mapper.UserInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestApp {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void test() {
        UserInfo userInfo = userInfoMapper.findUserInfoByUserName("admin");
        System.out.println(JSON.toJSONString(userInfo));
    }
}
