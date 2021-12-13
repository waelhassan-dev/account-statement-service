package com.nagarro.account.statement.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateFormatValidator.class)
@Target( { ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormat {

    String message() default "invalid date format, must be as (yyyy-MM-dd) format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
