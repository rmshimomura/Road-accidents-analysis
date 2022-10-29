package com.uel.road_accidents_analysis.dao;

import java.sql.Connection;

public class PostgresDAOFactory extends DAOFactory {

    public PostgresDAOFactory(Connection connection) {
        this.connection = connection;
    }

    // TODO implementar getAcidenteDAO retornando um novo PostgresAcidenteDAO(this.connection) alem do resto dos metodos
}
