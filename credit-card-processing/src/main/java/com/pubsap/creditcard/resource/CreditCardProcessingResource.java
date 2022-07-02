package com.pubsap.creditcard.resource;

import com.pubsap.creditcard.api.CreditCardProcessingApi;
import com.pubsap.creditcard.models.CreditCard;
import com.pubsap.creditcard.service.CreditCardProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
@RestController
public class CreditCardProcessingResource implements CreditCardProcessingApi {
    @Autowired
    private CreditCardProcessingService service;

    @Override
    public ResponseEntity<CreditCard> createCreditCard(CreditCard card) {
        final CreditCard creditCard = service.createCreditCard(card);
        if (creditCard != null) {
            return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{/id}").buildAndExpand(creditCard.getId()).toUri())
                    .body(creditCard);
        } else return ResponseEntity.badRequest().build();
    }

    @Override
    public List<CreditCard> getAllCreditCards() {
        return service.getAllCreditCards();
    }
}
