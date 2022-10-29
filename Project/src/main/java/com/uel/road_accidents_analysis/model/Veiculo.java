package com.uel.road_accidents_analysis.model;

public class Veiculo {
    private String nome;

    public Veiculo(){

    }

    public Veiculo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
