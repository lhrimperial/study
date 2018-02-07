package com.githup.study;

import com.githup.study.security.shiro.web.Application;
import com.githup.study.security.shiro.web.domain.UserPO;
import com.githup.study.security.shiro.web.mapper.UserMapper;
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
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1() {
        UserPO userPO = new UserPO();
        userPO.setUserId(275688);
        userPO.setUserName("longhairne");
        userPO.setPassword("12345");
        userMapper.save(userPO);

        UserPO po = userMapper.findById(275688);
        System.out.println(po);

        po.setUserName("hairen");
        po.setEmail("lhr@163.com");
        userMapper.update(po);

        po = userMapper.findById(275688);
        System.out.println(po);


    }

}
