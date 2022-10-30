package com.uel.road_accidents_analysis.dao;

import com.uel.road_accidents_analysis.model.Rodovia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostgresRodoviaDAO {
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
        }

    }
    public List<Rodovia> getRodovias() {
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
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return rodovias;
    }

}
