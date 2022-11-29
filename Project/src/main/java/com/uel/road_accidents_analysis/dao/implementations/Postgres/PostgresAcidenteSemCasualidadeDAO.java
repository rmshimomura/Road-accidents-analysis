package com.uel.road_accidents_analysis.dao.implementations.Postgres;

import com.uel.road_accidents_analysis.dao.interfaces.custom.AcidenteSemCasualidadeDAO;
import com.uel.road_accidents_analysis.model.AcidenteSemCasualidade;
import com.uel.road_accidents_analysis.model.query_aux.AcidentesRodoviaCount;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresAcidenteSemCasualidadeDAO implements AcidenteSemCasualidadeDAO {
    private final Connection connection;

    public PostgresAcidenteSemCasualidadeDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(AcidenteSemCasualidade acidenteSemCasualidade) throws SQLException {
        String sql = "INSERT INTO Acidente_sc(id_trecho, data_acidente, horario, km_acidente, sentido, tipo_acidente) VALUES(?,?,?,?,?,?)";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, acidenteSemCasualidade.getIdTrecho());
            prstate.setDate(2, new java.sql.Date(acidenteSemCasualidade.getData().getTime()));
            prstate.setTime(3, acidenteSemCasualidade.getHorario());
            prstate.setDouble(4, acidenteSemCasualidade.getKm());
            prstate.setString(5, acidenteSemCasualidade.getSentido());
            prstate.setString(6, acidenteSemCasualidade.getTipo());

            //print prepared statement
            prstate.execute();
        } catch (SQLException e) {
            //System.err.println(e.getMessage());
            throw new SQLException("Erro ao inserir acidente sem casualidade");
        }

    }

    @Override
    public void update(AcidenteSemCasualidade acidenteSemCasualidade) throws SQLException {
        String sql = "UPDATE Acidente_sc SET id_trecho = ?, data_acidente = ?, horario = ?, km_acidente = ?, sentido = ?, tipo_acidente = ? WHERE id_acidente_sc = ?";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, acidenteSemCasualidade.getIdTrecho());
            prstate.setDate(2, (Date) acidenteSemCasualidade.getData());
            prstate.setTime(3, acidenteSemCasualidade.getHorario());
            prstate.setDouble(4, acidenteSemCasualidade.getKm());
            prstate.setString(5, acidenteSemCasualidade.getSentido());
            prstate.setString(6, acidenteSemCasualidade.getTipo());
            prstate.setLong(7, acidenteSemCasualidade.getId());
            prstate.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao atualizar acidente sem casualidade");
        }

    }

    @Override
    public void delete(AcidenteSemCasualidade acidenteSemCasualidade) throws SQLException {
        String sql = "DELETE FROM Acidente_sc WHERE id_acidente_sc = ?";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, acidenteSemCasualidade.getId());
            prstate.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao deletar acidente sem casualidade");
        }
    }

    @Override
    public AcidenteSemCasualidade getById(Long id) throws SQLException {
        String sql = "SELECT * FROM Acidente_sc WHERE id_acidente_sc = ?";
        AcidenteSemCasualidade acidenteSemCasualidade = null;

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, id);
            prstate.executeQuery();

            if (prstate.getResultSet().next()) {
                acidenteSemCasualidade = new AcidenteSemCasualidade();
                acidenteSemCasualidade.setId(prstate.getResultSet().getLong("id_acidente_sc"));
                acidenteSemCasualidade.setIdTrecho(prstate.getResultSet().getLong("id_trecho"));
                acidenteSemCasualidade.setData(prstate.getResultSet().getDate("data_acidente"));
                acidenteSemCasualidade.setHorario(prstate.getResultSet().getTime("horario"));
                acidenteSemCasualidade.setKm(prstate.getResultSet().getDouble("km_acidente"));
                acidenteSemCasualidade.setSentido(prstate.getResultSet().getString("sentido"));
                acidenteSemCasualidade.setTipo(prstate.getResultSet().getString("tipo_acidente"));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar acidente sem casualidade");
        }

        return acidenteSemCasualidade;
    }

    @Override
    public List<AcidenteSemCasualidade> getAll() throws SQLException {
        List<AcidenteSemCasualidade> acidentesSemCasualidade = new ArrayList<AcidenteSemCasualidade>();
        String sql = "SELECT * FROM Acidente_sc";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.executeQuery();

            while (prstate.getResultSet().next()) {
                AcidenteSemCasualidade acidenteSemCasualidade = new AcidenteSemCasualidade();
                acidenteSemCasualidade.setId(prstate.getResultSet().getLong("id_acidente_sc"));
                acidenteSemCasualidade.setIdTrecho(prstate.getResultSet().getLong("id_trecho"));
                acidenteSemCasualidade.setData(prstate.getResultSet().getDate("data_acidente"));
                acidenteSemCasualidade.setHorario(prstate.getResultSet().getTime("horario"));
                acidenteSemCasualidade.setKm(prstate.getResultSet().getDouble("km_acidente"));
                acidenteSemCasualidade.setSentido(prstate.getResultSet().getString("sentido"));
                acidenteSemCasualidade.setTipo(prstate.getResultSet().getString("tipo_acidente"));
                acidentesSemCasualidade.add(acidenteSemCasualidade);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar acidentes sem casualidade");
        }

        return acidentesSemCasualidade;
    }

    @Override
    public int getCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM Acidente_sc";
        int count = 0;

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.executeQuery();

            if (prstate.getResultSet().next()) {
                count = prstate.getResultSet().getInt(1);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar quantidade de acidentes sem casualidade");
        }

        return count;
    }

    @Override
    public AcidenteSemCasualidade getByTrechoHorarioDataAndKm(Long idTrecho, java.util.Date data, Time horario, Double km) throws SQLException {
        String sql = "SELECT * FROM Acidente_sc WHERE id_trecho = ? AND data_acidente = ? AND horario = ? AND km_acidente = ?";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, idTrecho);
            prstate.setDate(2, new Date(data.getTime()));
            prstate.setTime(3, horario);
            prstate.setDouble(4, km);

            prstate.execute();

            if (prstate.getResultSet().next()) {
                AcidenteSemCasualidade acidenteSemCasualidade = new AcidenteSemCasualidade();
                acidenteSemCasualidade.setId(prstate.getResultSet().getLong("id_acidente_sc"));
                acidenteSemCasualidade.setIdTrecho(prstate.getResultSet().getLong("id_trecho"));
                acidenteSemCasualidade.setData(prstate.getResultSet().getDate("data_acidente"));
                acidenteSemCasualidade.setHorario(prstate.getResultSet().getTime("horario"));
                acidenteSemCasualidade.setKm(prstate.getResultSet().getDouble("km_acidente"));
                acidenteSemCasualidade.setSentido(prstate.getResultSet().getString("sentido"));
                acidenteSemCasualidade.setTipo(prstate.getResultSet().getString("tipo_acidente"));

                return acidenteSemCasualidade;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar acidente sem casualidade");
        }

        return null;
    }

    @Override
    public List<AcidentesRodoviaCount> getCountAcidentesForEachRodovia() throws SQLException {
        String sql = "SELECT COUNT(*), " +
                "rod.nome_rodovia " +
                "FROM acidente_sc a_sc " +
                "INNER JOIN trecho t " +
                "ON a_sc.id_trecho = t.id_trecho " +
                "INNER JOIN rodovia rod " +
                "ON rod.id_rodovia = t.id_rodovia " +
                "GROUP BY rod.id_rodovia";

        List<AcidentesRodoviaCount> acidentesRodoviaCount = new ArrayList<AcidentesRodoviaCount>();

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.executeQuery();

            while (prstate.getResultSet().next()) {
                AcidentesRodoviaCount acidentesRodovia = new AcidentesRodoviaCount();
                acidentesRodovia.setCount(prstate.getResultSet().getInt(1));
                acidentesRodovia.setNomeRodovia(prstate.getResultSet().getString(2));
                acidentesRodoviaCount.add(acidentesRodovia);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar acidentes por rodovia");
        }

        return acidentesRodoviaCount;
    }
}
