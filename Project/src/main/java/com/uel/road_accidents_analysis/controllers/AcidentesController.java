package com.uel.road_accidents_analysis.controllers;

import com.uel.road_accidents_analysis.dao.factories.DAOFactory;
import com.uel.road_accidents_analysis.model.query_aux.AcidentesRodoviaCount;
import org.primefaces.model.chart.*;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.charts.optionconfig.title.Title;


import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class AcidentesController {

    // arquivo recriado pois o intellij deixou a identacao horrivel

    private PieChartModel acidentesComparativoPieChart;
    private BarChartModel acidenteSCRodoviaBarChart;
    private BarChartModel acidenteCCRodoviaBarChart;

    @PostConstruct
    public void init() {
        createAcidentesComparativoBarChart();
        createAcidenteSCRodoviaBarChart();
        createAcidenteCCRodoviaBarChart();
    }


    public void createAcidentesComparativoBarChart() {
        acidentesComparativoPieChart = new PieChartModel();

        try(DAOFactory daoFactory = DAOFactory.getInstance()) {
            int acidentes_sc_count = daoFactory.getAcidenteSemCasualidadeDAO().getCount();
            int acidentes_cc_count = daoFactory.getAcidenteComCasualidadeDAO().getCount();

            acidentesComparativoPieChart.setTitle("Acidentes");
            acidentesComparativoPieChart.set("Sem Casualidades", acidentes_sc_count);
            acidentesComparativoPieChart.set("Com Casualidades", acidentes_cc_count);
            acidentesComparativoPieChart.setLegendPosition("w");
            acidentesComparativoPieChart.setShowDataLabels(true);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void createAcidenteSCRodoviaBarChart() {
        acidenteSCRodoviaBarChart = new BarChartModel();

        try(DAOFactory daoFactory = DAOFactory.getInstance()) {
            List<AcidentesRodoviaCount> acidentesRodoviaCountList = daoFactory.getAcidenteSemCasualidadeDAO().getCountAcidentesForEachRodovia();

            for (int i = 0; i < acidentesRodoviaCountList.size(); i++) {
                for (int j = i + 1; j < acidentesRodoviaCountList.size(); j++) {
                    if (acidentesRodoviaCountList.get(i).getNomeRodovia().equals(acidentesRodoviaCountList.get(j).getNomeRodovia())) {
                        acidentesRodoviaCountList.get(i).setCount(acidentesRodoviaCountList.get(i).getCount() + acidentesRodoviaCountList.get(j).getCount());
                        acidentesRodoviaCountList.remove(j);
                        j--;
                    }
                }
            }

            acidentesRodoviaCountList.sort((o1, o2) -> o2.getCount() - o1.getCount());

            ChartSeries acidentes = new ChartSeries();
            acidentes.setLabel("Acidentes sem casualidades por rodovia");
            acidenteSCRodoviaBarChart.setTitle("Acidentes sem casualidades por rodovia");
            acidenteSCRodoviaBarChart.setShowPointLabels(true);
            acidenteSCRodoviaBarChart.setAnimate(true);
            Axis yAxis = acidenteSCRodoviaBarChart.getAxis(AxisType.Y);
            yAxis.setLabel("Acidentes");
            yAxis.setMax(acidentesRodoviaCountList.get(0).getCount() + 100);
            yAxis.setMin(0);
            Axis xAxis = acidenteSCRodoviaBarChart.getAxis(AxisType.X);
            xAxis.setLabel("Rodovias");

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

            for (int i = 0; i < acidentesRodoviaCountList.size(); i++) {
                for (int j = i + 1; j < acidentesRodoviaCountList.size(); j++) {
                    if (acidentesRodoviaCountList.get(i).getNomeRodovia().equals(acidentesRodoviaCountList.get(j).getNomeRodovia())) {
                        acidentesRodoviaCountList.get(i).setCount(acidentesRodoviaCountList.get(i).getCount() + acidentesRodoviaCountList.get(j).getCount());
                        acidentesRodoviaCountList.remove(j);
                        j--;
                    }
                }
            }

            acidentesRodoviaCountList.sort((o1, o2) -> o2.getCount() - o1.getCount());

            ChartSeries acidentes = new ChartSeries();
            acidentes.setLabel("Acidentes com casualidades por rodovia");
            acidenteCCRodoviaBarChart.setTitle("Acidentes com casualidades por rodovia");
            acidenteCCRodoviaBarChart.setShowPointLabels(true);
            acidenteCCRodoviaBarChart.setAnimate(true);
            Axis yAxis = acidenteCCRodoviaBarChart.getAxis(AxisType.Y);
            yAxis.setLabel("Acidentes");
            yAxis.setMax(acidentesRodoviaCountList.get(0).getCount() + 100);
            yAxis.setMin(0);
            Axis xAxis = acidenteCCRodoviaBarChart.getAxis(AxisType.X);
            xAxis.setLabel("Rodovias");


            for (AcidentesRodoviaCount acidentesRodoviaCount : acidentesRodoviaCountList) {
                acidentes.set(acidentesRodoviaCount.getNomeRodovia(), acidentesRodoviaCount.getCount());
            }


            acidenteCCRodoviaBarChart.addSeries(acidentes);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public PieChartModel getAcidentesComparativoBarChart() {
        return acidentesComparativoPieChart;
    }

    public BarChartModel getAcidenteSCRodoviaBarChart() {
        return acidenteSCRodoviaBarChart;
    }

    public BarChartModel getAcidenteCCRodoviaBarChart() {
        return acidenteCCRodoviaBarChart;
    }

}
