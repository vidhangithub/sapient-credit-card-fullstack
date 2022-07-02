package com.pubsap.creditcard.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CreditCardValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public  @interface ValidCreditCard {
    String message() default "Credit Card number should be  luhn10 compliant";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    boolean decimal() default false;
}
