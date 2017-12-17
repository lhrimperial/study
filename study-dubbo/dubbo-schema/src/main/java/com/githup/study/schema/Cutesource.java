package com.githup.study.schema;

import com.githup.study.schema.domain.People;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author longhairen
 * @create 2017-12-11 9:04
 * @description
 **/
public class Cutesource {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:spring.xml");
        People p = (People)ctx.getBean("cutesource");
        System.out.println(p.getId());
        System.out.println(p.getName());
        System.out.println(p.getAge());
    }
}
