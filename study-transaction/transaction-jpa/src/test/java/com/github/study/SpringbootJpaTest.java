package com.github.study;


import com.github.study.transaction.jpa.Application;
import com.github.study.transaction.jpa.dao.RoleRepository;
import com.github.study.transaction.jpa.dao.UserRepository;
import com.github.study.transaction.jpa.domain.Role;
import com.github.study.transaction.jpa.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class SpringbootJpaTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testRead(){

        User user = userRepository.findByUserId(43);
        System.out.println(user.getUserName());
        for (Role r:user.getRoles()){
            System.out.println(r.getRoleName());
        }
    }

    @Test
    public void test5() {
        User u1 = new User();
        u1.setUserName("admin1");

        User u2 = new User();
        u2.setUserName("wan1");

        Role r1 = new Role();
        r1.setRoleName("超级管理员");
        r1.setRoleId(31);

        Role r2 = new Role();
        r2.setRoleName("普通用户");
        r2.setRoleId(32);

        Set<Role> roleSet1 = new HashSet<Role>();
        roleSet1.add(r1);
        roleSet1.add(r2);

        u1.setRoles(roleSet1);


        Set<Role> roleSet2 = new HashSet<Role>();
        roleSet2.add(r2);

        u2.setRoles(roleSet2);

//        roleRepository.save(r1);
//        roleRepository.save(r2);
        userRepository.save(u1);
        userRepository.save(u2);
    }

    @Test
    public void testJoin() {
        User user1 =  new User();
        user1.setUserName("user1");
        user1.setPassword("12343");
        User user2 =  new User();
        user2.setUserName("user2");
        user2.setPassword("12343");

        Role role1 = new Role();
        role1.setRoleName("role1");
        Role role2 = new Role();
        role2.setRoleName("role2");

        user1.getRoles().add(role1);
        user1.getRoles().add(role2);
        user2.getRoles().add(role1);
        user2.getRoles().add(role2);


        roleRepository.save(role1);
        roleRepository.save(role2);
        userRepository.save(user1);
        userRepository.save(user2);

    }

    @Test
    public void test4() {
        Role role1 = new Role();
        role1.setRoleName("role1");
        Role role2 = new Role();
        role2.setRoleName("role2");
        roleRepository.save(role1);
        roleRepository.save(role2);
    }

    @Test
    public void testPageQuery() throws Exception {
        int page=1,size=10;
        Sort sort = new Sort(Sort.Direction.DESC, "userId");
        Pageable pageable = new PageRequest(page, size);
        Page<User> list =  userRepository.findAll(pageable);
        for (User user : list.getContent()) {
            System.out.println(user);
        }

        Page<User> page2 = userRepository.findByUserNameLike("andy%", pageable);
        System.out.println("totalCount=" + page2.getTotalPages());
        for (User user : page2.getContent()) {
            System.out.println(user);
        }
    }

    @Test
    public void test3() {
        List<User> list = userRepository.findByUserNameLike("andy%");
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void test2() {
        User user = userRepository.findByUserName("andy");
        System.out.println(user);
    }

    @Test
   public void test1() {
       User user = null;
       for (int i = 2; i < 15; i++) {
           user = new User();
           user.setUserName("andy"+i);
           user.setPassword("12343");
           userRepository.save(user);
       }
   }
}
