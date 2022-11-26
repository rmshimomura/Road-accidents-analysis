package com.uel.road_accidents_analysis.dao.implementations.Postgres;

import com.uel.road_accidents_analysis.dao.interfaces.custom.VeiculoAcidenteSemCasualidadeDAO;
import com.uel.road_accidents_analysis.model.VeiculoAcidenteSemCasualidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresVeiculoAcidenteSemCasualidadeDAO implements VeiculoAcidenteSemCasualidadeDAO {

    private final Connection connection;

    public PostgresVeiculoAcidenteSemCasualidadeDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(VeiculoAcidenteSemCasualidade veiculoAcidenteSemCasualidade) throws SQLException {

        String sql = "INSERT INTO veiculo_acidente_sc (id_acidente_sc, id_veiculo, quantidade) VALUES (?, ?, ?)";
        //print prepared statement

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, veiculoAcidenteSemCasualidade.getIdAcidenteSemCasualidade());
            prstate.setLong(2, veiculoAcidenteSemCasualidade.getIdVeiculo());
            prstate.setInt(3, veiculoAcidenteSemCasualidade.getQuantidade());

            prstate.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao inserir veiculo_acidente_sc");
        }

    }

    @Override
    public void update(VeiculoAcidenteSemCasualidade veiculoAcidenteSemCasualidade) throws SQLException {

        String sql = "UPDATE veiculo_acidente_sc SET id_acidente_sc = ?, id_veiculo = ?, quantidade = ? WHERE id_acidente_sc = ? AND id_veiculo = ?";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, veiculoAcidenteSemCasualidade.getIdAcidenteSemCasualidade());
            prstate.setLong(2, veiculoAcidenteSemCasualidade.getIdVeiculo());
            prstate.setInt(3, veiculoAcidenteSemCasualidade.getQuantidade());
            prstate.setLong(4, veiculoAcidenteSemCasualidade.getIdAcidenteSemCasualidade());
            prstate.setLong(5, veiculoAcidenteSemCasualidade.getIdVeiculo());

            prstate.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao atualizar veiculo_acidente_sc");
        }

    }

    @Override
    public void delete(VeiculoAcidenteSemCasualidade veiculoAcidenteSemCasualidade) throws SQLException {

        String sql = "DELETE FROM veiculo_acidente_sc WHERE id_acidente_sc = ? AND id_veiculo = ?";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, veiculoAcidenteSemCasualidade.getIdAcidenteSemCasualidade());
            prstate.setLong(2, veiculoAcidenteSemCasualidade.getIdVeiculo());

            prstate.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao deletar veiculo_acidente_sc");
        }

    }

    @Override
    public VeiculoAcidenteSemCasualidade getById(Long id_acidente_Sem_casualidade) throws SQLException {
        return null;
    }

    @Override
    public VeiculoAcidenteSemCasualidade getByDoubleId(Long idAcidenteSemCasualidade, Long idVeiculo) throws SQLException {
        String sql = "SELECT * FROM veiculo_acidente_sc WHERE id_acidente_sc = ? AND id_veiculo = ?";
        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, idAcidenteSemCasualidade);
            prstate.setLong(2, idVeiculo);
            ResultSet rs = prstate.executeQuery();

            if (rs.next()) {
                VeiculoAcidenteSemCasualidade veiculoAcidenteSemCasualidade = new VeiculoAcidenteSemCasualidade();
                veiculoAcidenteSemCasualidade.setIdAcidenteSemCasualidade(rs.getLong("id_acidente_sc"));
                veiculoAcidenteSemCasualidade.setIdVeiculo(rs.getLong("id_veiculo"));
                veiculoAcidenteSemCasualidade.setQuantidade(rs.getInt("quantidade"));

                return veiculoAcidenteSemCasualidade;
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar veiculo_acidente_sc");
        }
        return null;
    }

    @Override
    public List<VeiculoAcidenteSemCasualidade> getAll() throws SQLException {
        String sql = "SELECT * FROM veiculo_acidente_sc";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            ResultSet rs = prstate.executeQuery();

            List<VeiculoAcidenteSemCasualidade> veiculoAcidenteSemCasualidades = new ArrayList<>();

            while (rs.next()) {
                VeiculoAcidenteSemCasualidade veiculoAcidenteSemCasualidade = new VeiculoAcidenteSemCasualidade();
                veiculoAcidenteSemCasualidade.setIdAcidenteSemCasualidade(rs.getLong("id_acidente_sc"));
                veiculoAcidenteSemCasualidade.setIdVeiculo(rs.getLong("id_veiculo"));
                veiculoAcidenteSemCasualidade.setQuantidade(rs.getInt("quantidade"));

                veiculoAcidenteSemCasualidades.add(veiculoAcidenteSemCasualidade);
            }

            return veiculoAcidenteSemCasualidades;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar veiculo_acidente_sc");
        }

    }

    @Override
    public int getCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM veiculo_acidente_sc";
        int count = 0;

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.executeQuery();

            while (prstate.getResultSet().next()) {
                count = prstate.getResultSet().getInt(1);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar quantidade de acidentes sem casualidade");
        }

        return count;
    }


}
