package com.uel.road_accidents_analysis.model;

import java.sql.Time;
import java.util.Date;

public class AcidenteComCasualidade {
    private Long id;
    private Trecho trecho;
    private Date data;
    private Time horario;
    private double km;
    private String sentido;
    private String tipo;

    public AcidenteComCasualidade(){

    }

    public AcidenteComCasualidade(Long id, Trecho trecho, Date data, Time horario, double km, String sentido, String tipo) {
        this.id = id;
        this.trecho = trecho;
        this.data = data;
        this.horario = horario;
        this.km = km;
        this.sentido = sentido;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Trecho getTrecho() {
        return trecho;
    }

    public void setTrecho(Trecho trecho) {
        this.trecho = trecho;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    public double getKm() {
        return km;
    }

    public void setKm(double km) {
        this.km = km;
    }

    public String getSentido() {
        return sentido;
    }

    public void setSentido(String sentido) {
        this.sentido = sentido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
