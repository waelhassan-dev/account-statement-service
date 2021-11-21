package com.nagarro.account.statement.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String message;
    private String code;
    private List<ValidationError> details = new ArrayList<>();
    private String applicationMessage;

    public ErrorResponse() {
    }

    public ErrorResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public ErrorResponse(String message, String code, List<ValidationError> details, String applicationMessage) {
        this.message = message;
        this.code = code;
        this.details = details;
        this.applicationMessage = applicationMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<ValidationError> getDetails() {
        return details;
    }

    public void setDetails(List<ValidationError> details) {
        this.details = details;
    }

    public String getApplicationMessage() {
        return applicationMessage;
    }

    public void setApplicationMessage(String applicationMessage) {
        this.applicationMessage = applicationMessage;
    }
}
