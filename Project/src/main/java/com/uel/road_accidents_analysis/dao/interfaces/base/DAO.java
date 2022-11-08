package com.uel.road_accidents_analysis.dao.interfaces.base;

import java.sql.SQLException;
import java.util.List;

public interface DAO <T> {
    void insert(T t) throws SQLException;
    void update(T t) throws SQLException;
    void delete(T t) throws SQLException;
    T getById(Long id) throws SQLException;
    List<T> getAll() throws SQLException;
}
