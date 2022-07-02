package com.pubsap.creditcard.service;

import com.pubsap.creditcard.models.CreditCard;

import java.util.List;

public interface CreditCardProcessingService {
    CreditCard createCreditCard(CreditCard creditCard);
    List<CreditCard> getAllCreditCards();
}
