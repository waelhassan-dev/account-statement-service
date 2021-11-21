package com.nagarro.account.statement.exception;

public class ApplicationRuntimeException extends BaseWebApplicationException {

    public ApplicationRuntimeException(int httpStatus, String errorCode, String errorMessageKey, String errorMessage, String applicationMessage) {
        super(httpStatus, errorCode, errorMessageKey, errorMessage, applicationMessage);
    }

    public ApplicationRuntimeException(String applicationMessage) {
        super(500, "001", "ex.internal.system.error", "Internal System Error", applicationMessage);
    }
}
