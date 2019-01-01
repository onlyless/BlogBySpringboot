package com.djangoz.blog.config;

import com.djangoz.blog.filter.AuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class Configurer {
    @Bean
    public FilterRegistrationBean AuthFilterRegistrationBean(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setName("authFilter");
        AuthFilter authFilter = new AuthFilter();
        registrationBean.setFilter(authFilter);
        registrationBean.setOrder(1);
        List<String> urlList = new ArrayList<>();
        urlList.add("/admin/*");
        registrationBean.setUrlPatterns(urlList);
        return registrationBean;
    }
}
