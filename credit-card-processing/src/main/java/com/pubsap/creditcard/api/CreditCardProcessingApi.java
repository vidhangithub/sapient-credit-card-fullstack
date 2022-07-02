package com.pubsap.creditcard.api;

import com.pubsap.creditcard.models.CreditCard;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/api/v1/cc-process")
public interface CreditCardProcessingApi {
    @PostMapping("credit-card")
    ResponseEntity<CreditCard> createCreditCard(@Valid  @RequestBody CreditCard card, BindingResult bindingResult);
    @GetMapping("credit-cards")
    List<CreditCard> getAllCreditCards();
}
