package com.uel.road_accidents_analysis.model;

import java.sql.Time;
import java.util.Date;

public class AcidenteSemCasualidade {
    private Long id;
    private Long id_trecho;
    private Date data;
    private Time horario;
    private double km;
    private String sentido;
    private String tipo;

    public AcidenteSemCasualidade(){

    }

    public AcidenteSemCasualidade(Long id, Long id_trecho, Date data, Time horario, double km, String sentido, String tipo) {
        this.id = id;
        this.id_trecho = id_trecho;
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

    public Long getId_trecho() {
        return id_trecho;
    }

    public void setId_trecho(Long id_trecho) {
        this.id_trecho = id_trecho;
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
