package com.nagarro.account.statement.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class DateFormatValidator implements ConstraintValidator<DateFormat, String> {

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    @Override
    public void initialize(DateFormat customDate) {
    }

    @Override
    public boolean isValid(String dateField, ConstraintValidatorContext cxt) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
        try
        {
            simpleDateFormat.setLenient(false);
            if (Objects.nonNull(dateField)) {
                simpleDateFormat.parse(dateField);
            }
            return true;
        }
        catch (ParseException e)
        {
            return false;
        }
    }
}
