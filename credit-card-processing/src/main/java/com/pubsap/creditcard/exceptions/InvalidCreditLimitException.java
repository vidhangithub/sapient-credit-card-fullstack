package com.pubsap.creditcard.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidCreditLimitException extends  RuntimeException {
    private final int status;
    private final String errorMessage;

    public InvalidCreditLimitException(int status, String errorMessage) {
        super(errorMessage);
        this.status = status;
        this.errorMessage = errorMessage;
    }

}
