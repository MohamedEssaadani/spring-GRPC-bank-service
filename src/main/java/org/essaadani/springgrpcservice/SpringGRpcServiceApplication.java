package org.essaadani.springgrpcservice;

import org.essaadani.springgrpcservice.entities.Currency;
import org.essaadani.springgrpcservice.repository.CurrencyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class SpringGRpcServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGRpcServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CurrencyRepository currencyRepository){
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

        };
    }
}
