package com.pubsap.creditcard.exception;

import com.pubsap.creditcard.exceptions.InvalidCreditCardException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InvalidCreditCardExceptionTest {
    @Test
    public void testApiRunTimeExceptionTest() {
        InvalidCreditCardException exception =
                new InvalidCreditCardException(400,"InvalidCreditCardExceptionTest");
        Assertions.assertTrue(
                (exception.getMessage()).equalsIgnoreCase("InvalidCreditCardExceptionTest"));
    }
}
