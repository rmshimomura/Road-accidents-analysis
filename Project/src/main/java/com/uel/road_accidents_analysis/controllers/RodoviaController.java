package com.uel.road_accidents_analysis.controllers;

import com.uel.road_accidents_analysis.dao.factories.DAOFactory;
import com.uel.road_accidents_analysis.dao.interfaces.custom.RodoviaDAO;
import com.uel.road_accidents_analysis.model.Rodovia;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
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

    private String chosenUF;

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

    @PostConstruct
    public void init() {
        rodovia = new Rodovia();

        ufs = getRodoviasUF();

    }

}
