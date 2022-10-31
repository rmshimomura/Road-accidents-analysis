package com.uel.road_accidents_analysis.dao.interfaces.custom;

import com.uel.road_accidents_analysis.dao.interfaces.base.DAO;
import com.uel.road_accidents_analysis.model.VeiculoAcidenteSemCasualidade;

import java.sql.SQLException;

public interface VeiculoAcidenteSemCasualidadeDAO extends DAO<VeiculoAcidenteSemCasualidade> {

    public VeiculoAcidenteSemCasualidade getByDoubleId(Long idAcidenteSemCasualidade, Long idVeiculo) throws SQLException;

}
