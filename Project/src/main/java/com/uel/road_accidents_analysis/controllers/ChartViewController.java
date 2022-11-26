package com.uel.road_accidents_analysis.controllers;

import com.uel.road_accidents_analysis.dao.factories.DAOFactory;
import com.uel.road_accidents_analysis.dao.interfaces.custom.VeiculoAcidenteComCasualidadeDAO;
import com.uel.road_accidents_analysis.dao.interfaces.custom.VeiculoAcidenteSemCasualidadeDAO;
import com.uel.road_accidents_analysis.model.VeiculoAcidenteSemCasualidade;
import com.uel.road_accidents_analysis.model.query_aux.VeiculoCount;
import org.primefaces.model.chart.PieChartModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class ChartViewController implements Serializable {

    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    private PieChartModel semCasualidades;
    private PieChartModel comCasualidades;
    private PieChartModel todos;

    @PostConstruct
    public void init() {
        createSemCasualidadesModel();
        createComCasualidadesModel();
        createTodosModel();
    }

    public void createSemCasualidadesModel() {

        try (DAOFactory daoFactory = DAOFactory.getInstance()) {

            VeiculoAcidenteSemCasualidadeDAO veiculoAcidenteSemCasualidadeDAO = daoFactory.getVeiculoAcidenteSemCasualidadeDAO();
            List<VeiculoCount> listaVeiculosSemCasualidades = veiculoAcidenteSemCasualidadeDAO.count_groups();

            semCasualidades = new PieChartModel();

            for (VeiculoCount veiculoCount : listaVeiculosSemCasualidades) {
                semCasualidades.set(veiculoCount.getNomeVeiculo(), veiculoCount.getQuantidade());
            }

            semCasualidades.setTitle("Veículos sem Casualidades");
            semCasualidades.setLegendPosition("w");
            semCasualidades.setShowDataLabels(true);
            semCasualidades.setDiameter(300);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void createComCasualidadesModel() {
        try (DAOFactory daoFactory = DAOFactory.getInstance()) {

            VeiculoAcidenteComCasualidadeDAO veiculoAcidenteComCasualidadeDAO = daoFactory.getVeiculoAcidenteComCasualidadeDAO();
            List<VeiculoCount> listaVeiculosComCasualidades = veiculoAcidenteComCasualidadeDAO.count_groups();

            comCasualidades = new PieChartModel();

            for (VeiculoCount veiculoCount : listaVeiculosComCasualidades) {
                comCasualidades.set(veiculoCount.getNomeVeiculo(), veiculoCount.getQuantidade());
            }

            comCasualidades.setTitle("Veículos com Casualidades");
            comCasualidades.setLegendPosition("w");
            comCasualidades.setShowDataLabels(true);
            comCasualidades.setDiameter(300);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTodosModel() {

        try (DAOFactory daoFactory = DAOFactory.getInstance()) {

            VeiculoAcidenteComCasualidadeDAO veiculoAcidenteComCasualidadeDAO = daoFactory.getVeiculoAcidenteComCasualidadeDAO();
            List<VeiculoCount> listaVeiculosComCasualidades = veiculoAcidenteComCasualidadeDAO.count_groups();

            VeiculoAcidenteSemCasualidadeDAO veiculoAcidenteSemCasualidadeDAO = daoFactory.getVeiculoAcidenteSemCasualidadeDAO();
            List<VeiculoCount> listaVeiculosSemCasualidades = veiculoAcidenteSemCasualidadeDAO.count_groups();

            // Make list of all vehicles
            List<VeiculoCount> listaVeiculos = listaVeiculosComCasualidades;
            for (VeiculoCount veiculoCount : listaVeiculosSemCasualidades) {
                boolean found = false;
                for (VeiculoCount veiculoCount1 : listaVeiculos) {
                    if (veiculoCount.getNomeVeiculo().equals(veiculoCount1.getNomeVeiculo())) {
                        veiculoCount1.setQuantidade(veiculoCount1.getQuantidade() + veiculoCount.getQuantidade());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    listaVeiculos.add(veiculoCount);
                }
            }

            todos = new PieChartModel();

            for (VeiculoCount veiculoCount : listaVeiculos) {
                todos.set(veiculoCount.getNomeVeiculo(), veiculoCount.getQuantidade());
            }

            todos.setTitle("Veículos com e sem Casualidades");
            todos.setLegendPosition("w");
            todos.setShowDataLabels(true);
            todos.setDiameter(300);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void createPieModel1() {
        pieModel1 = new PieChartModel();

        pieModel1.set("Brand 1", 540);
        pieModel1.set("Brand 2", 325);
        pieModel1.set("Brand 3", 702);

        pieModel1.setTitle("Accidents by Severity");
        pieModel1.setLegendPosition("w");
        pieModel1.setShowDataLabels(true);
        pieModel1.setDiameter(150);


    }

    public void createPieModel2() {
        pieModel2 = new PieChartModel();

        pieModel2.set("Brand 1", 540);
        pieModel2.set("Brand 2", 325);
        pieModel2.set("Brand 3", 702);

        pieModel2.setTitle("Accidents by Severity");
        pieModel2.setLegendPosition("w");
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    public PieChartModel getSemCasualidades() {
        return semCasualidades;
    }

    public PieChartModel getComCasualidades() {
        return comCasualidades;
    }

    public PieChartModel getTodos() {
        return todos;
    }

}