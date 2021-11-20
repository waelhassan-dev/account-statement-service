package com.nagarro.account.statement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationRequest {

    private Integer pageIndex;
    private Integer pageSize;
    //TODO: sortby and sort direction
}
