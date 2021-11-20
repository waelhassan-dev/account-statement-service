package com.nagarro.account.statement.service;

import com.nagarro.account.statement.dto.StatementRequest;
import org.springframework.validation.BindingResult;

public interface StatementService {

    StatementRequest processRequest(StatementRequest request);
}
