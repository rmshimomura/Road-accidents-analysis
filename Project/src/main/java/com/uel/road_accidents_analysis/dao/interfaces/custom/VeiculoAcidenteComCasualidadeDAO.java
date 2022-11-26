package com.uel.road_accidents_analysis.dao.interfaces.custom;

import com.uel.road_accidents_analysis.dao.interfaces.base.DAO;
import com.uel.road_accidents_analysis.model.VeiculoAcidenteComCasualidade;
import com.uel.road_accidents_analysis.model.query_aux.VeiculoCount;

import java.sql.SQLException;
import java.util.List;

public interface VeiculoAcidenteComCasualidadeDAO extends DAO<VeiculoAcidenteComCasualidade> {

    public VeiculoAcidenteComCasualidade getByDoubleId(Long idAcidenteComCasualidade, Long idVeiculo) throws SQLException;
    public List<VeiculoCount> count_groups() throws SQLException;

}
