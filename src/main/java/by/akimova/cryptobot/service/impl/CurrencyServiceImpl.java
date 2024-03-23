package by.akimova.cryptobot.service.impl;

import by.akimova.cryptobot.data.model.CurrencyEntity;
import by.akimova.cryptobot.data.repository.CurrencyRepository;
import by.akimova.cryptobot.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public void add(CurrencyEntity currency) throws SQLException {
        currencyRepository.add(currency);

    }

    @Override
    public List<CurrencyEntity> getAll() throws SQLException {
        return currencyRepository.getAll();
    }

    @Override
    public CurrencyEntity getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public CurrencyEntity update(CurrencyEntity currency) throws SQLException {
        return null;
    }

    @Override
    public void remove(Long id) throws SQLException {

    }

    @Override
    public void chooseNotificationPersentage() {

    }

    @Override
    public CurrencyEntity getCurrentRate() {
        return null;
    }
}
