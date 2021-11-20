package com.nagarro.account.statement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nagarro.account.statement.model.Account;
import com.nagarro.account.statement.model.Statement;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class StatementRequest {

    private StatementFilter filter;
    private PaginationRequest paginationRequest;
    private List<Statement> statements;
    private Account account;

    @JsonIgnore
    private Long accountId;

    public StatementRequest() {
    }

    public StatementRequest(Long accountId) {
        this.accountId = accountId;
    }

    public StatementRequest(StatementFilter filter, PaginationRequest paginationRequest, Long accountId) {
        this.filter = filter;
        this.paginationRequest = paginationRequest;
        this.accountId = accountId;
    }
}
