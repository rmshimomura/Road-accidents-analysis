package com.uel.road_accidents_analysis.model;

import java.util.Date;

public class LogCargas {

    private Long id;
    private String nomeArquivo;
    private String tipoArquivo;
    private Integer tuplasCarregadas;
    private Date horarioCarga;

    public LogCargas() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public Integer getTuplasCarregadas() {
        return tuplasCarregadas;
    }

    public void setTuplasCarregadas(Integer tuplasCarregadas) {
        this.tuplasCarregadas = tuplasCarregadas;
    }

    public Date getHorarioCarga() {
        return horarioCarga;
    }

    public void setHorarioCarga(Date horarioCarga) {
        this.horarioCarga = horarioCarga;
    }
}
