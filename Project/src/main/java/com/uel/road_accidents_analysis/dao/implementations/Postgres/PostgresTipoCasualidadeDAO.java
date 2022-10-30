package com.uel.road_accidents_analysis.dao.implementations.Postgres;

import com.uel.road_accidents_analysis.dao.interfaces.custom.TipoCasualidadeDAO;
import com.uel.road_accidents_analysis.model.TipoCasualidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresTipoCasualidadeDAO implements TipoCasualidadeDAO {
    private final Connection connection;

    public PostgresTipoCasualidadeDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(TipoCasualidade tipoCasualidade) throws SQLException {
        String sql = "INSERT INTO tipo_casualidade (nome_casualidade) VALUES (?)";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setString(1, tipoCasualidade.getNome());
            prstate.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao inserir tipo de casualidade");
        }
    }

    @Override
    public void update(TipoCasualidade tipoCasualidade) throws SQLException {
        String sql = "UPDATE tipo_casualidade SET nome_casualidade = ? WHERE id_tipo_casualidade = ?";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setString(1, tipoCasualidade.getNome());
            prstate.setLong(2, tipoCasualidade.getId());
            prstate.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao atualizar tipo de casualidade");
        }
    }

    @Override
    public void delete(TipoCasualidade tipoCasualidade) throws SQLException {
        String sql = "DELETE FROM tipo_casualidade WHERE id = ?";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, tipoCasualidade.getId());
            prstate.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao deletar tipo de casualidade");
        }
    }

    @Override
    public TipoCasualidade getById(Long id) throws SQLException {
        TipoCasualidade tipoCasualidade = null;

        String sql = "SELECT * FROM tipo_casualidade WHERE id_tipo_casualidade = ?";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, id);

            ResultSet rs = prstate.executeQuery();

            if (rs.next()) {
                tipoCasualidade = new TipoCasualidade();
                tipoCasualidade.setId(rs.getLong("id_tipo_casualidade"));
                tipoCasualidade.setNome(rs.getString("nome_casualidade"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar tipo de casualidade");
        }

        return tipoCasualidade;
    }

    @Override
    public List<TipoCasualidade> getAll() throws SQLException {
        String sql = "SELECT * FROM tipo_casualidade";
        List<TipoCasualidade> tipoCasualidades = new ArrayList<>();

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            ResultSet result = prstate.executeQuery();

            while (result.next()) {
                TipoCasualidade tipoCasualidade = new TipoCasualidade();
                tipoCasualidade.setId(result.getLong("id_tipo_casualidade"));
                tipoCasualidade.setNome(result.getString("nome_casualidade"));
                tipoCasualidades.add(tipoCasualidade);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar todos os tipos de casualidade");
        }

        return tipoCasualidades;
    }

}