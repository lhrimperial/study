package com.github.study.sboot.vue.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 **/
@Component
public class CorsFilter implements Filter {

    @Value("${cors.origin}")
    private String origin;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        httpResponse.setHeader("Access-Control-Allow-Origin", origin);
        httpResponse.setHeader("Access-Control-Allow-Methods", httpRequest.getMethod());
        httpResponse.setHeader("Access-Control-Max-Age", "3600");
        httpResponse.setHeader("Access-Control-Allow-Headers", httpRequest.getHeader("Access-Control-Request-Headers"));
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpResponse.setStatus(HttpStatus.OK.value());
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}