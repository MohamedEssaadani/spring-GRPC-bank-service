package org.essaadani.springgrpcservice;

import org.essaadani.springgrpcservice.entities.Account;
import org.essaadani.springgrpcservice.entities.Currency;
import org.essaadani.springgrpcservice.enums.AccountStatus;
import org.essaadani.springgrpcservice.enums.AccountType;
import org.essaadani.springgrpcservice.repository.AccountRepository;
import org.essaadani.springgrpcservice.repository.CurrencyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringGRpcServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGRpcServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CurrencyRepository currencyRepository, AccountRepository accountRepository){
        return args -> {
            // create currencies
            currencyRepository.save(new Currency(null, "USD", "$", 1));
            currencyRepository.save(Currency.builder()
                    .name("MAD")
                    .symbol("DH")
                    .price(0.1)
                    .build());
            currencyRepository.save(Currency.builder()
                    .name("EUR")
                    .symbol("EUR")
                    .price(0.98)
                    .build());

            // show all currencies
            currencyRepository.findAll()
                    .forEach(currency -> {
                        System.out.println(currency.toString());
                    });

            List<Currency> currencyList=currencyRepository.findAll();
            for (int i = 1; i <10 ; i++) {
                Account account= Account.builder()
                        .id("CC"+i)
                        .currency(currencyList.get(new Random().nextInt(currencyList.size())))
                        .createAt(System.currentTimeMillis())
                        .balance(Math.random()*9000000)
                        .type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
                        .status(AccountStatus.CREATED)
                        .build();
                accountRepository.save(account);
            }
        };
    }
}
