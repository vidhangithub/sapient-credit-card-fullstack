package com.pubsap.creditcard.models;

import com.pubsap.creditcard.validation.ValidCreditCard;
import com.pubsap.creditcard.validation.ValidNumber;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor
@Entity
@Table(name = "credit_card")
public class CreditCard {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Size(min = 12, max = 19, message = "Credit Card number length should fall between 12 to 19")
    @ValidNumber(message = "Provided credit card is not numeric")
    @ValidCreditCard(message = "Provided credit card number is not  luhn10 compliant")
    @Column(name="card_number", unique = true)
    private String cardNumber;

    @NotBlank(message = "Name can not be blank")
    @NotNull(message = "must not be null")
    @Column(name="name")
    private String name;

    @NotNull
    @Digits(integer = 9, fraction = 2)
    @Column(name="credit_limit")
    private BigDecimal creditLimit;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Column(name="balance")
    private BigDecimal balance = BigDecimal.valueOf(0.0);

    public CreditCard(String name, String cardNumber, BigDecimal creditLimit) {
        this(name, cardNumber, creditLimit, creditLimit);
    }

    public CreditCard(String name, String cardNumber, BigDecimal creditLimit, BigDecimal balance) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.creditLimit = creditLimit;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "id='" + id + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", name='" + name + '\'' +
                ", creditLimit=" + creditLimit +
                ", balance=" + balance +
                '}';
    }
}
