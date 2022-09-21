package org.essaadani.springgrpcservice.entities;

import lombok.*;

import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
@Data @ToString @AllArgsConstructor @NoArgsConstructor @Builder
public class Currency {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private String symbol;
    private double price;
}
