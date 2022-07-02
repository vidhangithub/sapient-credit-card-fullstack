package com.pubsap.creditcard.validation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class CreditCardValidator implements ConstraintValidator<ValidCreditCard, Object> {

    private static final Logger LOG = LoggerFactory.getLogger(CreditCardValidator.class);

    private ValidCreditCard validCreditCard;

    @Override
    public void initialize(ValidCreditCard constraintAnnotation) {
       this.validCreditCard=constraintAnnotation;
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        String cardNumber = String.valueOf(value);
        int[] cardIntArr = new int[cardNumber.length()];
        for(int i = 0; i < cardNumber.length(); i++){
            try{
                cardIntArr[i] = Integer.parseInt(String.valueOf(cardNumber.charAt(i)));
            }catch (NumberFormatException ex){
                LOG.error("NumberFormatException while parsing card number {}", ex.getMessage());
                return false;
            }
        }
        for(int i = cardIntArr.length -2; i >= 0; i=i-2){
            int num = cardIntArr[i];
            num = num*2;
            if(num > 9){
                num = num%10 + num/10;
            }
            cardIntArr[i] = num;
        }
        int sum = Arrays.stream(cardIntArr).sum();
        return sum % 10 == 0;
    }
}
