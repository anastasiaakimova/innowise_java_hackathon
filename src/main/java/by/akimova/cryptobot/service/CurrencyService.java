package by.akimova.cryptobot.service;

import by.akimova.cryptobot.data.model.CurrencyEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface CurrencyService {

    void add(CurrencyEntity currency) throws SQLException;

    List<CurrencyEntity> getAll() throws SQLException;

    CurrencyEntity getById(Long id) throws SQLException;

    CurrencyEntity update(CurrencyEntity currency) throws SQLException;
    void remove(Long id) throws SQLException;
    void chooseNotificationPersentage();
    CurrencyEntity getCurrentRate();

}
