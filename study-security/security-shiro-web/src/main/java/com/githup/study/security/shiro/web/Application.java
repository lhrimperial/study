package com.githup.study.security.shiro.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 */
@MapperScan("com.githup.**.mapper")
@SpringBootApplication
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }
}
