package com.uel.road_accidents_analysis.model;

public class VeiculoAcidenteComCasualidade {
    private AcidenteComCasualidade acidenteComCasualidade;
    private Veiculo veiculo;
    private int quantidade;

    public VeiculoAcidenteComCasualidade(){

    }

    public VeiculoAcidenteComCasualidade(AcidenteComCasualidade acidenteComCasualidade, Veiculo veiculo, int quantidade) {
        this.acidenteComCasualidade = acidenteComCasualidade;
        this.veiculo = veiculo;
        this.quantidade = quantidade;
    }

    public AcidenteComCasualidade getAcidenteComCasualidade() {
        return acidenteComCasualidade;
    }

    public void setAcidenteComCasualidade(AcidenteComCasualidade acidenteComCasualidade) {
        this.acidenteComCasualidade = acidenteComCasualidade;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
