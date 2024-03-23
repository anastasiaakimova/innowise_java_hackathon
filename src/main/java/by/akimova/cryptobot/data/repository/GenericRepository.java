package by.akimova.cryptobot.data.repository;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface GenericRepository<T, ID> {

    void add(T t) throws SQLException;

    List<T> getAll() throws SQLException;

    T getById(ID id) throws SQLException;

    T update(T t) throws SQLException;

    void remove(ID id) throws SQLException;

}
