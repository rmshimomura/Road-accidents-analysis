package com.uel.road_accidents_analysis.dao.implementations.Postgres;

import com.uel.road_accidents_analysis.dao.interfaces.custom.TrechoDAO;
import com.uel.road_accidents_analysis.model.Rodovia;
import com.uel.road_accidents_analysis.model.Trecho;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgresTrechoDAO implements TrechoDAO {

    private final Connection connection;

    public PostgresTrechoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Trecho trecho) throws SQLException {
        String sql = "INSERT INTO trecho (id_rodovia, km_inicial, km_final, data_avaliacao, icc, icp, icm) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, trecho.getIdRodovia());
            prstate.setDouble(2, trecho.getKmInicial());
            prstate.setDouble(3, trecho.getKmFinal());
            prstate.setDate(4, new java.sql.Date(trecho.getDataAvaliacao().getTime()));
            prstate.setDouble(5, trecho.getICC());
            prstate.setDouble(6, trecho.getICP());
            prstate.setDouble(7, trecho.getICM());

            prstate.execute();
        } catch (SQLException e) {
//            System.err.println(e.getMessage());
            throw new SQLException("Erro ao inserir trecho");
        }
    }

    @Override
    public void update(Trecho trecho) throws SQLException {
        String sql = "UPDATE trecho SET id_rodovia = ?, km_inicial = ?, km_final = ?, data_avaliacao = ?, icc = ?, icp = ?, icm = ? WHERE id_trecho = ?";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, trecho.getIdRodovia());
            prstate.setDouble(2, trecho.getKmInicial());
            prstate.setDouble(3, trecho.getKmFinal());
            prstate.setDate(4, (Date) trecho.getDataAvaliacao());
            prstate.setDouble(5, trecho.getICC());
            prstate.setDouble(6, trecho.getICP());
            prstate.setDouble(7, trecho.getICM());
            prstate.setLong(8, trecho.getId());

            prstate.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao atualizar trecho");
        }
    }

    @Override
    public void delete(Trecho trecho) throws SQLException {
        String sql = "DELETE FROM trecho WHERE id_trecho = ?";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, trecho.getId());

            prstate.execute();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao deletar trecho");
        }
    }

    @Override
    public Trecho getById(Long id) throws SQLException {
        String sql = "SELECT * FROM trecho WHERE id_trecho = ?";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, id);

            ResultSet rs = prstate.executeQuery();

            if (rs.next()) {
                Trecho trecho = new Trecho();
                trecho.setId(rs.getLong("id_trecho"));
                trecho.setIdRodovia(rs.getLong("id_rodovia"));
                trecho.setKmInicial(rs.getDouble("km_inicial"));
                trecho.setKmFinal(rs.getDouble("km_final"));
                trecho.setDataAvaliacao(rs.getDate("data_avaliacao"));
                trecho.setICC(rs.getDouble("icc"));
                trecho.setICP(rs.getDouble("icp"));
                trecho.setICM(rs.getDouble("icm"));

                return trecho;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar trecho por id");
        }

        return null;
    }

    @Override
    public List<Trecho> getAll() throws SQLException {
        String sql = "SELECT * FROM trecho";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            ResultSet rs = prstate.executeQuery();

            List<Trecho> trechos = new ArrayList<>();

            while (rs.next()) {
                Trecho trecho = new Trecho();
                trecho.setId(rs.getLong("id_trecho"));
                trecho.setIdRodovia(rs.getLong("id_rodovia"));
                trecho.setKmInicial(rs.getDouble("km_inicial"));
                trecho.setKmFinal(rs.getDouble("km_final"));
                trecho.setDataAvaliacao(rs.getDate("data_avaliacao"));
                trecho.setICC(rs.getDouble("icc"));
                trecho.setICP(rs.getDouble("icp"));
                trecho.setICM(rs.getDouble("icm"));

                trechos.add(trecho);
            }

            return trechos;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar todos os trechos");
        }
    }

    @Override
    public int getCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM trecho";
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
    public Trecho getTrechoByRodoviaKmAndData(Long idRodovia, Double km, java.util.Date dataAvaliacao) throws java.sql.SQLException{

        String sql = "SELECT * FROM trecho WHERE id_rodovia = ? AND km_inicial <= ? AND km_final >= ? ORDER BY ABS(data_avaliacao - TO_DATE(?, 'YYYY-MM-DD')) LIMIT 1";
        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setLong(1, idRodovia);
            prstate.setDouble(2, km);
            prstate.setDouble(3, km);
            prstate.setDate(4, new Date(dataAvaliacao.getTime()));

            ResultSet rs = prstate.executeQuery();

            if (rs.next()) {
                Trecho trecho = new Trecho();
                trecho.setId(rs.getLong("id_trecho"));
                trecho.setIdRodovia(rs.getLong("id_rodovia"));
                trecho.setKmInicial(rs.getDouble("km_inicial"));
                trecho.setKmFinal(rs.getDouble("km_final"));
                trecho.setDataAvaliacao(rs.getDate("data_avaliacao"));
                trecho.setICC(rs.getDouble("icc"));
                trecho.setICP(rs.getDouble("icp"));
                trecho.setICM(rs.getDouble("icm"));

                return trecho;
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar trecho por rodovia, km e data");
        }

        return null;
    }

    @Override
    public List<Trecho> getTrechosByRodovia(String uf, String rodovia) throws SQLException{

        String sql = "SELECT * FROM trecho WHERE id_rodovia IN (SELECT id_rodovia FROM rodovia WHERE uf = ? AND nome_rodovia = ?)";
        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setString(1, uf);
            prstate.setString(2, rodovia);

            ResultSet rs = prstate.executeQuery();

            List<Trecho> trechos = new ArrayList<>();

            while (rs.next()) {
                Trecho trecho = new Trecho();
                trecho.setId(rs.getLong("id_trecho"));
                trecho.setIdRodovia(rs.getLong("id_rodovia"));
                trecho.setKmInicial(rs.getDouble("km_inicial"));
                trecho.setKmFinal(rs.getDouble("km_final"));
                trecho.setDataAvaliacao(rs.getDate("data_avaliacao"));
                trecho.setICC(rs.getDouble("icc"));
                trecho.setICP(rs.getDouble("icp"));
                trecho.setICM(rs.getDouble("icm"));

                trechos.add(trecho);
            }

            return trechos;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar trechos por rodovia");
        }
    }

    @Override
    public List<String> getUfs() throws SQLException {

        List<String> rodovias = new ArrayList<>();

        String sql = "SELECT DISTINCT uf FROM rodovia ORDER BY uf";

        try {
            PreparedStatement prstate = connection.prepareStatement(sql);

            ResultSet result = prstate.executeQuery();

            while (result.next()) {
                rodovias.add(result.getString("uf"));
            }

            result.close();
            prstate.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar Ufs");
        }


        return rodovias;
    }

}
