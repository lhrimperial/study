package com.github.study;


import com.github.study.transaction.jpa.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

/**
 *
 */
public class JpaTest {
    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJPA");
    static EntityManager entityManager = factory.createEntityManager();

    public static void main(String[] args) {
        User person = new User(); //person为new状态
        person.setUserId(2);
        person.setUserName("zhang san");
        person.setPassword("123");
        entityManager.persist(person); //持久化实体

        add(person);

        System.out.println("user id:" + person.getUserId());
        User user = find(person.getUserId());
        System.out.println(user);

        List<User> all = findAll();
        for (User u : all) {
            System.out.println(u);
        }

        // close
        entityManager.close();
        entityManager.close();
    }

    // 添加用户的方法
    public static void add(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    // 查询用户的方法
    public static User find(Object id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    public static List<User> findAll() {
        List<User> users = entityManager.createQuery("select u from User u")
                .getResultList();
        return users;
    }
}
