package com.pubsap.creditcard.service;

import com.pubsap.creditcard.exceptions.ApiRunTimeException;
import com.pubsap.creditcard.models.CreditCard;
import com.pubsap.creditcard.repository.CreditCardProcessingRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardProcessingServiceImpl  implements  CreditCardProcessingService {

    private static final Logger LOG = LoggerFactory.getLogger(CreditCardProcessingServiceImpl.class);

    @Autowired
    private CreditCardProcessingRepo repo;

    @Override
    public CreditCard createCreditCard(CreditCard creditCard) {
        CreditCard card = null;
        LOG.info("Card creation service initiated");
        try {
                    card = repo.save(creditCard);
        } catch (Exception e) {
            LOG.error("Error encountered while creating card with {}" ,e.getMessage());
                throw new ApiRunTimeException(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getCause().toString());
        }
        LOG.info("Card creation service completed");
        return repo.save(creditCard);
    }

    @Override
    public List<CreditCard> getAllCreditCards() {
        return repo.findAll();
    }
}
