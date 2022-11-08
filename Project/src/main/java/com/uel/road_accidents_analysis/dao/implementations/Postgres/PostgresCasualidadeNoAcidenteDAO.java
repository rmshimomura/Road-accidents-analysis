package com.uel.road_accidents_analysis.dao.implementations.Postgres;

import com.uel.road_accidents_analysis.dao.interfaces.custom.CasualidadeNoAcidenteDAO;
import com.uel.road_accidents_analysis.model.CasualidadeNoAcidente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresCasualidadeNoAcidenteDAO implements CasualidadeNoAcidenteDAO {

    private final Connection connection;

    public PostgresCasualidadeNoAcidenteDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(CasualidadeNoAcidente casualidadeNoAcidente) throws SQLException {

        String sql = "INSERT INTO casualidade_acidente (id_acidente_cc, id_tipo_casualidade, quantidade) VALUES (?, ?, ?)";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, casualidadeNoAcidente.getIdAcidente());
            prstate.setLong(2, casualidadeNoAcidente.getIdTipoCasualidade());
            prstate.setInt(3, casualidadeNoAcidente.getQuantidade());

            prstate.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao inserir casualidade no acidente");
        }

    }

    @Override
    public void update(CasualidadeNoAcidente casualidadeNoAcidente) throws SQLException {
        String sql = "UPDATE casualidade_acidente SET id_acidente_cc = ?, id_tipo_casualidade = ?, quantidade = ? WHERE (id_acidente_cc = ? and id_tipo_casualidade = ?)";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, casualidadeNoAcidente.getIdAcidente());
            prstate.setLong(2, casualidadeNoAcidente.getIdTipoCasualidade());
            prstate.setInt(3, casualidadeNoAcidente.getQuantidade());
            prstate.setLong(4, casualidadeNoAcidente.getIdAcidente());
            prstate.setLong(5, casualidadeNoAcidente.getIdTipoCasualidade());

            prstate.execute();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao atualizar casualidade no acidente");
        }

    }

    @Override
    public void delete(CasualidadeNoAcidente casualidadeNoAcidente) throws SQLException {
        String sql = "DELETE FROM casualidade_acidente WHERE (id_acidente_cc = ? and id_tipo_casualidade = ?)";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, casualidadeNoAcidente.getIdAcidente());
            prstate.setLong(2, casualidadeNoAcidente.getIdTipoCasualidade());

            prstate.execute();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao deletar casualidade no acidente");
        }
    }

    @Override
    public CasualidadeNoAcidente getById(Long id) throws SQLException {
        return null;
    }

    @Override
    public CasualidadeNoAcidente getByDoubleId(Long id1, Long id2) throws SQLException {

        String sql = "SELECT * FROM casualidade_acidente WHERE (id_acidente_cc = ? and id_tipo_casualidade = ?)";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, id1);
            prstate.setLong(2, id2);

            ResultSet rs = prstate.executeQuery();
            CasualidadeNoAcidente result = new CasualidadeNoAcidente();
            if (rs.next()) {
                result.setIdAcidente(rs.getLong("id_acidente_cc"));
                result.setIdTipoCasualidade(rs.getLong("id_tipo_casualidade"));
                result.setQuantidade(rs.getInt("quantidade"));
                return result;
            }

            return null;


        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar casualidade no acidente");
        }

    }

    @Override
    public List<CasualidadeNoAcidente> getAll() throws SQLException {

        String sql = "SELECT * FROM casualidade_acidente";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            ResultSet rs = prstate.executeQuery();
            List<CasualidadeNoAcidente> result = new ArrayList<>();
            while (rs.next()) {
                CasualidadeNoAcidente casualidadeNoAcidente = new CasualidadeNoAcidente();
                casualidadeNoAcidente.setIdAcidente(rs.getLong("id_acidente_cc"));
                casualidadeNoAcidente.setIdTipoCasualidade(rs.getLong("id_tipo_casualidade"));
                casualidadeNoAcidente.setQuantidade(rs.getInt("quantidade"));
                result.add(casualidadeNoAcidente);
            }
            return result;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar todas as casualidades no acidente");
        }

    }



}
