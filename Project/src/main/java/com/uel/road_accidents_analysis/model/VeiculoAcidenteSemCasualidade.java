package com.uel.road_accidents_analysis.model;

public class VeiculoAcidenteSemCasualidade {
    private Long id_acidente_sem_casualidade;
    private Long id_veiculo;
    private int quantidade;

    public VeiculoAcidenteSemCasualidade() {

    }

    public VeiculoAcidenteSemCasualidade(Long id_acidente_sem_casualidade, Long id_veiculo, int quantidade) {
        this.id_acidente_sem_casualidade = id_acidente_sem_casualidade;
        this.id_veiculo = id_veiculo;
        this.quantidade = quantidade;
    }

    public Long getIdAcidenteSemCasualidade() {
        return id_acidente_sem_casualidade;
    }

    public void setIdAcidenteSemCasualidade(Long id_acidente_sem_casualidade) {
        this.id_acidente_sem_casualidade = id_acidente_sem_casualidade;
    }

    public Long getIdVeiculo() {
        return id_veiculo;
    }

    public void setIdVeiculo(Long id_veiculo) {
        this.id_veiculo = id_veiculo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
