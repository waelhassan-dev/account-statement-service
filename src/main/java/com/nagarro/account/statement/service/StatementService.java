package com.nagarro.account.statement.service;

import com.nagarro.account.statement.dto.StatementRequest;

public interface StatementService {

    StatementRequest processRequest(StatementRequest request);
}
