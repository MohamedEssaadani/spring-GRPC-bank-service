package org.essaadani.springgrpcservice.repository;

import org.essaadani.springgrpcservice.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Currency findByName(String name);
}
