package com.uel.road_accidents_analysis.dao.implementations.Postgres;

import com.uel.road_accidents_analysis.dao.interfaces.custom.VeiculoDAO;
import com.uel.road_accidents_analysis.model.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresVeiculoDAO implements VeiculoDAO {
    private final Connection connection;

    public PostgresVeiculoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO veiculo (nome_veiculo) VALUES (?)";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setString(1, veiculo.getNome());

            prstate.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao inserir veiculo");
        }
    }

    @Override
    public void update(Veiculo veiculo) throws SQLException {
        String sql = "UPDATE veiculo SET nome_veiculo = ? WHERE id_veiculo = ?";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setString(1, veiculo.getNome());
            prstate.setLong(2, veiculo.getId());

            prstate.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao atualizar veiculo");
        }
    }

    @Override
    public void delete(Veiculo veiculo) throws SQLException {
        String sql = "DELETE FROM veiculo WHERE id_veiculo = ?";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, veiculo.getId());
            prstate.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao deletar veiculo");
        }
    }

    @Override
    public Veiculo getById(Long id) throws SQLException {
        String sql = "SELECT * FROM veiculo WHERE id_veiculo = ?";
        Veiculo veiculo = null;

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, id);
            ResultSet rs = prstate.executeQuery();

            if (rs.next()) {
                veiculo = new Veiculo();
                veiculo.setId(rs.getLong("id_veiculo"));
                veiculo.setNome(rs.getString("nome_veiculo"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar veiculo por id");
        }

        return veiculo;
    }


    @Override
    public List<Veiculo> getAll() throws SQLException {
        String sql = "SELECT * FROM veiculo";
        List<Veiculo> veiculos = new ArrayList<Veiculo>();

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            ResultSet rs = prstate.executeQuery();

            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getLong("id_veiculo"));
                veiculo.setNome(rs.getString("nome_veiculo"));

                veiculos.add(veiculo);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar veiculos");
        }

        return veiculos;
    }

    @Override
    public int getCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM veiculo";
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
