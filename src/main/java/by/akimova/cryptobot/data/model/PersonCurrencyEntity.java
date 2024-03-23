package by.akimova.cryptobot.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "person_currency")
public class PersonCurrencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "person_id")
    private Long personId;
    @Column(name = "currency_id")
    private Long currencyId;
    @Column(name = "persantage")
    private Double persentage;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonEntity person;
    @ManyToOne
    @JoinColumn(name = "currency_id")
    private CurrencyEntity currency;


}
