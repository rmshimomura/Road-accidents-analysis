package com.uel.road_accidents_analysis.dao.interfaces.base;

import java.util.List;

public interface DAO <T> {
    void insert(T t) throws Exception;
    void update(T t) throws Exception;
    void delete(T t) throws Exception;
    T getById(Long id) throws Exception;
    List<T> getAll() throws Exception;
}
