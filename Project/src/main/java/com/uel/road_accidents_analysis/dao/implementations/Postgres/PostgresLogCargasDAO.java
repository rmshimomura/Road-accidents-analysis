package com.uel.road_accidents_analysis.dao.implementations.Postgres;

import com.uel.road_accidents_analysis.dao.interfaces.custom.LogCargasDAO;
import com.uel.road_accidents_analysis.model.LogCargas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresLogCargasDAO implements LogCargasDAO {

    private final Connection connection;

    public PostgresLogCargasDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(LogCargas logCargas) throws SQLException {

        String sql = "INSERT INTO log_cargas (nome_arquivo, tipo_arquivo, tuplas_carregadas) VALUES (?, ?, ?)";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setString(1, logCargas.getNomeArquivo());
            prstate.setString(2, logCargas.getTipoArquivo());
            prstate.setLong(3, logCargas.getTuplasCarregadas());

            prstate.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao inserir log de carga");
        }

    }

    @Override
    public void update(LogCargas logCargas) {
        // Not available
    }

    @Override
    public void delete(LogCargas logCargas) {
        // Not available
    }

    @Override
    public LogCargas getById(Long id) {
        // Not available
        return null;
    }

    @Override
    public List<LogCargas> getAll() throws SQLException {

        String sql = "SELECT * FROM log_cargas";
        List<LogCargas> logCargasList = new ArrayList<>();

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            ResultSet rs = prstate.executeQuery();

            while (rs.next()) {
                LogCargas logCargas = new LogCargas();
                logCargas.setId(rs.getLong("id"));
                logCargas.setNomeArquivo(rs.getString("nome_arquivo"));
                logCargas.setTipoArquivo(rs.getString("tipo_arquivo"));
                logCargas.setTuplasCarregadas(rs.getInt("tuplas_carregadas"));
                logCargas.setHorarioCarga(rs.getTimestamp("horario_carga"));
                logCargasList.add(logCargas);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar todos os logs de carga");
        }

        return logCargasList;

    }

}
