package com.nagarro.account.statement.service;

import com.nagarro.account.statement.exception.BaseWebApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import java.util.Locale;

@Service
public class MessageResolverService {

    @Autowired
    private MessageSource messageSource;

    public String resolveLocalizedErrorMessage(BaseWebApplicationException ex) {
        Locale currentLocale =  LocaleContextHolder.getLocale();
        return resolveLocalizedErrorMessage(ex, currentLocale);
    }

    public String resolveLocalizedErrorMessage(BaseWebApplicationException ex, Locale locale) {
        String localizedErrorMessage = null;
        try {
            localizedErrorMessage = messageSource.getMessage(ex.getErrorMessageKey(), ex.getVars(), locale);
        } catch (NoSuchMessageException e1) {
            try {
                localizedErrorMessage = messageSource.getMessage(ex.getDefaultErrorMessageKey(), null, locale);
            } catch (NoSuchMessageException e2) {

            }
        }

        return localizedErrorMessage;
    }

    public String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale =  LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);

        return localizedErrorMessage;
    }
}
