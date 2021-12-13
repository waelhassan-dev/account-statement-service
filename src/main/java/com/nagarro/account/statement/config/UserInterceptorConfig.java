package com.nagarro.account.statement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Component
public class UserInterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private UserRequestInterceptor userRequestInterceptor ;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(userRequestInterceptor).addPathPatterns("/api/v1/statements");
    }
}
