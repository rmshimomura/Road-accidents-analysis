package com.uel.road_accidents_analysis.model;

public class CasualidadeNoAcidente {
    private Long id_acidente;
    private Long id_tipo_casualidade;
    private int quantidade;

    public CasualidadeNoAcidente(){

    }

    public CasualidadeNoAcidente(Long id_acidente, Long id_tipo_casualidade, int quantidade) {
        this.id_acidente = id_acidente;
        this.id_tipo_casualidade = id_tipo_casualidade;
        this.quantidade = quantidade;
    }

    public Long getIdAcidente() {
        return id_acidente;
    }

    public void setIdAcidente(Long id_acidente) {
        this.id_acidente = id_acidente;
    }

    public Long getIdTipoCasualidade() {
        return id_tipo_casualidade;
    }

    public void setIdTipoCasualidade(Long id_tipo_casualidade) {
        this.id_tipo_casualidade = id_tipo_casualidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
