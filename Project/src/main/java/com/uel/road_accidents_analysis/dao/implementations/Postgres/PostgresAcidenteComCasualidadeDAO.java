package com.uel.road_accidents_analysis.dao.implementations.Postgres;

import com.uel.road_accidents_analysis.dao.interfaces.custom.AcidenteComCasualidadeDAO;
import com.uel.road_accidents_analysis.model.AcidenteComCasualidade;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresAcidenteComCasualidadeDAO implements AcidenteComCasualidadeDAO {

    private final Connection connection;

    public PostgresAcidenteComCasualidadeDAO(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void insert(AcidenteComCasualidade acidenteComCasualidade) throws SQLException {
        String sql = "INSERT INTO Acidente_cc(id_trecho, data_acidente, horario, km_acidente, sentido, tipo_acidente) VALUES(?,?,?,?,?,?)";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, acidenteComCasualidade.getIdTrecho());
            prstate.setDate(2, new java.sql.Date(acidenteComCasualidade.getData().getTime()));
            prstate.setTime(3, acidenteComCasualidade.getHorario());
            prstate.setDouble(4, acidenteComCasualidade.getKm());
            prstate.setString(5, acidenteComCasualidade.getSentido());
            prstate.setString(6, acidenteComCasualidade.getTipo());

            prstate.execute();
        } catch (SQLException e) {
            //System.err.println(e.getMessage());
            throw new SQLException("Erro ao inserir acidente sem casualidade");
        }
    }

    @Override
    public void update(AcidenteComCasualidade acidenteComCasualidade) throws SQLException {
        String sql = "UPDATE Acidente_cc SET id_trecho = ?, data_acidente = ?, horario = ?, km_acidente = ?, sentido = ?, tipo_acidente = ? WHERE id_acidente_cc = ?";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, acidenteComCasualidade.getIdTrecho());
            prstate.setDate(2, (Date) acidenteComCasualidade.getData());
            prstate.setTime(3, acidenteComCasualidade.getHorario());
            prstate.setDouble(4, acidenteComCasualidade.getKm());
            prstate.setString(5, acidenteComCasualidade.getSentido());
            prstate.setString(6, acidenteComCasualidade.getTipo());
            prstate.setLong(7, acidenteComCasualidade.getId());
            prstate.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao atualizar acidente sem casualidade");
        }
    }

    @Override
    public void delete(AcidenteComCasualidade acidenteComCasualidade) throws SQLException {
        String sql = "DELETE FROM Acidente_cc WHERE id_acidente_cc = ?";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, acidenteComCasualidade.getId());
            prstate.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao deletar acidente sem casualidade");
        }
    }

    @Override
    public AcidenteComCasualidade getById(Long id) throws SQLException {
        String sql = "SELECT * FROM Acidente_cc WHERE id_acidente_cc = ?";
        AcidenteComCasualidade acidenteComCasualidade = null;

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, id);
            prstate.executeQuery();

            while (prstate.getResultSet().next()) {
                acidenteComCasualidade = new AcidenteComCasualidade();
                acidenteComCasualidade.setId(prstate.getResultSet().getLong("id_acidente_cc"));
                acidenteComCasualidade.setIdTrecho(prstate.getResultSet().getLong("id_trecho"));
                acidenteComCasualidade.setData(prstate.getResultSet().getDate("data_acidente"));
                acidenteComCasualidade.setHorario(prstate.getResultSet().getTime("horario"));
                acidenteComCasualidade.setKm(prstate.getResultSet().getDouble("km_acidente"));
                acidenteComCasualidade.setSentido(prstate.getResultSet().getString("sentido"));
                acidenteComCasualidade.setTipo(prstate.getResultSet().getString("tipo_acidente"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar acidente sem casualidade");
        }

        return acidenteComCasualidade;
    }

    @Override
    public List<AcidenteComCasualidade> getAll() throws SQLException {
        String sql = "SELECT * FROM Acidente_cc";
        List<AcidenteComCasualidade> acidentesComCasualidade = new ArrayList<AcidenteComCasualidade>();

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.executeQuery();

            while (prstate.getResultSet().next()) {
                AcidenteComCasualidade acidenteComCasualidade = new AcidenteComCasualidade();
                acidenteComCasualidade.setId(prstate.getResultSet().getLong("id_acidente_cc"));
                acidenteComCasualidade.setIdTrecho(prstate.getResultSet().getLong("id_trecho"));
                acidenteComCasualidade.setData(prstate.getResultSet().getDate("data_acidente"));
                acidenteComCasualidade.setHorario(prstate.getResultSet().getTime("horario"));
                acidenteComCasualidade.setKm(prstate.getResultSet().getDouble("km_acidente"));
                acidenteComCasualidade.setSentido(prstate.getResultSet().getString("sentido"));
                acidenteComCasualidade.setTipo(prstate.getResultSet().getString("tipo_acidente"));
                acidentesComCasualidade.add(acidenteComCasualidade);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar acidentes sem casualidade");
        }

        return acidentesComCasualidade;
    }

    @Override
    public int getCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM Acidente_cc";
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

    @Override
    public AcidenteComCasualidade getByTrechoHorarioDataAndKm(Long idTrecho, java.util.Date data, Time horario, Double km) throws SQLException {
        String sql = "SELECT * FROM Acidente_cc WHERE id_trecho = ? AND data_acidente = ? AND horario = ? AND km_acidente = ?";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, idTrecho);
            prstate.setDate(2, new Date(data.getTime()));
            prstate.setTime(3, horario);
            prstate.setDouble(4, km);

            //print prepared statement
            prstate.execute();

            if (prstate.getResultSet().next()) {
                AcidenteComCasualidade acidenteComCasualidade = new AcidenteComCasualidade();
                acidenteComCasualidade.setId(prstate.getResultSet().getLong("id_acidente_cc"));
                acidenteComCasualidade.setIdTrecho(prstate.getResultSet().getLong("id_trecho"));
                acidenteComCasualidade.setData(prstate.getResultSet().getDate("data_acidente"));
                acidenteComCasualidade.setHorario(prstate.getResultSet().getTime("horario"));
                acidenteComCasualidade.setKm(prstate.getResultSet().getDouble("km_acidente"));
                acidenteComCasualidade.setSentido(prstate.getResultSet().getString("sentido"));
                acidenteComCasualidade.setTipo(prstate.getResultSet().getString("tipo_acidente"));

                return acidenteComCasualidade;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar acidente sem casualidade");
        }

        return null;
    }
}
