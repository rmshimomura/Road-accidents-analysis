package com.uel.road_accidents_analysis.model;

public class TipoCasualidade {
    private String nome;

    public TipoCasualidade(){

    }

    public TipoCasualidade(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
