package com.nagarro.account.statement.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nagarro.account.statement.model.Account;
import com.nagarro.account.statement.model.Statement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatementRequest {

    private StatementFilter filter;
    private PaginationRequest paginationRequest;
    private List<Statement> statements;
    private Account account;

    @JsonIgnore
    private Long accountId;


    public StatementRequest(Long accountId) {
        this.accountId = accountId;
    }

}
