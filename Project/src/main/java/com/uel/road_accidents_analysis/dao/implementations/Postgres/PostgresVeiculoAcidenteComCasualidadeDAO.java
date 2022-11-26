package com.uel.road_accidents_analysis.dao.implementations.Postgres;

import com.uel.road_accidents_analysis.dao.interfaces.custom.VeiculoAcidenteComCasualidadeDAO;
import com.uel.road_accidents_analysis.model.VeiculoAcidenteComCasualidade;
import com.uel.road_accidents_analysis.model.query_aux.VeiculoCount;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresVeiculoAcidenteComCasualidadeDAO implements VeiculoAcidenteComCasualidadeDAO {

    private final Connection connection;

    public PostgresVeiculoAcidenteComCasualidadeDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(VeiculoAcidenteComCasualidade veiculoAcidenteComCasualidade) throws SQLException {

        String sql = "INSERT INTO veiculo_acidente_cc (id_acidente_cc, id_veiculo, quantidade) VALUES (?, ?, ?)";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, veiculoAcidenteComCasualidade.getIdAcidenteComCasualidade());
            prstate.setLong(2, veiculoAcidenteComCasualidade.getIdVeiculo());
            prstate.setInt(3, veiculoAcidenteComCasualidade.getQuantidade());

            prstate.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao inserir veiculo_acidente_cc");
        }

    }

    @Override
    public void update(VeiculoAcidenteComCasualidade veiculoAcidenteComCasualidade) throws SQLException {

        String sql = "UPDATE veiculo_acidente_cc SET id_acidente_cc = ?, id_veiculo = ?, quantidade = ? WHERE id_acidente_cc = ? AND id_veiculo = ?";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, veiculoAcidenteComCasualidade.getIdAcidenteComCasualidade());
            prstate.setLong(2, veiculoAcidenteComCasualidade.getIdVeiculo());
            prstate.setInt(3, veiculoAcidenteComCasualidade.getQuantidade());
            prstate.setLong(4, veiculoAcidenteComCasualidade.getIdAcidenteComCasualidade());
            prstate.setLong(5, veiculoAcidenteComCasualidade.getIdVeiculo());

            prstate.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao atualizar veiculo_acidente_cc");
        }

    }

    @Override
    public void delete(VeiculoAcidenteComCasualidade veiculoAcidenteComCasualidade) throws SQLException {

        String sql = "DELETE FROM veiculo_acidente_cc WHERE id_acidente_cc = ? AND id_veiculo = ?";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, veiculoAcidenteComCasualidade.getIdAcidenteComCasualidade());
            prstate.setLong(2, veiculoAcidenteComCasualidade.getIdVeiculo());

            prstate.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao deletar veiculo_acidente_cc");
        }

    }

    @Override
    public VeiculoAcidenteComCasualidade getById(Long id_acidente_com_casualidade) throws SQLException {
        return null;
    }

    @Override
    public VeiculoAcidenteComCasualidade getByDoubleId(Long idAcidenteComCasualidade, Long idVeiculo) throws SQLException {
        String sql = "SELECT * FROM veiculo_acidente_cc WHERE id_acidente_cc = ? AND id_veiculo = ?";
        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, idAcidenteComCasualidade);
            prstate.setLong(2, idVeiculo);
            ResultSet rs = prstate.executeQuery();

            if (rs.next()) {
                VeiculoAcidenteComCasualidade veiculoAcidenteComCasualidade = new VeiculoAcidenteComCasualidade();
                veiculoAcidenteComCasualidade.setIdAcidenteComCasualidade(rs.getLong("id_acidente_cc"));
                veiculoAcidenteComCasualidade.setIdVeiculo(rs.getLong("id_veiculo"));
                veiculoAcidenteComCasualidade.setQuantidade(rs.getInt("quantidade"));

                return veiculoAcidenteComCasualidade;
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar veiculo_acidente_cc");
        }
        return null;
    }

    @Override
    public List<VeiculoAcidenteComCasualidade> getAll() throws SQLException {
        String sql = "SELECT * FROM veiculo_acidente_cc";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            ResultSet rs = prstate.executeQuery();

            List<VeiculoAcidenteComCasualidade> veiculoAcidenteComCasualidades = new ArrayList<>();

            while (rs.next()) {
                VeiculoAcidenteComCasualidade veiculoAcidenteComCasualidade = new VeiculoAcidenteComCasualidade();
                veiculoAcidenteComCasualidade.setIdAcidenteComCasualidade(rs.getLong("id_acidente_cc"));
                veiculoAcidenteComCasualidade.setIdVeiculo(rs.getLong("id_veiculo"));
                veiculoAcidenteComCasualidade.setQuantidade(rs.getInt("quantidade"));

                veiculoAcidenteComCasualidades.add(veiculoAcidenteComCasualidade);
            }

            return veiculoAcidenteComCasualidades;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar veiculo_acidente_cc");
        }

    }

    @Override
    public int getCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM veiculo_acidente_cc";
        int count = 0;

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.executeQuery();

            while (prstate.getResultSet().next()) {
                count = prstate.getResultSet().getInt(1);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar quantidade de veiculos com casualidade");
        }

        return count;
    }

    @Override
    public List<VeiculoCount> count_groups() throws SQLException {

        String sql = "SELECT SUM(vac.quantidade), v.nome_veiculo FROM veiculo_acidente_cc vac INNER JOIN veiculo v ON vac.id_veiculo = v.id_veiculo GROUP BY v.nome_veiculo order by v.nome_veiculo";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            ResultSet rs = prstate.executeQuery();

            List<VeiculoCount> veiculoCounts = new ArrayList<>();

            while (rs.next()) {
                VeiculoCount veiculoCount = new VeiculoCount();
                veiculoCount.setQuantidade(rs.getInt("sum"));
                veiculoCount.setNomeVeiculo(rs.getString("nome_veiculo"));

                veiculoCounts.add(veiculoCount);
            }

            return veiculoCounts;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar veiculo_acidente_cc");
        }

    }


}
