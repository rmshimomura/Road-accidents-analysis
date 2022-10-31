package com.uel.road_accidents_analysis.dao.interfaces.custom;

import com.uel.road_accidents_analysis.dao.interfaces.base.DAO;
import com.uel.road_accidents_analysis.model.VeiculoAcidenteComCasualidade;

import java.sql.SQLException;

public interface VeiculoAcidenteComCasualidadeDAO extends DAO<VeiculoAcidenteComCasualidade> {

    public VeiculoAcidenteComCasualidade getByDoubleId(Long idAcidenteComCasualidade, Long idVeiculo) throws SQLException;

}
