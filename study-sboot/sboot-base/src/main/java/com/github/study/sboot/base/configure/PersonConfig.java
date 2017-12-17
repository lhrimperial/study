package com.github.study.sboot.base.configure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author longhairen
 * @create 2017-12-12 19:26
 * @description
 **/
@Component
@ConfigurationProperties(prefix = "user")
public class PersonConfig {

    private @Value("${userName:lkl}") String name;
    private @Value("${age}") Integer         age;
    private @Value("${remark}") String       remark;
    private String                           address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
