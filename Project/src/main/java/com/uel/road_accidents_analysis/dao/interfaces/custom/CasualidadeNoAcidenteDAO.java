package com.uel.road_accidents_analysis.dao.interfaces.custom;

import com.uel.road_accidents_analysis.dao.interfaces.base.DAO;
import com.uel.road_accidents_analysis.model.CasualidadeNoAcidente;
import com.uel.road_accidents_analysis.model.CasualidadeNoAcidenteTotal;

import java.sql.SQLException;
import java.util.List;

public interface CasualidadeNoAcidenteDAO extends DAO<CasualidadeNoAcidente> {

    public CasualidadeNoAcidente getByDoubleId(Long id_acidente_cc, Long id_tipo_casualidade) throws SQLException;

    public List<CasualidadeNoAcidenteTotal> getSumCasualidadesBySeverity() throws SQLException;
}
