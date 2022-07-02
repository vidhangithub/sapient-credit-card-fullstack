package com.pubsap.creditcard.exceptions;

public enum ErrorMessages {
    DUPLICATE_CREDIT_CARD ("Duplicate credit card found"),
    INVALID_CREDIT_CARD("Credit card passed is Invalid");
    private final String errorMsg;

    ErrorMessages(String errorMsg) {
        this.errorMsg=errorMsg;
    }
    public  String getErrorMsg() {return  errorMsg;}
}
