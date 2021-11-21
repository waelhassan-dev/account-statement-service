package com.nagarro.account.statement.aop;

import com.nagarro.account.statement.dto.ErrorResponse;
import com.nagarro.account.statement.dto.ValidationError;
import com.nagarro.account.statement.exception.ApplicationRuntimeException;
import com.nagarro.account.statement.exception.BaseWebApplicationException;
import com.nagarro.account.statement.exception.ValidationException;
import com.nagarro.account.statement.service.MessageResolverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler /*extends ResponseEntityExceptionHandler*/ {

    @Autowired
    private MessageResolverService messageResolverService;

    @ExceptionHandler(BaseWebApplicationException.class)
    public @ResponseBody
    ErrorResponse baseWebAppException(HttpServletResponse servletResponse, BaseWebApplicationException ex) {
        logException(ex);
        servletResponse.setStatus(ex.getStatus());
        ErrorResponse errorResponse = ex.getErrorResponse();
        errorResponse.setMessage(messageResolverService.resolveLocalizedErrorMessage(ex));
        log.debug("baseWebAppException setErrorMessage:"+errorResponse.getMessage());
        log.debug("baseWebAppException ex:"+ex);
        return errorResponse;
    }

    @ExceptionHandler(ValidationException.class)
    public @ResponseBody
    ErrorResponse baseWebAppValidationException(HttpServletResponse servletResponse, ValidationException ex) {
        logException(ex);
        servletResponse.setStatus(ex.getStatus());
        return ex.getErrorResponse();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, NumberFormatException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse ValidationError(HttpServletResponse servletResponse, MethodArgumentNotValidException ex) {
        logException(ex);
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        ApplicationRuntimeException exception = new ApplicationRuntimeException(
                HttpStatus.BAD_REQUEST.value(), "001", "ex.method.args.invalid", "Validation Error", "The data passed in the request was invalid. Please check and resubmit");
        ErrorResponse response = exception.getErrorResponse();
        response.setMessage(messageResolverService.resolveLocalizedErrorMessage(exception));
        response.setDetails(processFieldErrors(fieldErrors));
        servletResponse.setStatus(exception.getStatus());
        return response;
    }


    @ExceptionHandler({HttpRequestMethodNotSupportedException.class, HttpMediaTypeNotSupportedException.class})
    public @ResponseBody
    ErrorResponse httpMethod(HttpServletResponse servletResponse, Exception ex) {
        logException(ex);
        ApplicationRuntimeException exception = new ApplicationRuntimeException(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "002", "ex.http.request.notSupported", "Request Error", "Http Content-Type or Method Not Supported");
        ErrorResponse response = exception.getErrorResponse();
        response.setMessage(messageResolverService.resolveLocalizedErrorMessage(exception));
        servletResponse.setStatus(exception.getStatus());
        return response;

    }

    @ExceptionHandler(Throwable.class)
    public @ResponseBody
    ErrorResponse otherThrowable(HttpServletResponse servletResponse, Throwable e) {
        logException(e);
        ApplicationRuntimeException exception = new ApplicationRuntimeException(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "003", "ex.default.system.error", "System Error", "System Error");
        ErrorResponse response = exception.getErrorResponse();
        response.setMessage(messageResolverService.resolveLocalizedErrorMessage(exception));
        servletResponse.setStatus(exception.getStatus());
        return response;

    }

    private List<ValidationError> processFieldErrors(List<FieldError> fieldErrors) {
        List<ValidationError> errors = new ArrayList<ValidationError>();
        for (FieldError fieldError: fieldErrors) {
            String localizedErrorMessage = messageResolverService.resolveLocalizedErrorMessage(fieldError);
            ValidationError error = new ValidationError();
            error.setMessage(localizedErrorMessage);
            error.setPropertyName(fieldError.getField());
            error.setPropertyValue(fieldError.getRejectedValue() != null ? fieldError.getRejectedValue().toString() : null);
            errors.add(error);
        }

        return errors;
    }

    private void logException(Throwable ex) {
        log.error(ex.getLocalizedMessage(), ex);
    }
}
