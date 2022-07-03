package com.pubsap.creditcard.validation;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
public class CreditCardValidatorTest {
    CreditCardValidator validator = null;

    @Before
    public void init() {
        validator = new CreditCardValidator();
    }

    @Test
    public void test_valid_card_luhn_compliant_and_16_digits() {

        boolean isValid = validator.isValid("5264274004108628", null);
        assertTrue("Should be valid card", isValid);
    }

    @Test
    public void test_invalid_card_luhn_non_compliant_and_16_digits() {

        boolean isValid = validator.isValid("1234567890147895", null);
        assertFalse("Should not be be valid card", isValid);
    }

    @Test
    public void test_valid_card_luhn_compliant_and_19_digits(){

        boolean isValid = validator.isValid("6011008785309789336", null);
        assertTrue("Should be valid card", isValid);
    }

    @Test
    public void test_invalid_card_luhn_non_compliant_and_19_digits(){

        boolean isValid = validator.isValid("1234567890124789563", null);
        assertFalse("Should not be be valid card", isValid);
    }

    @Test
    public void test_invalid_card_alphanumeric(){
        boolean isValid = validator.isValid("52642740041086Ab", null);
        assertFalse("Should not be be valid card", isValid);
    }

}

