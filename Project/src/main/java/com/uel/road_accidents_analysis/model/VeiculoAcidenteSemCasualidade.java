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

    public Long getId_acidente_sem_casualidade() {
        return id_acidente_sem_casualidade;
    }

    public void setId_acidente_sem_casualidade(Long id_acidente_sem_casualidade) {
        this.id_acidente_sem_casualidade = id_acidente_sem_casualidade;
    }

    public Long getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(Long id_veiculo) {
        this.id_veiculo = id_veiculo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
