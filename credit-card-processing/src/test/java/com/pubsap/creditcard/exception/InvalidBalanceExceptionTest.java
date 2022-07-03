package com.pubsap.creditcard.exception;

import com.pubsap.creditcard.exceptions.InvalidBalanceException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InvalidBalanceExceptionTest {
    @Test
    public void testApiRunTimeExceptionTest() {
        InvalidBalanceException exception =
                new InvalidBalanceException(400,"InvalidBalanceExceptionTest");
        Assertions.assertTrue(
                (exception.getMessage()).equalsIgnoreCase("InvalidBalanceExceptionTest"));
    }
}
