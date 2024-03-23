package by.akimova.cryptobot.service.impl;

import by.akimova.cryptobot.data.model.PersonEntity;
import by.akimova.cryptobot.data.repository.PersonRepository;
import by.akimova.cryptobot.service.PersonService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public void add(PersonEntity person) throws SQLException {
        personRepository.add(person);

    }

    @Override
    public List<PersonEntity> getAll() throws SQLException {
        return null;
    }

}
