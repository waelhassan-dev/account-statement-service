package com.nagarro.account.statement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatementFilter {

    private LocalDate fromDate;
    private LocalDate toDate;
    private Double fromAmount;
    private Double toAmount;
}
