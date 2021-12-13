package com.nagarro.account.statement.repository;

import com.nagarro.account.statement.dto.PaginationRequest;
import com.nagarro.account.statement.dto.StatementFilter;
import com.nagarro.account.statement.dto.StatementRequest;
import com.nagarro.account.statement.model.Statement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJdbcTest
class StatementJdbcRepositoryTest {

    @Mock
    private JdbcTemplate mockJdbcTemplate;

    @InjectMocks
    private StatementRepository statementJdbcRepositoryUnderTest = new StatementJdbcRepository();


    //TODO: populate mock data in order to fetch from - using in memory database

    //@Test
    void testFindAccountByAccountId() {
        //TODO
    }

    @Test
    void testFindStatementByAccountId() {

        StatementRequest request = new StatementRequest(1L);
        StatementFilter filter = new StatementFilter();
        filter.setFromAmount(1d);
        filter.setToAmount(200d);
        filter.setFromDate(LocalDate.now().minusDays(10));
        filter.setToDate(LocalDate.now());
        request.setFilter(filter);

        List<Statement> statementByAccountId = statementJdbcRepositoryUnderTest.findStatementByAccountId(request);

        //due no data populated so empty list will be returned
        assertThat(statementByAccountId).isEqualTo(Collections.EMPTY_LIST);

    }


    @Test
    void testFindStatementByAccountIdWithPagination() {
        StatementRequest request = new StatementRequest(1L);

        StatementFilter filter = new StatementFilter();
        filter.setFromAmount(1d);
        filter.setToAmount(200d);
        filter.setFromDate(LocalDate.now().minusDays(10));
        filter.setToDate(LocalDate.now());
        request.setFilter(filter);

        PaginationRequest pagination = new PaginationRequest();
        pagination.setPageIndex(1);
        pagination.setPageSize(10);
        request.setPaginationRequest(pagination);

        List<Statement> statementByAccountId = statementJdbcRepositoryUnderTest.findStatementByAccountIdWithPagination(request);

        //due no data populated so empty list will be returned
        assertThat(statementByAccountId).isEqualTo(Collections.EMPTY_LIST);
    }

}
