package com.pubsap.creditcard.exceptions;

public enum ErrorMessages {
    DUPLICATE_CREDIT_CARD ("Duplicate credit card found"),
    INVALID_CREDIT_CARD ("Invalid Card number provided please check"),
    INVALID_USER_NAME ("invalid User name provided please check"),
    INVALID_CREDITLIMIT ("Invalid Credit limit please check"),
    INVALID_BALANCE("Invalid Balance please check");
    private final String errorMsg;

    ErrorMessages(String errorMsg) {
        this.errorMsg=errorMsg;
    }
    public  String getErrorMsg() {return  errorMsg;}
}
