package dev.damene.accountservice.repository;

import dev.damene.accountservice.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
