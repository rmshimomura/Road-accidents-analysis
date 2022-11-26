package com.uel.road_accidents_analysis.model;

public class CasualidadeNoAcidenteTotal {
    private int total;
    private String nome_casualidade;

    public CasualidadeNoAcidenteTotal() {

    }

    public CasualidadeNoAcidenteTotal(int total, String nome_casualidade) {
        this.total = total;
        this.nome_casualidade = nome_casualidade;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNomeCasualidade() {
        return nome_casualidade;
    }

    public void setNomeCasualidade(String nome_casualidade) {
        this.nome_casualidade = nome_casualidade;
    }
}
