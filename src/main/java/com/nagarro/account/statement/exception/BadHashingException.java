package com.nagarro.account.statement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class BadHashingException extends RuntimeException {

    public BadHashingException() {
        super();
    }

    public BadHashingException(String message) {
        super(message);
    }
}
