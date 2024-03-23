package by.akimova.cryptobot.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "currency")
public class CurrencyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "symbol")
    private String symbol;

    public CurrencyEntity(String symbol) {
        this.symbol = symbol;
    }

    public CurrencyEntity() {
    }
}
