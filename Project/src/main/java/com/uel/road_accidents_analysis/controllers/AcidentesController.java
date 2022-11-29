package com.uel.road_accidents_analysis.controllers;

import com.uel.road_accidents_analysis.dao.factories.DAOFactory;
import com.uel.road_accidents_analysis.model.query_aux.AcidentesRodoviaCount;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.BarChartModel;


import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class AcidentesController {

    // arquivo recriado pois o intellij deixou a identacao horrivel

    private BarChartModel acidentesComparativoBarChart;
    private BarChartModel acidenteSCRodoviaBarChart;
    private BarChartModel acidenteCCRodoviaBarChart;

    @PostConstruct
    public void init() {
        createAcidentesComparativoBarChart();
        createAcidenteSCRodoviaBarChart();
        createAcidenteCCRodoviaBarChart();
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

    public void createAcidenteSCRodoviaBarChart() {
        acidenteSCRodoviaBarChart = new BarChartModel();

        try(DAOFactory daoFactory = DAOFactory.getInstance()) {
            List<AcidentesRodoviaCount> acidentesRodoviaCountList = daoFactory.getAcidenteSemCasualidadeDAO().getCountAcidentesForEachRodovia();

            ChartSeries acidentes = new ChartSeries();
            acidentes.setLabel("Acidentes sem casualidades por rodovia");
            for (AcidentesRodoviaCount acidentesRodoviaCount : acidentesRodoviaCountList) {
                acidentes.set(acidentesRodoviaCount.getNomeRodovia(), acidentesRodoviaCount.getCount());
            }

            acidenteSCRodoviaBarChart.addSeries(acidentes);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void createAcidenteCCRodoviaBarChart() {
        acidenteCCRodoviaBarChart = new BarChartModel();

        try(DAOFactory daoFactory = DAOFactory.getInstance()) {
            List<AcidentesRodoviaCount> acidentesRodoviaCountList = daoFactory.getAcidenteComCasualidadeDAO().getCountAcidentesForEachRodovia();

            ChartSeries acidentes = new ChartSeries();
            acidentes.setLabel("Acidentes com casualidades por rodovia");
            for (AcidentesRodoviaCount acidentesRodoviaCount : acidentesRodoviaCountList) {
                acidentes.set(acidentesRodoviaCount.getNomeRodovia(), acidentesRodoviaCount.getCount());
            }


            acidenteCCRodoviaBarChart.addSeries(acidentes);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public BarChartModel getAcidentesComparativoBarChart() {
        return acidentesComparativoBarChart;
    }

    public BarChartModel getAcidenteSCRodoviaBarChart() {
        return acidenteSCRodoviaBarChart;
    }

    public BarChartModel getAcidenteCCRodoviaBarChart() {
        return acidenteCCRodoviaBarChart;
    }

}
