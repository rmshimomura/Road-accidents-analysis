package com.uel.road_accidents_analysis.model;

import java.util.Date;

public class Trecho {
    private Long id;
    private Rodovia rodovia;
    private double kmInicial;
    private double kmFinal;
    private Date dataAvaliacao;
    private double ICC;
    private double ICP;
    private double ICM;

    public Trecho(){

    }

    public Trecho(Long id, Rodovia rodovia, double kmInicial, double kmFinal, Date dataAvaliacao, double ICC, double ICP, double ICM) {
        this.id = id;
        this.rodovia = rodovia;
        this.kmInicial = kmInicial;
        this.kmFinal = kmFinal;
        this.dataAvaliacao = dataAvaliacao;
        this.ICC = ICC;
        this.ICP = ICP;
        this.ICM = ICM;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rodovia getRodovia() {
        return rodovia;
    }

    public void setRodovia(Rodovia rodovia) {
        this.rodovia = rodovia;
    }

    public double getKmInicial() {
        return kmInicial;
    }

    public void setKmInicial(double kmInicial) {
        this.kmInicial = kmInicial;
    }

    public double getKmFinal() {
        return kmFinal;
    }

    public void setKmFinal(double kmFinal) {
        this.kmFinal = kmFinal;
    }

    public Date getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(Date dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public double getICC() {
        return ICC;
    }

    public void setICC(double ICC) {
        this.ICC = ICC;
    }

    public double getICP() {
        return ICP;
    }

    public void setICP(double ICP) {
        this.ICP = ICP;
    }

    public double getICM() {
        return ICM;
    }

    public void setICM(double ICM) {
        this.ICM = ICM;
    }

}
