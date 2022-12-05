package com.uel.road_accidents_analysis.controllers;

import com.uel.road_accidents_analysis.dao.factories.DAOFactory;
import com.uel.road_accidents_analysis.dao.interfaces.custom.RodoviaDAO;
import com.uel.road_accidents_analysis.model.Rodovia;
import com.uel.road_accidents_analysis.model.query_aux.AveragePerState;
import org.primefaces.model.chart.*;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class RodoviaController {

    private Rodovia rodovia;

    private List<Rodovia> listaRodovias;

    private List<String> ufs;

    private List<AveragePerState> averagePerStateList;

    private String chosenUF;

    private BarChartModel avgIcc;

    private BarChartModel avgIcm;

    private BarChartModel avgIcp;

    public BarChartModel getAvgIcc() {
        return avgIcc;
    }

    public void setAvgIcc(BarChartModel avgIcc) {
        this.avgIcc = avgIcc;
    }

    public BarChartModel getAvgIcm() {
        return avgIcm;
    }

    public void setAvgIcm(BarChartModel avgIcm) {
        this.avgIcm = avgIcm;
    }

    public BarChartModel getAvgIcp() {
        return avgIcp;
    }

    public void setAvgIcp(BarChartModel avgIcp) {
        this.avgIcp = avgIcp;
    }

    public String getChosenUF() {
        return chosenUF;
    }

    public void setChosenUF(String chosenUF) {
        this.chosenUF = chosenUF;
    }

    public List<String> getUfs() {
        return ufs;
    }

    public void setUfs(List<String> ufs) {
        this.ufs = ufs;
    }

    public Rodovia getRodovia() {
        return rodovia;
    }

    public void setRodovia(Rodovia rodovia) {
        this.rodovia = rodovia;
    }

    public List<Rodovia> getListaRodovias() {
        return listaRodovias;
    }

    public void setListaRodovias(List<Rodovia> rodovias) {
        this.listaRodovias = rodovias;
    }

    public List<AveragePerState> getAveragePerStateList() { return averagePerStateList; }

    public void setAveragePerStateList(List<AveragePerState> averagePerStateList) { this.averagePerStateList = averagePerStateList; }

    public List<String> getRodoviasUF() {

        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            RodoviaDAO rodoviaDAO = daoFactory.getRodoviaDAO();
            return rodoviaDAO.getAllUF();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public void buscar() {

        if (chosenUF == null) {

            if(rodovia.getNome().equals("")) {

                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    RodoviaDAO rodoviaDAO = daoFactory.getRodoviaDAO();
                    listaRodovias = rodoviaDAO.getAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {

                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    RodoviaDAO rodoviaDAO = daoFactory.getRodoviaDAO();
                    listaRodovias = rodoviaDAO.getAllRodoviaByName(rodovia.getNome());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (rodovia.getNome().equals("")) {
                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    RodoviaDAO rodoviaDAO = daoFactory.getRodoviaDAO();
                    listaRodovias = rodoviaDAO.getAllRodoviaByUF(chosenUF);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                    RodoviaDAO rodoviaDAO = daoFactory.getRodoviaDAO();
                    listaRodovias = new ArrayList<>();
                    listaRodovias= rodoviaDAO.getRodoviaByUfAndName(chosenUF, rodovia.getNome());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if(listaRodovias.size() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro!", "Nenhuma rodovia encontrada."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Busca realizada, " + listaRodovias.size() + " resultados encontrados."));
        }

    }

    public void createBarModels() {

        avgIcc = new BarChartModel();
        avgIcm = new BarChartModel();
        avgIcp = new BarChartModel();

        ChartSeries avgIccSeries = new ChartSeries();
        ChartSeries avgIcmSeries = new ChartSeries();
        ChartSeries avgIcpSeries = new ChartSeries();

        avgIccSeries.setLabel("Índice condição da conservação");
        avgIcmSeries.setLabel("Índice condição da manutenção");
        avgIcpSeries.setLabel("Índice condição do pavimento");

        for (AveragePerState averagePerState : averagePerStateList) {

            avgIccSeries.set(averagePerState.getUf(), Math.round(averagePerState.getIccAverage() * 100.0) / 100.0);
            avgIcmSeries.set(averagePerState.getUf(), Math.round(averagePerState.getIcmAverage() * 100.0) / 100.0);
            avgIcpSeries.set(averagePerState.getUf(), Math.round(averagePerState.getIcpAverage() * 100.0) / 100.0);


        }

        avgIcc.addSeries(avgIccSeries);
        avgIcm.addSeries(avgIcmSeries);
        avgIcp.addSeries(avgIcpSeries);

        avgIcc.setTitle("Índice de Condição da Conservação - quanto maior, melhor");
        avgIcc.setLegendPosition("w");
        avgIcc.setShowPointLabels(true);
        avgIcc.getAxes().put(AxisType.X, new CategoryAxis("UF"));
        avgIcc.getAxes().put(AxisType.Y, new LinearAxis("Média"));
        Axis yAxis = avgIcc.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(averagePerStateList.stream().mapToDouble(AveragePerState::getIccAverage).max().getAsDouble() + 10);


        avgIcp.setTitle("Índice de Condição do Pavimento - quanto maior, melhor");
        avgIcp.setLegendPosition("w");
        avgIcp.setShowPointLabels(true);
        avgIcp.getAxes().put(AxisType.X, new CategoryAxis("UF"));
        avgIcp.getAxes().put(AxisType.Y, new LinearAxis("Média"));
        yAxis = avgIcp.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(averagePerStateList.stream().mapToDouble(AveragePerState::getIcpAverage).max().getAsDouble() + 10);

        avgIcm.setTitle("Índice de Condição de Manutenção - quanto menor, melhor");
        avgIcm.setLegendPosition("w");
        avgIcm.setShowPointLabels(true);
        avgIcm.getAxes().put(AxisType.X, new CategoryAxis("UF"));
        avgIcm.getAxes().put(AxisType.Y, new LinearAxis("Média"));
        yAxis = avgIcm.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setMax(averagePerStateList.stream().mapToDouble(AveragePerState::getIcmAverage).max().getAsDouble() + 10);


    }

    public void getAveragePerState() {
        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            RodoviaDAO rodoviaDAO = daoFactory.getRodoviaDAO();
            averagePerStateList = rodoviaDAO.getStateAverages();

            createBarModels();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init() {
        rodovia = new Rodovia();

        ufs = getRodoviasUF();

        getAveragePerState();

    }

}
