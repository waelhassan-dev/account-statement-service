package com.nagarro.account.statement.exception;

import com.nagarro.account.statement.dto.ErrorResponse;


public abstract class BaseWebApplicationException extends RuntimeException {

    private final String defaultErrorMessageKey = "ex.default.system.error";
    private final int status;
    private final String errorCode;
    private final String errorMessageKey;
    private final String errorMessage;
    private final String applicationMessage;
    private final Object[] vars;

    public BaseWebApplicationException(int httpStatus, String errorCode, String errorMessageKey) {
        this(httpStatus, errorCode, errorMessageKey, null, null);
    }

    public BaseWebApplicationException(int httpStatus, String errorCode, String errorMessageKey, String applicationMessage) {
        this(httpStatus, errorCode, errorMessageKey, null, applicationMessage);
    }

    public BaseWebApplicationException(int httpStatus, String errorCode, String errorMessageKey, String errorMessage, String applicationMessage, Object[] vars) {
        super(errorMessageKey);
        this.status = httpStatus;
        this.errorMessageKey = errorMessageKey;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.applicationMessage = applicationMessage;
        this.vars = vars;
    }

    public BaseWebApplicationException(int httpStatus, String errorCode, String errorMessageKey, String errorMessage, String applicationMessage) {
        super(errorMessage);
        this.status = httpStatus;
        this.errorMessageKey = errorMessageKey;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.applicationMessage = applicationMessage;
        this.vars = null;
    }
    
    public ErrorResponse getErrorResponse() {
        ErrorResponse response = new ErrorResponse();
        response.setCode(errorCode);
        response.setApplicationMessage(applicationMessage);
        response.setMessage(errorMessage);
        return response;
    }

	public int getStatus() {
		return status;
	}

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessageKey() {
        return errorMessageKey;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getApplicationMessage() {
        return applicationMessage;
    }

    public String getDefaultErrorMessageKey() {
        return defaultErrorMessageKey;
    }

    public Object[] getVars() {
        return vars;
    }
}
