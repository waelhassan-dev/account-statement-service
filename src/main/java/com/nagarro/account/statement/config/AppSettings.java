package com.nagarro.account.statement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppSettings {

    @Autowired
    private Environment environment;

    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";


    public static final String DEFAULT_BACK_STATEMENT_PERIOD_IN_MONTHS = "config.default.back-statement.period"; //3;
    public static final String PAGINATION_ENABLED = "config.default.pagination.enabled"; //false
    public static final String DEFAULT_PAGINATION_PAGE_SIZE = "config.default.pagination.page.size"; //30;
    public static final String DEFAULT_PAGINATION_PAGE_INDEX = "config.default.pagination.page.index"; // 0;
    public static final String DEFAULT_HTTP_SESSION_TIMEOUT_IN_SECONDS = "config.default.http.session.timeout"; // 300;


    public int getDefaultBackStatementPeriodInMonths() {
        return Integer.parseInt(environment.getRequiredProperty(DEFAULT_BACK_STATEMENT_PERIOD_IN_MONTHS));
    }

    public boolean getPaginationEnabled() {
        return Boolean.parseBoolean(environment.getRequiredProperty(PAGINATION_ENABLED));
    }

    public int getDefaultPaginationPageSize() {
        return Integer.parseInt(environment.getRequiredProperty(DEFAULT_PAGINATION_PAGE_SIZE));
    }

    public int getDefaultPaginationPageIndex() {
        return Integer.parseInt(environment.getRequiredProperty(DEFAULT_PAGINATION_PAGE_INDEX));
    }

    public int getDefaultHttpSessionTimeoutInSeconds() {
        return Integer.parseInt(environment.getRequiredProperty(DEFAULT_HTTP_SESSION_TIMEOUT_IN_SECONDS));
    }
}
