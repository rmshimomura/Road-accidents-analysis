package com.uel.road_accidents_analysis.model.query_aux;

public class VeiculoCount {
    private String nome_veiculo;
    private int quantidade;

    public VeiculoCount() {

    }

    public VeiculoCount(String nome_veiculo, int quantidade) {
        this.nome_veiculo = nome_veiculo;
        this.quantidade = quantidade;
    }

    public String getNomeVeiculo() {
        return nome_veiculo;
    }

    public void setNomeVeiculo(String nome_veiculo) {
        this.nome_veiculo = nome_veiculo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
