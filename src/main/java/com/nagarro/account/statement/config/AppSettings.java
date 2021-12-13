package com.nagarro.account.statement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppSettings {

    @Autowired
    private Environment environment;

    public static final String DEFAULT_BACK_STATEMENT_PERIOD_IN_MONTHS = "config.default.back-statement.period";
    public static final String PAGINATION_ENABLED = "config.default.pagination.enabled";
    public static final String DEFAULT_PAGINATION_PAGE_SIZE = "config.default.pagination.page.size";
    public static final String DEFAULT_PAGINATION_PAGE_INDEX = "config.default.pagination.page.index";
    public static final String DEFAULT_HTTP_SESSION_TIMEOUT_IN_SECONDS = "config.default.http.session.timeout";
    public static final String DEFAULT_HTTP_MAXIMUM_CONCURRENT_SESSIONS = "config.default.http.session.concurrent.max";


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

    public int getDefaultHttpMaxConcurrentSessions() {
        return Integer.parseInt(environment.getRequiredProperty(DEFAULT_HTTP_MAXIMUM_CONCURRENT_SESSIONS));
    }
}
