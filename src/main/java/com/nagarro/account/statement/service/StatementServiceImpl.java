package com.nagarro.account.statement.service;

import com.nagarro.account.statement.config.AppSettings;
import com.nagarro.account.statement.dto.PaginationRequest;
import com.nagarro.account.statement.dto.StatementFilter;
import com.nagarro.account.statement.dto.StatementRequest;
import com.nagarro.account.statement.exception.NotFoundException;
import com.nagarro.account.statement.model.Account;
import com.nagarro.account.statement.model.Statement;
import com.nagarro.account.statement.repository.StatementJdbcTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StatementServiceImpl implements StatementService {

    @Autowired
    private StatementJdbcTemplate statementRepository;

    @Autowired
    private AppSettings appSettings;


    @Override
    public StatementRequest processRequest(StatementRequest request) {
        log.trace("process request of " + request);
        //TODO: should have a requirement of user have as set of account ids that can allowed to access only

        StatementRequest response = request;
        List<Statement> statements;

        Optional<Account> accountByAccountId = Optional.ofNullable(statementRepository.findAccountByAccountId
                (request.getAccountId())
                .orElseThrow(() -> new NotFoundException(String.format("no account found with id %s", request.getAccountId()))));

        response.setAccount(accountByAccountId.get());

        if (request.getFilter() == null || request.getFilter().getFromDate() == null || request.getFilter().getToDate() == null) {
            log.info("using default configuration - 3 months back statement");
            System.err.println("\n filter:" + request.getFilter());

            StatementFilter filter = request.getFilter();

            if (request.getFilter().getFromDate() == null)
                filter.setFromDate(LocalDate.now().minusMonths(3));
            if (request.getFilter().getToDate() == null)
                filter.setToDate(LocalDate.now());

            request.setFilter(filter);

            System.err.println("filter:" + request.getFilter());

        }


        if (appSettings.getPaginationEnabled())
            statements = getStatementWithPagination(request);
        else
            statements = getStatementWithoutPagination(request);


        if (request.getFilter().getFromAmount() != null && request.getFilter().getToAmount() != null)
            statements = filterByAmountRange(request, statements);


        response.setStatements(statements);
        return response;
    }

    private boolean isDateWithinRange(LocalDate recordDate, LocalDate startDate, LocalDate endDate) {
        return !(recordDate.isBefore(startDate) || recordDate.isAfter(endDate));
    }

    private boolean isAmountWithinRange(Double recordAmount, Double fromAmount, Double toAmount) {
        return recordAmount >= fromAmount && recordAmount <= toAmount;
    }

    private List<Statement> getStatementWithPagination(StatementRequest request) {

        if (request.getPaginationRequest() == null) {
            log.info("using default configuration - 3 months back statement");
            PaginationRequest pagination = new PaginationRequest();
            pagination.setPageSize(appSettings.getDefaultPaginationPageSize());
            pagination.setPageIndex(appSettings.getDefaultPaginationPageIndex());
            request.setPaginationRequest(pagination);
        }

        return statementRepository.findStatementByAccountIdWithPagination(request)
                .stream()
                .filter(statement -> isDateWithinRange(statement.getDate(), request.getFilter().getFromDate(), request.getFilter().getToDate()))
                .collect(Collectors.toList());
    }

    private List<Statement> getStatementWithoutPagination(StatementRequest request) {

        return statementRepository.findStatementByAccountId(request)
                .stream()
                .filter(statement -> isDateWithinRange(statement.getDate(), request.getFilter().getFromDate(), request.getFilter().getToDate()))
                .collect(Collectors.toList());
    }

    private List<Statement> filterByAmountRange(StatementRequest request, List<Statement> statements) {
        return statements
                .stream()
                .filter(statement -> isAmountWithinRange(statement.getAmount(), request.getFilter().getFromAmount(), request.getFilter().getToAmount()))
                .collect(Collectors.toList());
    }
}