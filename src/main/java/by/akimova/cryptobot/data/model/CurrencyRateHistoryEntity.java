package by.akimova.cryptobot.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "currency_rate_history")
public class CurrencyRateHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "currency_id")
    private Long currencyId;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "last_pdate")
    private LocalDateTime lastUpdate;
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private CurrencyEntity currency;
}
