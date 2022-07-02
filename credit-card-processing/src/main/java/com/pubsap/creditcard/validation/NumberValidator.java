package com.pubsap.creditcard.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberValidator  implements ConstraintValidator<ValidNumber, Object> {

    private ValidNumber validNumber;

    @Override
    public void initialize(ValidNumber constraintAnnotation) {
        this.validNumber = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }
        String regex = "-?[0-9]+";
        String data = String.valueOf(value);
        return data.matches(regex);
    }
}
