package com.nagarro.account.statement.repository;

import com.nagarro.account.statement.dto.StatementRequest;
import com.nagarro.account.statement.exception.NotFoundException;
import com.nagarro.account.statement.mapper.AccountRowMapper;
import com.nagarro.account.statement.mapper.StatementRowMapper;
import com.nagarro.account.statement.model.Account;
import com.nagarro.account.statement.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class StatementJdbcTemplate {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Optional<Account> findAccountByAccountId(Long accountId) {
        String sql = "SELECT * FROM account WHERE ID = " + accountId + ";";
        try {
            return Optional
                    .of(jdbcTemplate.query(sql, new AccountRowMapper()).get(0));
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "404", String.format("no account found for with id %s", accountId));
        }
    }

    public List<Statement> findStatementByAccountId(StatementRequest statementRequest) {
        String sql = "SELECT * FROM statement WHERE account_id = " + statementRequest.getAccountId() + ";";
        try {
            return Optional.of(jdbcTemplate.query(sql, new StatementRowMapper())).get();
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "404", String.format("no account found for with id %s", statementRequest.getAccountId()));
        }
    }

    public List<Statement> findStatementByAccountIdWithPagination(StatementRequest statementRequest) {

        //TODO: adding sorting to pagination
        Sort.Order order = Sort.Order.by("ID");
        Integer size = statementRequest.getPaginationRequest().getPageSize();
        Integer index = statementRequest.getPaginationRequest().getPageIndex();
        int offset = index > 0 ? index -1 : 0;

        String sql = "SELECT * FROM statement WHERE account_id = "
                + statementRequest.getAccountId() + " ORDER BY " + order.getProperty() + " "
                + order.getDirection().name() + " LIMIT " + size + " OFFSET " + offset + ";";

    try {
            return Optional.ofNullable(jdbcTemplate.query(sql, new StatementRowMapper())).get();
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(HttpStatus.NOT_FOUND.value(), "404", String.format("no account found for with id %s", statementRequest.getAccountId()));
        }
    }

}
