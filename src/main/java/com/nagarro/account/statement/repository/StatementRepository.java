package com.nagarro.account.statement.repository;

import com.nagarro.account.statement.dto.StatementRequest;
import com.nagarro.account.statement.model.Account;
import com.nagarro.account.statement.model.Statement;

import java.util.List;
import java.util.Optional;

public interface StatementRepository {

    Optional<Account> findAccountByAccountId(Long accountId);
    List<Statement> findStatementByAccountId(StatementRequest statementRequest);
    List<Statement> findStatementByAccountIdWithPagination(StatementRequest statementRequest);
}
