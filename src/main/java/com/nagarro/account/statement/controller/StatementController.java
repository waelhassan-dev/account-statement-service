package com.nagarro.account.statement.controller;

import com.nagarro.account.statement.dto.PaginationRequest;
import com.nagarro.account.statement.dto.StatementFilter;
import com.nagarro.account.statement.dto.StatementRequest;
import com.nagarro.account.statement.service.StatementService;
import com.nagarro.account.statement.validator.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;


@Validated
@RestController
@RequestMapping(value = "api/v1/statements", produces = {MediaType.APPLICATION_JSON_VALUE})
public class StatementController {

    @Autowired
    private StatementService statementService;

    @GetMapping()
    public @ResponseBody
    ResponseEntity<StatementRequest> getParameterizeStatements(@RequestParam("accountId")
                                                               @NotBlank(message = "account id must be provided") @Min(1) String accountId,
                                                               @RequestParam(value = "fromDate",required = false) @DateFormat String fromDate,
                                                               @RequestParam(value = "toDate",required = false) @DateFormat String toDate,
                                                               @RequestParam(value = "fromAmount",required = false) @Min(0) Double fromAmount,
                                                               @RequestParam(value = "toAmount",required = false) @Min(0) Double toAmount,
                                                               @RequestParam(value = "size",required = false) @Min(1) String size,
                                                               @RequestParam(value = "index",required = false) @Min(1) String index
    ) {

        return new ResponseEntity<>(statementService.processRequest(
                StatementRequest.builder()
                        .accountId(Long.parseLong(accountId))
                        .filter(
                                StatementFilter.builder()
                                        .fromDate(fromDate != null ? LocalDate.parse(fromDate) : null)
                                        .toDate(toDate != null ? LocalDate.parse(toDate) : null)
                                        .fromAmount(fromAmount)
                                        .toAmount(toAmount)
                                        .build()
                        )
                        .paginationRequest(
                                PaginationRequest.builder()
                                        .pageSize(size != null ? Integer.parseInt(size) : null)
                                        .pageIndex(index != null ? Integer.parseInt(index) : null)
                                        .build()
                        )
                        .build())
                , HttpStatus.OK);
    }

}
