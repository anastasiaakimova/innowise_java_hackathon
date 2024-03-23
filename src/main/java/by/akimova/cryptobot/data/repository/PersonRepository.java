package by.akimova.cryptobot.data.repository;

import by.akimova.cryptobot.data.model.PersonEntity;
import by.akimova.cryptobot.util.SessionUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonRepository implements GenericRepository<PersonEntity, Long> {
    @Override
    public void add(PersonEntity person) throws SQLException {
        SessionUtil sessionUtil = new SessionUtil();
        sessionUtil.openTransactionSession();
        Session session = sessionUtil.getSession();
        session.save(person);
        sessionUtil.closeTransactionSesstion();
    }

    @Override
    public List<PersonEntity> getAll() throws SQLException {
        return null;
    }

    @Override
    public PersonEntity getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public PersonEntity update(PersonEntity person) throws SQLException {
        return null;
    }

    @Override
    public void remove(Long aLong) throws SQLException {

    }
}
