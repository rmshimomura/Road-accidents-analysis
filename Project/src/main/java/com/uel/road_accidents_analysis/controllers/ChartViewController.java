package com.uel.road_accidents_analysis.controllers;

import com.uel.road_accidents_analysis.dao.factories.DAOFactory;
import com.uel.road_accidents_analysis.model.CasualidadeNoAcidenteTotal;
import org.primefaces.model.chart.PieChartModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ChartViewController  {

    private PieChartModel casualidadeNoAcidentePieChart;

    @PostConstruct
    public void init() {
        createCasualidadeNoAcidentePieChart();
    }

    public void createCasualidadeNoAcidentePieChart() {
        try(DAOFactory daoFactory = DAOFactory.getInstance()) {
            casualidadeNoAcidentePieChart = new PieChartModel();
            List<CasualidadeNoAcidenteTotal> casualidades = daoFactory.getCasualidadeNoAcidenteDAO().getSumCasualidadesBySeverity();

            for (CasualidadeNoAcidenteTotal casualidade : casualidades) {
                casualidadeNoAcidentePieChart.set(casualidade.getNomeCasualidade(), casualidade.getTotal());
            }

            casualidadeNoAcidentePieChart.setTitle("Casualidades por gravidade");
            casualidadeNoAcidentePieChart.setLegendPosition("w");
            casualidadeNoAcidentePieChart.setShowDataLabels(true);
            casualidadeNoAcidentePieChart.setDiameter(150);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  

    public PieChartModel getCasualidadeNoAcidentePieChart() {
        return casualidadeNoAcidentePieChart;
    }



}
