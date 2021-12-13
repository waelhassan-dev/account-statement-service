package com.nagarro.account.statement.service;

import com.nagarro.account.statement.config.AppSettings;
import com.nagarro.account.statement.dto.PaginationRequest;
import com.nagarro.account.statement.dto.StatementFilter;
import com.nagarro.account.statement.dto.StatementRequest;
import com.nagarro.account.statement.model.Account;
import com.nagarro.account.statement.model.Statement;
import com.nagarro.account.statement.repository.StatementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StatementServiceImplTest {

    @Mock
    private StatementRepository mockStatementRepository;
    @Mock
    private AppSettings mockAppSettings;

    @InjectMocks
    private StatementServiceImpl statementServiceImplUnderTest;

    //@Test
    void testProcessRequest() {
        //TODO: Setup

        //TODO: Configure Conditions

        //TODO: Run the test

        //TODO: Verify the results
    }

    @Test
    void testProcessRequest_StatementJdbcTemplateFindAccountByAccountIdReturnsAccountDetails() {

        when(mockStatementRepository.findAccountByAccountId(1L))
                .thenReturn(Optional.of(new Account(1L, "saving", "54001235")));

        StatementRequest request = new StatementRequest(1L);
        Account account = statementServiceImplUnderTest.processRequest(request).getAccount();

        // Verify the results
        assertThat(account.getId()).isEqualTo(1L);

    }

    @Test
    void testProcessRequest_StatementJdbcTemplateFindStatementByAccountId() {

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

        when(mockStatementRepository.findAccountByAccountId(1L))
                .thenReturn(Optional.of(new Account(1L, "saving", "54001235")));

        when(mockStatementRepository.findStatementByAccountId(request))
                .thenReturn(Arrays.asList(
                        new Statement(1L, 1L, LocalDate.now(), 1d),
                        new Statement(2L, 1L, LocalDate.now().minusDays(10), 5d),
                        new Statement(13L, 1L, LocalDate.now().minusDays(5), 130d)
                ));
        List<Statement> statements = statementServiceImplUnderTest.processRequest(request).getStatements();

        // Verify the results
        assertThat(statements.size()).isEqualTo(3L);
    }

}
