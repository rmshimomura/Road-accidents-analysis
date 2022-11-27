package com.uel.road_accidents_analysis.controllers;

import com.uel.road_accidents_analysis.dao.factories.DAOFactory;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;


import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class AcidentesController {

    // arquivo recriado pois o intellij deixou a identacao horrivel

    private BarChartModel acidentesComparativoBarChart;

    @PostConstruct
    public void init() {
        createAcidentesComparativoBarChart();
    }


    public void createAcidentesComparativoBarChart() {
        acidentesComparativoBarChart = new BarChartModel();

        try(DAOFactory daoFactory = DAOFactory.getInstance()) {
            int acidentes_sc_count = daoFactory.getAcidenteSemCasualidadeDAO().getCount();
            int acidentes_cc_count = daoFactory.getAcidenteComCasualidadeDAO().getCount();

            ChartSeries acidentes = new ChartSeries();
            acidentes.setLabel("Acidentes");
            acidentes.set("Sem Casualidades", acidentes_sc_count);
            acidentes.set("Com Casualidades", acidentes_cc_count);
            acidentes.set("Total", acidentes_sc_count + acidentes_cc_count);

            acidentesComparativoBarChart.addSeries(acidentes);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public BarChartModel getAcidentesComparativoBarChart() {
        return acidentesComparativoBarChart;
    }


}
