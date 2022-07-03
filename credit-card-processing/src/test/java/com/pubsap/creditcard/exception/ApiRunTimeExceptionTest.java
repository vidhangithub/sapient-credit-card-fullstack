package com.pubsap.creditcard.exception;

import com.pubsap.creditcard.exceptions.ApiRunTimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ApiRunTimeExceptionTest {

    @Test
    public void testApiRunTimeExceptionTest() {
        ApiRunTimeException exception =
                new ApiRunTimeException(500,"ApiRunTimeExceptionTest");
        Assertions.assertTrue(
                (exception.getMessage()).equalsIgnoreCase("ApiRunTimeExceptionTest"));
    }
}
