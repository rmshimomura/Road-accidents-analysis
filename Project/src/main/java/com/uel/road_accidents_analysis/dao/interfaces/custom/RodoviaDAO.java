package com.uel.road_accidents_analysis.dao.interfaces.custom;

import com.uel.road_accidents_analysis.dao.interfaces.base.DAO;
import com.uel.road_accidents_analysis.model.Rodovia;
import com.uel.road_accidents_analysis.model.query_aux.AveragePerState;

import java.sql.SQLException;
import java.util.List;

public interface RodoviaDAO extends DAO<Rodovia> {
    List<Rodovia> getRodoviaByUfAndName(String uf, String nomeRodovia) throws SQLException;

    List<Rodovia> getAllRodoviaByName(String name) throws SQLException;

    List<String> getAllUF() throws SQLException;

    List<Rodovia> getAllRodoviaByUF(String uf) throws SQLException;

    List<AveragePerState> getStateAverages() throws SQLException;

}
