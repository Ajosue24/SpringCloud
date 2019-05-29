package com.vytra.notification.config;

import com.vytra.notification.interceptor.AclInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new AclInterceptor())//
                .addPathPatterns("/**")//
                .excludePathPatterns("/login","/login2","/login3")
                .excludePathPatterns("/error");
    }
}
