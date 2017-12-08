package com.githup.study;

import com.githup.study.jpa.dao.BaseDao;
import com.githup.study.jpa.entity.UserPO;
import com.githup.study.jpa.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:spring.xml"})
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void test1() {
        UserPO po = userService.find("8af083b1603004ab01603004acf30000");
        System.out.println(po);
    }

    @Test
    public void test() {
        UserPO po = new UserPO();
        po.setName("hello");
        po.setPwd("123");
        userService.save(po);
    }
}
