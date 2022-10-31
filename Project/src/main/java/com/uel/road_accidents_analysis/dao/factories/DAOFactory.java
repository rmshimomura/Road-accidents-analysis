package com.uel.road_accidents_analysis.dao.factories;

import com.uel.road_accidents_analysis.dao.implementations.Postgres.PostgresRodoviaDAO;
import com.uel.road_accidents_analysis.dao.interfaces.custom.*;
import com.uel.road_accidents_analysis.jdbc.ConnectionFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOFactory implements AutoCloseable {

    protected Connection connection;

    @SuppressWarnings("unused")
    public static DAOFactory getInstance() throws ClassNotFoundException, SQLException, IOException {

        Connection connection = ConnectionFactory.getInstance().getConnection();
        DAOFactory daoFactory;

        if (ConnectionFactory.getdbServer().equals("postgresql")) {
            daoFactory = new PostgresDAOFactory(connection);
        } else {
            throw new RuntimeException("Servidor de banco de dados não suportado");
        }

        return daoFactory;

    }

    @SuppressWarnings("unused")
    public void commitTransaction() throws SQLException {
        try {
            connection.commit();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao realizar commit");
        }
    }

    @SuppressWarnings("unused")
    public void rollbackTransaction() throws SQLException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao realizar rollback");
        }
    }

    @SuppressWarnings("unused")
    public void endTransaction() throws SQLException {
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao finalizar transação");
        }
    }

    public void closeConnection() throws SQLException {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao fechar conexão");
        }
    }

    public abstract AcidenteComCasualidadeDAO getAcidenteComCasualidadeDAO();
    public abstract AcidenteSemCasualidadeDAO getAcidenteSemCasualidadeDAO();
    public abstract CasualidadeNoAcidenteDAO getCasualidadeNoAcidenteDAO();
    public abstract LogCargasDAO getLogCargasDAO();
    public abstract RodoviaDAO getRodoviaDAO();
    public abstract TipoCasualidadeDAO getTipoCasualidadeDAO();
    public abstract TrechoDAO getTrechoDAO();
    public abstract VeiculoAcidenteComCasualidadeDAO getVeiculoAcidenteComCasualidadeDAO();
    public abstract VeiculoAcidenteSemCasualidadeDAO getVeiculoAcidenteSemCasualidadeDAO();
    public abstract VeiculoDAO getVeiculoDAO();



    @Override
    public void close() throws Exception {
        closeConnection();
    }

}
