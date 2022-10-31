package com.uel.road_accidents_analysis.dao.factories;

import com.uel.road_accidents_analysis.dao.implementations.Postgres.*;
import com.uel.road_accidents_analysis.dao.interfaces.custom.*;

import java.sql.Connection;

public class PostgresDAOFactory extends DAOFactory {

    public PostgresDAOFactory(Connection connection) {
        this.connection = connection;
    }
    @Override
    public AcidenteComCasualidadeDAO getAcidenteComCasualidadeDAO() {
        return new PostgresAcidenteComCasualidadeDAO(connection);
    }
    @Override
    public AcidenteSemCasualidadeDAO getAcidenteSemCasualidadeDAO() {
        return new PostgresAcidenteSemCasualidadeDAO(connection);
    }
    @Override
    public CasualidadeNoAcidenteDAO getCasualidadeNoAcidenteDAO() {
        return new PostgresCasualidadeNoAcidenteDAO(connection);
    }
    @Override
    public LogCargasDAO getLogCargasDAO() {
        return new PostgresLogCargasDAO(connection);
    }
    @Override
    public RodoviaDAO getRodoviaDAO() {
        return new PostgresRodoviaDAO(connection);
    }
    @Override
    public TipoCasualidadeDAO getTipoCasualidadeDAO() {
        return new PostgresTipoCasualidadeDAO(connection);
    }
    @Override
    public TrechoDAO getTrechoDAO() {
        return new PostgresTrechoDAO(connection);
    }
    @Override
    public VeiculoAcidenteComCasualidadeDAO getVeiculoAcidenteComCasualidadeDAO() { return new PostgresVeiculoAcidenteComCasualidadeDAO(connection); }
    @Override
    public VeiculoAcidenteSemCasualidadeDAO getVeiculoAcidenteSemCasualidadeDAO() { return new PostgresVeiculoAcidenteSemCasualidadeDAO(connection); }
    @Override
    public VeiculoDAO getVeiculoDAO() {
        return new PostgresVeiculoDAO(connection);
    }


}
