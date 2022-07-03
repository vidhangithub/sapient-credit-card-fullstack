package com.pubsap.creditcard.repo;

import com.pubsap.creditcard.models.CreditCard;
import com.pubsap.creditcard.repository.CreditCardProcessingRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CreditCardProcessingRepoTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired private CreditCardProcessingRepo creditCardProcessingRepo;

    @Before
    public void init() {
        creditCardProcessingRepo.deleteAll();
    }
    @Test
    public void test_get_all_credit_cards_when_repo_find_all() {
        CreditCard testCreditCard = new CreditCard();
        testCreditCard.setName("vidhan");
        testCreditCard.setCardNumber("5510263428177402");
        testCreditCard.setCreditLimit(BigDecimal.valueOf(200.0));
        testCreditCard.setBalance(BigDecimal.valueOf(0.0));

        CreditCard testCreditCard2 = new CreditCard();
        testCreditCard2.setName("chandra");
        testCreditCard2.setCardNumber("5510720093522600");
        testCreditCard2.setCreditLimit(BigDecimal.valueOf(1500.0));
        testCreditCard2.setBalance(BigDecimal.valueOf(100.0));

        entityManager.persist(testCreditCard);
        entityManager.persist(testCreditCard2);
        entityManager.flush();

        List<CreditCard> allCreditCards = creditCardProcessingRepo.findAll();
        assertThat(allCreditCards)
                .hasSize(2)
                .extracting(CreditCard::getName)
                .containsOnly(testCreditCard.getName(), testCreditCard2.getName());
    }
}
