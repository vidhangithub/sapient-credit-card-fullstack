package com.pubsap.creditcard.exception;

import com.pubsap.creditcard.exceptions.InvalidUserNameException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InvalidUserNameExceptionTest {
    @Test
    public void testApiRunTimeExceptionTest() {
        InvalidUserNameException exception =
                new InvalidUserNameException(400,"InvalidUserNameExceptionTest");
        Assertions.assertTrue(
                (exception.getMessage()).equalsIgnoreCase("InvalidUserNameExceptionTest"));
    }
}
