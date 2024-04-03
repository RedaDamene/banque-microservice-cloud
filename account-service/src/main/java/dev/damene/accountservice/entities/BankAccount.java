package dev.damene.accountservice.entities;

import dev.damene.accountservice.enums.AccountType;
import dev.damene.accountservice.model.Customer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter @Setter
@ToString @Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
    @Id
    private String accountId;
    private Double balance;
    private LocalDate createdAt;
    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    @Transient
    private Customer customer;
    private Long customerId;
}
