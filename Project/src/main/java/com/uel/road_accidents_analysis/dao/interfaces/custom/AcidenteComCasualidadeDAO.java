package com.uel.road_accidents_analysis.dao.interfaces.custom;

import com.uel.road_accidents_analysis.dao.interfaces.base.DAO;
import com.uel.road_accidents_analysis.model.AcidenteComCasualidade;
import com.uel.road_accidents_analysis.model.AcidenteSemCasualidade;
import com.uel.road_accidents_analysis.model.query_aux.AcidentesRodoviaCount;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface AcidenteComCasualidadeDAO extends DAO<AcidenteComCasualidade> {
    public AcidenteComCasualidade getByTrechoHorarioDataAndKm(Long idTrecho, Date data, Time horario, Double km) throws SQLException;

    public List<AcidentesRodoviaCount> getCountAcidentesForEachRodovia() throws SQLException;

}
