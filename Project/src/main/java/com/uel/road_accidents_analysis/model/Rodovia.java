package com.uel.road_accidents_analysis.model;


public class Rodovia {

    private Long id;
    private String UF;
    private String nome;

    public Rodovia() {
    }

    public Rodovia(Long id, String UF, String nome) {
        this.id = id;
        this.UF = UF;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Rodovia{" +
                "id=" + id +
                ", UF='" + UF + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }

}
