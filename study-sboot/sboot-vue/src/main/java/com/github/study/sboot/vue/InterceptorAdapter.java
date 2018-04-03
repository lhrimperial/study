package com.github.study.sboot.vue;

import com.github.study.sboot.vue.interceptor.ProcessInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 */
public class InterceptorAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //添加拦截器
        registry.addInterceptor(new ProcessInterceptor())
                .addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
