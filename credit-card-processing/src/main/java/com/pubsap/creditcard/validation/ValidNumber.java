package com.pubsap.creditcard.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NumberValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public  @interface ValidNumber {
    String message() default "Value should be numeric";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    boolean decimal() default false;
}
