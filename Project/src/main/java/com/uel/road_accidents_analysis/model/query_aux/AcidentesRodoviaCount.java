package com.uel.road_accidents_analysis.model.query_aux;

public class AcidentesRodoviaCount {

    private String nome_rodovia;

    private int count;


    public AcidentesRodoviaCount() {

    }

    public AcidentesRodoviaCount(String nome_rodovia, int count) {
        this.nome_rodovia = nome_rodovia;
        this.count = count;
    }

    public String getNomeRodovia() {
        return nome_rodovia;
    }

    public void setNomeRodovia(String nome_rodovia) {
        this.nome_rodovia = nome_rodovia;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
