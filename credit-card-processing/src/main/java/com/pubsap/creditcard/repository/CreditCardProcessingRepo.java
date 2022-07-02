package com.pubsap.creditcard.repository;

import com.pubsap.creditcard.models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardProcessingRepo extends JpaRepository<CreditCard, String> { }
