package com.nagarro.account.statement.service;

import com.nagarro.account.statement.config.AppSettings;
import com.nagarro.account.statement.dto.PaginationRequest;
import com.nagarro.account.statement.dto.StatementFilter;
import com.nagarro.account.statement.dto.StatementRequest;
import com.nagarro.account.statement.repository.StatementJdbcTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class StatementServiceImplTest {

    @Mock
    private StatementJdbcTemplate mockStatementRepository;
    @Mock
    private AppSettings mockAppSettings;

    @InjectMocks
    private StatementServiceImpl statementServiceImplUnderTest;

    @Test
    void testProcessRequest() {
        // Setup
        final StatementRequest request = new StatementRequest(1L);
        final StatementRequest expectedResult = new StatementRequest(1L);

        StatementFilter filter = new StatementFilter();
        filter.setFromAmount(1d);
        filter.setToAmount(200d);
        request.setFilter(filter);

        PaginationRequest pagination = new PaginationRequest();
        pagination.setPageIndex(1);
        pagination.setPageSize(10);
        request.setPaginationRequest(pagination);

        //TODO Configure Conditions

        //TODO: Run the test

        //TODO: Verify the results
        assertThat(0).isZero();
    }

    @Test
    void testProcessRequest_StatementJdbcTemplateFindAccountByAccountIdReturnsAbsent() {
    }

    @Test
    void testProcessRequest_StatementJdbcTemplateFindStatementByAccountIdWithPaginationReturnsNoItems() {
    }

    @Test
    void testProcessRequest_StatementJdbcTemplateFindStatementByAccountIdReturnsNoItems() {
    }
}
