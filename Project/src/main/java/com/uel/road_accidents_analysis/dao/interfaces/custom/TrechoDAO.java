package com.uel.road_accidents_analysis.dao.interfaces.custom;

import com.uel.road_accidents_analysis.dao.interfaces.base.DAO;
import com.uel.road_accidents_analysis.model.Trecho;

import java.sql.SQLException;
import java.util.Date;

public interface TrechoDAO extends DAO<Trecho> {
    public Trecho getTrechoByRodoviaKmAndData(Long idRodovia, Double km, Date dataAvaliacao) throws SQLException;
}

