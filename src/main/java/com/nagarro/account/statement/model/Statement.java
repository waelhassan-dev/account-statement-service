package com.nagarro.account.statement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Statement {

    private Long id;
    private Long accountId;
    private LocalDate date;
    private Double amount;

}
