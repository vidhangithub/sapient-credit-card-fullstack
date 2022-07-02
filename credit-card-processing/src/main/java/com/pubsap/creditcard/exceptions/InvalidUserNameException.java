package com.pubsap.creditcard.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidUserNameException  extends  RuntimeException {
    private final int status;
    private final String errorMessage;

    public InvalidUserNameException(int status, String errorMessage) {
        super(errorMessage);
        this.status = status;
        this.errorMessage = errorMessage;
    }

}
