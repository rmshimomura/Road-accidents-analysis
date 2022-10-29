package com.uel.road_accidents_analysis.model;

public class VeiculoAcidenteSemCasualidade {
    private AcidenteSemCasualidade acidenteSemCasualidade;
    private Veiculo veiculo;
    private int quantidade;

    public VeiculoAcidenteSemCasualidade(){

    }

    public VeiculoAcidenteSemCasualidade(AcidenteSemCasualidade acidenteSemCasualidade, Veiculo veiculo, int quantidade) {
        this.acidenteSemCasualidade = acidenteSemCasualidade;
        this.veiculo = veiculo;
        this.quantidade = quantidade;
    }

    public AcidenteSemCasualidade getAcidenteSemCasualidade() {
        return acidenteSemCasualidade;
    }

    public void setAcidenteSemCasualidade(AcidenteSemCasualidade acidenteSemCasualidade) {
        this.acidenteSemCasualidade = acidenteSemCasualidade;
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
