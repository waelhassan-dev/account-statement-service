package com.nagarro.account.statement.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

@ExtendWith(MockitoExtension.class)
class StatementJdbcTemplateTest {

    @Mock
    private JdbcTemplate mockJdbcTemplate;

    @InjectMocks
    private StatementJdbcTemplate statementJdbcTemplateUnderTest;

    @Test
    void testFindAccountByAccountId() {
        //TODO
    }

    @Test
    void testFindAccountByAccountId_JdbcTemplateReturnsNoItems() {
        //TODO
    }

    @Test
    void testFindAccountByAccountId_JdbcTemplateThrowsDataAccessException() {
        //TODO
    }

    @Test
    void testFindStatementByAccountId() {
        //TODO
    }

    @Test
    void testFindStatementByAccountId_JdbcTemplateReturnsNoItems() {
        //TODO
    }

    @Test
    void testFindStatementByAccountId_JdbcTemplateThrowsDataAccessException() {
        //TODO
    }

    @Test
    void testFindStatementByAccountIdWithPagination() {
        //TODO
    }

    @Test
    void testFindStatementByAccountIdWithPagination_JdbcTemplateReturnsNoItems() {
        //TODO
    }

    @Test
    void testFindStatementByAccountIdWithPagination_JdbcTemplateThrowsDataAccessException() {
        //TODO
    }
}
