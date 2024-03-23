package by.akimova.cryptobot.service;

import by.akimova.cryptobot.data.model.PersonEntity;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public interface PersonService {

    void add(PersonEntity person) throws SQLException;

    List<PersonEntity> getAll() throws SQLException;

}
