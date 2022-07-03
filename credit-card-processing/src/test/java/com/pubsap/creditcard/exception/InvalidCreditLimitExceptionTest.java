package com.pubsap.creditcard.exception;

import com.pubsap.creditcard.exceptions.InvalidCreditLimitException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InvalidCreditLimitExceptionTest {

    @Test
    public void testApiRunTimeExceptionTest() {
        InvalidCreditLimitException exception =
                new InvalidCreditLimitException(500,"InvalidCreditLimitExceptionTest");
        Assertions.assertTrue(
                (exception.getMessage()).equalsIgnoreCase("InvalidCreditLimitExceptionTest"));
    }
}
