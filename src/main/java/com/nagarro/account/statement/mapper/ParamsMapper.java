package com.nagarro.account.statement.mapper;

import com.nagarro.account.statement.dto.PaginationRequest;
import com.nagarro.account.statement.dto.StatementFilter;
import com.nagarro.account.statement.dto.StatementRequest;
import com.nagarro.account.statement.exception.ValidationException;

import java.time.LocalDate;

public class ParamsMapper {

    public static StatementRequest paramsToObject(String accountId, String fromDate, String toDate,
                                                  String fromAmount, String toAmount,
                                                  String size, String index) {
        StatementRequest request = new StatementRequest();
        StatementFilter filter = new StatementFilter();
        PaginationRequest pagination = new PaginationRequest();

        try {
            if (accountId != null)
                request.setAccountId(Long.valueOf(accountId));

            if (fromDate != null)
                filter.setFromDate(LocalDate.parse(fromDate));
            if (toDate != null)
                filter.setToDate(LocalDate.parse(toDate));
            if (fromAmount != null)
                filter.setFromAmount(Double.valueOf(fromAmount));
            if (toAmount != null)
                filter.setToAmount(Double.valueOf(toAmount));

            if (size != null)
                pagination.setPageSize(Integer.valueOf(size));
            if (index != null)
                pagination.setPageIndex(Integer.valueOf(index));

        } catch (Exception e) {
            throw new ValidationException();
        }

        request.setFilter(filter);
        request.setPaginationRequest(pagination);

        return request;
    }

    public static StatementRequest accountIdToObject(String accountId) {
        StatementRequest request = new StatementRequest();
        StatementFilter filter = new StatementFilter();
        PaginationRequest pagination = new PaginationRequest();

        try {
            if(accountId != null)
                request.setAccountId(Long.valueOf(accountId));
        } catch (Exception e) {
            throw new ValidationException();
        }


        request.setFilter(filter);
        request.setPaginationRequest(pagination);

        return request;
    }
}