package com.pubsap.creditcard.util;

import com.pubsap.creditcard.models.CreditCard;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class TestUtil {
    private static TestUtil testUtil;
    private TestUtil(){}
    static {
        testUtil = new TestUtil();
    }
    public static TestUtil getTestUtil(){ return  testUtil;}

    public Map<String, CreditCard> getTestData() {
        Map<String, CreditCard> data = new HashMap<>();
        CreditCard validCard1 = new CreditCard("Vidhan Chandra", "5256972233310721", BigDecimal.valueOf(25000.0));
        data.put("validCard1", validCard1);
        CreditCard validCard2 = new CreditCard("Giri Chandra", "5242155659228802", BigDecimal.valueOf(95000.0));
        data.put("validCard2", validCard2);
        CreditCard invalidCardLuhnFailed = new CreditCard("Rita Chandra", "1234567890124578",BigDecimal.valueOf(20000.0));
        data.put("invalidCardLuhnFailed", invalidCardLuhnFailed);
        CreditCard invalidCardNumberFormat = new CreditCard("Lily Chandra", "522867565789110F",BigDecimal.valueOf(15000.0));
        data.put("invalidCardNumberFormat", invalidCardNumberFormat);
        CreditCard blankUserNameCard = new CreditCard("", "5192814696018473",BigDecimal.valueOf(5000.0));
        data.put("blankUserNameCard", blankUserNameCard);
        return data;
    }
}
