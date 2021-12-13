package com.nagarro.account.statement.service;

import com.nagarro.account.statement.config.AppSettings;
import com.nagarro.account.statement.dto.PaginationRequest;
import com.nagarro.account.statement.dto.StatementFilter;
import com.nagarro.account.statement.dto.StatementRequest;
import com.nagarro.account.statement.exception.NotFoundException;
import com.nagarro.account.statement.model.Account;
import com.nagarro.account.statement.model.Statement;
import com.nagarro.account.statement.repository.StatementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StatementServiceImpl implements StatementService {

    @Autowired
    private StatementRepository statementRepository;

    @Autowired
    private AppSettings appSettings;


    @Override
    public StatementRequest processRequest(StatementRequest request) {

        //TODO: should have a requirement of user have as set of account ids that can be allowed to access its details only

        StatementRequest response = request;

        setAccountDetails(request);
        setDefaultFilterConfiguration(request);
        List<Statement> statements = getStatement(request);
        statements = doAmountRangeFilter(request, statements);


        response.setStatements(statements);
        return response;
    }



    private void setAccountDetails(StatementRequest request) {
        request.setAccount(getAccountDetails(request));
    }

    private Account getAccountDetails(StatementRequest request) {
        return statementRepository.findAccountByAccountId(request.getAccountId())
                .orElseThrow(() -> new NotFoundException(String.format("no account found for with id %s", request.getAccountId())));
    }

    private void setDefaultFilterConfiguration(StatementRequest request) {

        if (request.getFilter() == null || Objects.isNull(request.getFilter())) {
            log.info("using default configuration - {} months back statement", appSettings.getDefaultBackStatementPeriodInMonths());
            request.setFilter(
                    StatementFilter.builder()
                            .fromDate(LocalDate.now().minusMonths(appSettings.getDefaultBackStatementPeriodInMonths()))
                            .toDate(LocalDate.now())
                            .build());
        } else if (request.getFilter().getFromDate() == null) {
            request.getFilter().setFromDate(LocalDate.now().minusMonths(appSettings.getDefaultBackStatementPeriodInMonths()));
        } else if (request.getFilter().getToDate() == null) {
            request.getFilter().setToDate(LocalDate.now());
        }
    }

    private List<Statement> getStatement(StatementRequest request) {
        if (appSettings.getPaginationEnabled())
            return getStatementWithPagination(request);
        return getStatementWithoutPagination(request);
    }

    private List<Statement> getStatementWithPagination(StatementRequest request) {

        if (request.getPaginationRequest() == null) {
            log.info("using default configuration - pagination params");
            PaginationRequest pagination = new PaginationRequest();
            pagination.setPageSize(appSettings.getDefaultPaginationPageSize());
            pagination.setPageIndex(appSettings.getDefaultPaginationPageIndex());
            request.setPaginationRequest(pagination);
        }

        //defense mechanism
        if (request.getPaginationRequest().getPageSize() > appSettings.getDefaultPaginationPageSize())
            request.getPaginationRequest().setPageSize(appSettings.getDefaultPaginationPageSize());

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


    private List<Statement> doAmountRangeFilter(StatementRequest request, List<Statement> statements) {
        return filterByAmountRange(request, statements);
    }


    private boolean isDateWithinRange(LocalDate recordDate, LocalDate startDate, LocalDate endDate) {
        return !(recordDate.isBefore(startDate) || recordDate.isAfter(endDate));
    }

    private boolean isAmountWithinRange(Double recordAmount, Double fromAmount, Double toAmount) {
        return recordAmount >= fromAmount && recordAmount <= toAmount;
    }


    private List<Statement> filterByAmountRange(StatementRequest request, List<Statement> statements) {
        return statements
                .stream()
                .filter(statement -> isAmountWithinRange(statement.getAmount(), request.getFilter().getFromAmount(), request.getFilter().getToAmount()))
                .collect(Collectors.toList());
    }
}