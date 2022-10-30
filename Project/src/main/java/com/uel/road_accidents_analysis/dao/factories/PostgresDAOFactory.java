package com.uel.road_accidents_analysis.dao.factories;

import com.uel.road_accidents_analysis.dao.implementations.Postgres.PostgresRodoviaDAO;

import java.sql.Connection;

public class PostgresDAOFactory extends DAOFactory {

    public PostgresDAOFactory(Connection connection) {
        this.connection = connection;
    }

    @Override
    public PostgresRodoviaDAO getRodoviaDAO() {
        return new PostgresRodoviaDAO(connection);
    }
    // TODO implementar getAcidenteDAO retornando um novo PostgresAcidenteDAO(this.connection) alem do resto dos metodos
}
