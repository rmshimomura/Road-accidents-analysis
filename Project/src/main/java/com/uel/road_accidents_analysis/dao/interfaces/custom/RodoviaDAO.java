package com.uel.road_accidents_analysis.dao.interfaces.custom;

import com.uel.road_accidents_analysis.dao.interfaces.base.DAO;
import com.uel.road_accidents_analysis.model.Rodovia;

import java.sql.SQLException;

public interface RodoviaDAO extends DAO<Rodovia> {
    Long getIdByInfo(Rodovia rodovia) throws SQLException;
}
