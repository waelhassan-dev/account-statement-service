package com.nagarro.account.statement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationRequest {

    private Integer pageIndex;
    private Integer pageSize;
    //TODO: sort by and sort direction
}
