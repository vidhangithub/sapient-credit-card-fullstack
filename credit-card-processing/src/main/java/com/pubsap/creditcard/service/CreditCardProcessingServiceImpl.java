package com.pubsap.creditcard.service;

import com.pubsap.creditcard.models.CreditCard;
import com.pubsap.creditcard.repository.CreditCardProcessingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardProcessingServiceImpl  implements  CreditCardProcessingService {

    @Autowired
    private CreditCardProcessingRepo repo;

    @Override
    public CreditCard createCreditCard(CreditCard creditCard) {
        return repo.save(creditCard);
    }

    @Override
    public List<CreditCard> getAllCreditCards() {
        return repo.findAll();
    }
}
