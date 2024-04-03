package dev.damene.accountservice;

import dev.damene.accountservice.clients.CustomerRestClient;
import dev.damene.accountservice.entities.BankAccount;
import dev.damene.accountservice.enums.AccountType;
import dev.damene.accountservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        return args -> {
            customerRestClient.allCustomers().forEach(c->{
                BankAccount account1 = BankAccount.builder()
                        .accountId(UUID.randomUUID().toString())
                        .currency("EUR")
                        .balance(Math.random()*2300.0)
                        .createdAt(LocalDate.now())
                        .type(AccountType.CURRENT_ACCOUNT)
                        .customerId(c.getId())
                        .build();

                BankAccount account2 = BankAccount.builder()
                        .accountId(UUID.randomUUID().toString())
                        .currency("EUR")
                        .balance(Math.random()*5321.0)
                        .createdAt(LocalDate.now())
                        .type(AccountType.SAVINGS_ACCOUNT)
                        .customerId(c.getId())
                        .build();
                bankAccountRepository.save(account1);
                bankAccountRepository.save(account2);
            });
        };
    }
}
