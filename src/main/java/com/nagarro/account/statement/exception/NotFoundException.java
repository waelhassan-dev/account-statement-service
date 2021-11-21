package com.nagarro.account.statement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseWebApplicationException {

    public NotFoundException(int httpStatus, String errorCode, String errorMessageKey) {
        super(httpStatus, errorCode, errorMessageKey);
    }

    public NotFoundException(int httpStatus, String errorCode, String errorMessageKey, String applicationMessage) {
        super(httpStatus, errorCode, errorMessageKey, applicationMessage);
    }

    public NotFoundException(int httpStatus, String errorCode, String errorMessageKey, String errorMessage, String applicationMessage, Object[] vars) {
        super(httpStatus, errorCode, errorMessageKey, errorMessage, applicationMessage, vars);
    }

    public NotFoundException(int httpStatus, String errorCode, String errorMessageKey, String errorMessage, String applicationMessage) {
        super(httpStatus, errorCode, errorMessageKey, errorMessage, applicationMessage);
    }
}
