package com.uel.road_accidents_analysis.dao.implementations.Postgres;

import com.uel.road_accidents_analysis.dao.interfaces.custom.RodoviaDAO;
import com.uel.road_accidents_analysis.model.Rodovia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresRodoviaDAO implements RodoviaDAO {
    private final Connection connection;

    public PostgresRodoviaDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(Rodovia rodovia) throws SQLException {
        String sql = "INSERT INTO rodovia (uf, nome_rodovia) VALUES (?, ?)";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setString(1, rodovia.getUF());
            prstate.setString(2, rodovia.getNome());

            prstate.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao inserir rodovia");
        }

    }

    public void update(Rodovia rodovia) throws SQLException {
        String sql = "UPDATE rodovia SET uf = ?, nome_rodovia = ? WHERE id_rodovia = ?";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setString(1, rodovia.getUF());
            prstate.setString(2, rodovia.getNome());
            prstate.setLong(3, rodovia.getId());

            prstate.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao atualizar rodovia");
        }
    }

    public void delete(Rodovia rodovia) throws SQLException {
        String sql = "DELETE FROM rodovia WHERE id_rodovia = ?";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, rodovia.getId());

            prstate.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao deletar rodovia");
        }
    }

    public Rodovia getById(Long id) throws SQLException {
        String sql = "SELECT * FROM rodovia WHERE id_rodovia = ?";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, id);

            try (ResultSet result = prstate.executeQuery()) {
                if (result.next()) {
                    Rodovia rodovia = new Rodovia();
                    rodovia.setId(result.getLong("id_rodovia"));
                    rodovia.setUF(result.getString("uf"));
                    rodovia.setNome(result.getString("nome_rodovia"));

                    return rodovia;
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                throw new SQLException("Erro ao buscar rodovia");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar rodovia");
        }

        return null;
    }

    public List<Rodovia> getAll() throws SQLException {

        List<Rodovia> rodovias = new ArrayList<Rodovia>();

        String sql = "SELECT * FROM rodovia";

        try {
            PreparedStatement prstate = connection.prepareStatement(sql);

            ResultSet result = prstate.executeQuery();

            while(result.next()){
                Rodovia rodovia = new Rodovia();
                rodovia.setId(Long.valueOf(result.getString("id_rodovia")));
                rodovia.setNome(result.getString("nome_rodovia"));
                rodovia.setUF(result.getString("UF"));

                rodovias.add(rodovia);
            }

            result.close();
            prstate.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar rodovias");
        }

        return rodovias;
    }

}
