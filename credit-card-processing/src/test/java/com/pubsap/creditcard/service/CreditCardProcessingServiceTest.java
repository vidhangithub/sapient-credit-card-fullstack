package com.pubsap.creditcard.service;

import com.pubsap.creditcard.models.CreditCard;
import com.pubsap.creditcard.repository.CreditCardProcessingRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class CreditCardProcessingServiceTest {

    @Mock
    CreditCardProcessingRepo creditCardProcessingRepo;

    @InjectMocks
    CreditCardProcessingServiceImpl creditCardProcessingService;

    private final List<CreditCard> creditCardsList = new ArrayList<>();
    private final CreditCard card1 = new CreditCard();
    private final CreditCard card2 = new CreditCard();

    @Before
    public void setUp() {
        creditCardProcessingRepo.deleteAll();
        card1.setName("vidhan");
        card1.setCardNumber("5256972233310721");
        card1.setCreditLimit(BigDecimal.valueOf(200.0));

        card2.setName("chandra");
        card2.setCardNumber("5395824499047830");
        card2.setCreditLimit(BigDecimal.valueOf(500));
        card2.setBalance(BigDecimal.valueOf(100));

        creditCardsList.add(card1);
        creditCardsList.add(card2);

        when(creditCardProcessingRepo.findAll()).thenReturn(creditCardsList);
        when(creditCardProcessingRepo.save(any(CreditCard.class))).thenReturn(card2);
    }

    @Test
    public void test_createCreditCard() {
        final CreditCard actualRecord = creditCardProcessingService.createCreditCard(new CreditCard());
        assertNotNull(actualRecord);
        Assert.assertEquals("5395824499047830", actualRecord.getCardNumber());
        Assert.assertEquals("chandra", actualRecord.getName());
    }

    @Test
    public void test_getAllCreditCards() {
        final List<CreditCard> creditCards = creditCardProcessingService.getAllCreditCards();
        assertNotNull(creditCards);
        assertTrue(creditCards.size() > 0);
        assertTrue(creditCards.get(0).getName().equalsIgnoreCase("vidhan"));
    }
}

