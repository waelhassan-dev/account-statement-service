package com.nagarro.account.statement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Configuration
public class CustomHttpSessionListener implements HttpSessionListener {

    @Autowired
    private AppSettings appSettings;

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        event.getSession().setMaxInactiveInterval(appSettings.getDefaultHttpSessionTimeoutInSeconds());
    }
}

