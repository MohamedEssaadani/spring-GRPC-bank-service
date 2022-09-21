package org.essaadani.springgrpcservice.repository;

import org.essaadani.springgrpcservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
