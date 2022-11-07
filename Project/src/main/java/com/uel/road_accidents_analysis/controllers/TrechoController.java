package com.uel.road_accidents_analysis.controllers;


import com.uel.road_accidents_analysis.dao.factories.DAOFactory;
import com.uel.road_accidents_analysis.model.Rodovia;
import com.uel.road_accidents_analysis.model.Trecho;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.List;

@Named
@ViewScoped
public class TrechoController {

    private Rodovia rodovia;

    private List<Rodovia> listRodovias;

    private List<Trecho> listaTrechos;

    private List<String> availableRodovias;

    private List<String> ufs;

    public String tempUF;

    private String chosenUF;

    private double mediaICC;

    private double mediaICP;

    private double mediaICM;

    public void buscar() {

        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            if(rodovia.getNome().equals("") || chosenUF.equals("")){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro!", "Selecione uma rodovia!"));
            } else if (!rodovia.getNome().equals("") && !chosenUF.equals("")) {
                listaTrechos = daoFactory.getTrechoDAO().getTrechosByRodovia(chosenUF, rodovia.getNome());

                if(listaTrechos.size() > 0) {

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso!", "Trechos encontrados!"));

                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erro!", "Nenhum trecho encontrado!"));
                }

                mediaICC = 0;
                mediaICP = 0;
                mediaICM = 0;
                for (Trecho trecho : listaTrechos) {
                    mediaICC += trecho.getICC();
                    mediaICP += trecho.getICP();
                    mediaICM += trecho.getICM();
                }
                mediaICC /= listaTrechos.size();
                mediaICP /= listaTrechos.size();
                mediaICM /= listaTrechos.size();

                // Limit the number of decimal places
                mediaICC = Math.round(mediaICC * 100.0) / 100.0;
                mediaICP = Math.round(mediaICP * 100.0) / 100.0;
                mediaICM = Math.round(mediaICM * 100.0) / 100.0;

            }
        } catch (Exception e) {
            e.printStackTrace();

        }

        tempUF = chosenUF;

    }

    public void getRodoviasByUf() {

        if(chosenUF != null) {
            try (DAOFactory daoFactory = DAOFactory.getInstance()) {
                listRodovias = daoFactory.getRodoviaDAO().getAllRodoviaByUF(chosenUF);
                availableRodovias = listRodovias.stream().map(Rodovia::getNome).toList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @PostConstruct
    public void init() {

        rodovia = new Rodovia();

        try (DAOFactory daoFactory = DAOFactory.getInstance()) {
            ufs = daoFactory.getTrechoDAO().getUfs();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getChosenUF() {
        return chosenUF;
    }

    public void setChosenUF(String chosenUF) {
        this.chosenUF = chosenUF;
    }

    public Rodovia getRodovia() {
        return rodovia;
    }

    public void setRodovia(Rodovia rodovia) {
        this.rodovia = rodovia;
    }

    public List<Rodovia> getListRodovias() {
        return listRodovias;
    }

    public void setListRodovias(List<Rodovia> listRodovias) {
        this.listRodovias = listRodovias;
    }

    public List<Trecho> getListaTrechos() {
        return listaTrechos;
    }

    public void setListaTrechos(List<Trecho> listaTrechos) {
        this.listaTrechos = listaTrechos;
    }

    public double getMediaICC() {
        return mediaICC;
    }

    public void setMediaICC(double mediaICC) {
        this.mediaICC = mediaICC;
    }

    public double getMediaICP() {
        return mediaICP;
    }

    public void setMediaICP(double mediaICP) {
        this.mediaICP = mediaICP;
    }

    public double getMediaICM() {
        return mediaICM;
    }

    public void setMediaICM(double mediaICM) {
        this.mediaICM = mediaICM;
    }

    public List<String> getUfs() {
        return ufs;
    }

    public void setUfs(List<String> ufs) {
        this.ufs = ufs;
    }

    public List<String> getAvailableRodovias() {
        return availableRodovias;
    }

    public void setAvailableRodovias(List<String> availableRodovias) {
        this.availableRodovias = availableRodovias;
    }

    public String getTempUF() {
        return tempUF;
    }

    public void setTempUF(String tempUF) {
        this.tempUF = tempUF;
    }
}
