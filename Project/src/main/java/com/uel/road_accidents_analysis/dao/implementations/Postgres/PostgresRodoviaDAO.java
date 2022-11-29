package com.uel.road_accidents_analysis.dao.implementations.Postgres;

import com.uel.road_accidents_analysis.dao.interfaces.custom.RodoviaDAO;
import com.uel.road_accidents_analysis.model.Rodovia;
import com.uel.road_accidents_analysis.model.query_aux.AveragePerState;

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

    @Override
    public void insert(Rodovia rodovia) throws SQLException {
        String sql = "INSERT INTO rodovia (uf, nome_rodovia) VALUES (?, ?)";

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setString(1, rodovia.getUF());
            prstate.setString(2, rodovia.getNome());

            prstate.execute();
        } catch (SQLException e) {
//            System.err.println(e.getMessage());
            throw new SQLException("Erro ao inserir rodovia");
        }

    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public List<Rodovia> getRodoviaByUfAndName(String uf, String nomeRodovia) throws SQLException {
        String sql = "SELECT * FROM rodovia WHERE uf = ? AND nome_rodovia like ?";

        List<Rodovia> rodovias = new ArrayList<>();

        try (PreparedStatement prstate = connection.prepareStatement(sql)) {
            prstate.setString(1, uf);
            prstate.setString(2, "%" + nomeRodovia + "%");

            try (ResultSet result = prstate.executeQuery()) {

                while (result.next()) {
                    Rodovia rodovia = new Rodovia();
                    rodovia.setId(result.getLong("id_rodovia"));
                    rodovia.setUF(result.getString("uf"));
                    rodovia.setNome(result.getString("nome_rodovia"));

                    rodovias.add(rodovia);
                }

                return rodovias;
            } catch (SQLException e) {
                System.err.println(e.getMessage());
                throw new SQLException("Erro ao buscar rodovia");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar rodovia");
        }

    }

    @Override
    public List<Rodovia> getAll() throws SQLException {

        List<Rodovia> rodovias = new ArrayList<Rodovia>();

        String sql = "SELECT * FROM rodovia group by id_rodovia, uf, nome_rodovia order by uf, nome_rodovia";

        try {
            PreparedStatement prstate = connection.prepareStatement(sql);

            ResultSet result = prstate.executeQuery();

            while (result.next()) {
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

    @Override
    public int getCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM rodovia";
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
    public List<String> getAllUF() throws SQLException {

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
            throw new SQLException("Erro ao buscar rodovias");
        }


        return rodovias;
    }

    @Override
    public List<Rodovia> getAllRodoviaByName(String name) throws SQLException {

        List<Rodovia> rodovias = new ArrayList<>();

        String sql = "SELECT * FROM rodovia WHERE nome_rodovia LIKE ? group by id_rodovia, uf, nome_rodovia order by uf, nome_rodovia";

        try {
            PreparedStatement prstate = connection.prepareStatement(sql);
            prstate.setString(1, "%" + name + "%");

            ResultSet result = prstate.executeQuery();

            while (result.next()) {
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

    @Override
    public List<Rodovia> getAllRodoviaByUF(String uf) throws SQLException {

        List<Rodovia> rodovias = new ArrayList<>();

        String sql = "SELECT * FROM rodovia WHERE uf = ? GROUP BY id_rodovia, uf, nome_rodovia ORDER BY uf, nome_rodovia";

        try {
            PreparedStatement prstate = connection.prepareStatement(sql);
            prstate.setString(1, uf);

            ResultSet result = prstate.executeQuery();

            while (result.next()) {
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

    @Override
    public List<AveragePerState> getStateAverages() throws SQLException {

        List<AveragePerState> averages = new ArrayList<>();

        String sql = "SELECT rod.uf, \n" +
                "ROUND(AVG(t.icm)::numeric,2) as icm_medio,\n" +
                "ROUND(AVG(t.icp)::numeric,2) as icp_medio,\n" +
                "ROUND(AVG(t.icc)::numeric,2) as icc_medio\n" +
                "FROM rodovia rod\n" +
                "INNER JOIN trecho t\n" +
                "ON rod.id_rodovia = t.id_rodovia\n" +
                "GROUP BY rod.uf;";

        try {
            PreparedStatement prstate = connection.prepareStatement(sql);

            ResultSet result = prstate.executeQuery();

            while (result.next()) {
                AveragePerState average = new AveragePerState();
                average.setUf(result.getString("uf"));
                average.setIccAverage(result.getDouble("icc_medio"));
                average.setIcmAverage(result.getDouble("icm_medio"));
                average.setIcpAverage(result.getDouble("icp_medio"));

                averages.add(average);
            }

            result.close();
            prstate.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new SQLException("Erro ao buscar rodovias");

        }

        return averages;

    }

}
