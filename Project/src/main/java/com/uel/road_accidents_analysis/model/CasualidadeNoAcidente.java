package com.uel.road_accidents_analysis.model;

public class CasualidadeNoAcidente {
    private AcidenteComCasualidade acidenteComCasualidade;
    private TipoCasualidade tipoCasualidade;
    private int quantidade;

    public CasualidadeNoAcidente(){

    }

    public CasualidadeNoAcidente(AcidenteComCasualidade acidenteComCasualidade, TipoCasualidade tipoCasualidade, int quantidade) {
        this.acidenteComCasualidade = acidenteComCasualidade;
        this.tipoCasualidade = tipoCasualidade;
        this.quantidade = quantidade;
    }

    public AcidenteComCasualidade getAcidenteComCasualidade() {
        return acidenteComCasualidade;
    }

    public void setAcidenteComCasualidade(AcidenteComCasualidade acidenteComCasualidade) {
        this.acidenteComCasualidade = acidenteComCasualidade;
    }

    public TipoCasualidade getTipoCasualidade() {
        return tipoCasualidade;
    }

    public void setTipoCasualidade(TipoCasualidade tipoCasualidade) {
        this.tipoCasualidade = tipoCasualidade;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
