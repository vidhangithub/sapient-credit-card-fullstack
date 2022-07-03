package com.pubsap.creditcard.validation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class ValidNumberTest {
    private NumberValidator validator;

    @Before
    public void setup(){
        validator = new NumberValidator();
    }

    @Test
    public void test_null_value_should_fail(){
        assertFalse("Should not be be valid card", validator.isValid(null, null));
    }

    @Test
    public void test_alphanumeric_value_should_fail(){
        assertFalse("Should not be be valid card", validator.isValid("1234567890D58574", null));
    }

    @Test
    public void test_special_char_value_should_fail(){
        assertFalse("Should not be be valid card", validator.isValid("1234567890%58574", null));
    }

    @Test
    public void test_invalid_decimal_value_should_fail(){
        assertFalse("Should not be be valid card", validator.isValid("9856.12", null));
    }

    @Test
    public void test_valid_value_should_passr(){
        assertTrue("Should be valid card", validator.isValid("5224530869441886", null));
    }
}
