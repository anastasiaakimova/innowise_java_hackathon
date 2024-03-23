package by.akimova.cryptobot.data.repository;

import by.akimova.cryptobot.data.model.CurrencyEntity;
import by.akimova.cryptobot.util.SessionUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;


@Repository
public class CurrencyRepository implements GenericRepository <CurrencyEntity, Long>{
    @Override
    public void add(CurrencyEntity currency) throws SQLException {
        SessionUtil sessionUtil = new SessionUtil();
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        session.save(currency);
        sessionUtil.closeTransactionSesstion();

    }

    @Override
    public List<CurrencyEntity> getAll() throws SQLException {
        SessionUtil sessionUtil = new SessionUtil();
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        String hql = "FROM CurrencyEntity";
        List<CurrencyEntity> currencyEntityList = session.createQuery(hql, CurrencyEntity.class).list();
        sessionUtil.closeTransactionSesstion();
        return currencyEntityList;
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
}
