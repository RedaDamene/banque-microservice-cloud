package dev.damene.accountservice.web;

import dev.damene.accountservice.clients.CustomerRestClient;
import dev.damene.accountservice.entities.BankAccount;
import dev.damene.accountservice.model.Customer;
import dev.damene.accountservice.repository.BankAccountRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountRestController {
    private final BankAccountRepository accountRepository;
    private final CustomerRestClient customerRestClient;

    public AccountRestController(BankAccountRepository bankAccountRepository, CustomerRestClient customerRestClient) {
        this.accountRepository = bankAccountRepository;
        this.customerRestClient = customerRestClient;
    }

    @GetMapping("/accounts")
    public List<BankAccount> getAccounts() {
        List<BankAccount> accounts = accountRepository.findAll();
        accounts.forEach(account -> {
            account.setCustomer(customerRestClient.finCustomerById(account.getCustomerId()));
        });
        return accounts;
    }

    @GetMapping("/accounts/{accountId}")
    public BankAccount getAccountById(@PathVariable String accountId) {
        BankAccount account = accountRepository.findById(accountId).get();
        Customer customer = customerRestClient.finCustomerById(account.getCustomerId());
        account.setCustomer(customer);
        return account;
    }
}
