package com.nagarro.account.statement.controller;

import com.nagarro.account.statement.dto.StatementRequest;
import com.nagarro.account.statement.service.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import static com.nagarro.account.statement.mapper.ParamsMapper.accountIdToObject;
import static com.nagarro.account.statement.mapper.ParamsMapper.paramsToObject;

@RestController
@RequestMapping("api/v1/statements")
public class StatementController {

    @Autowired
    private StatementService statementService;


    @GetMapping(value = "/user", produces = "application/json")
    public @ResponseBody
    ResponseEntity<StatementRequest> getStatements(@RequestParam("accountId") String accountId) {
        return new ResponseEntity<>(statementService.processRequest(accountIdToObject(accountId)), HttpStatus.OK);
    }


    @GetMapping(value = "/admin", produces = "application/json")
    public @ResponseBody
    ResponseEntity<StatementRequest> getParameterizeStatements(@Valid @RequestParam("accountId") @NotBlank String accountId,
                                                               @RequestParam(value = "fromDate",required = false) @DateTimeFormat String fromDate,
                                                               @RequestParam(value = "toDate",required = false) @DateTimeFormat String toDate,
                                                               @RequestParam(value = "fromAmount",required = false) @Min(0) String fromAmount,
                                                               @RequestParam(value = "toAmount",required = false) @NumberFormat String toAmount,
                                                               @RequestParam(value = "size",required = false) @Min(1) String size,
                                                               @RequestParam(value = "index",required = false) @Min(1) String index
    ) {
        StatementRequest request = paramsToObject(accountId, fromDate, toDate, fromAmount, toAmount, size, index);
        return new ResponseEntity<>(statementService.processRequest(request), HttpStatus.OK);
    }


    @PostMapping(value = "/admin", consumes = "application/json", produces = "application/json")
    public @ResponseBody
    ResponseEntity<StatementRequest> getParameterizeStatementsPost(@RequestBody @Valid StatementRequest request) {
        return new ResponseEntity<>(statementService.processRequest(request), HttpStatus.OK);
    }
}
