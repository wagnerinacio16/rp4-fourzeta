package com.rp4.fourzetaapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Dupla {

    @SerializedName("id")
    private int id;
    @SerializedName("torneio")
    private Torneio torneio;
    @SerializedName("categoria")
    private String categoria;
    @SerializedName("impedimento")
    private String impedimento;
    @SerializedName("atletas")
    private List<Atleta> atletas;
    @SerializedName("ponTotal")
    private int ponTotal;

    public int getId() {
        return id;
    }

    public Dupla(List<Atleta> atletas) {
        atletas = new ArrayList<Atleta>();
        atletas.addAll(atletas);
    }

    public Dupla() {
        atletas = new ArrayList<Atleta>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Atleta> getAtletas() {
        return atletas;
    }

    public void setAtletas(List<Atleta> atletas) {
        this.atletas = atletas;
    }

    public int getPonTotal() {
        return ponTotal;
    }

    public void setPonTotal(int ponTotal) {
        this.ponTotal = ponTotal;
    }

    public String toString() {
        return this.atletas.get(0).getNome() + " / " + this.atletas.get(1).getNome();
    }

    public String getImpedimento() {
        return impedimento;
    }

    public void setImpedimento(String impedimento) {
        this.impedimento = impedimento;
    }
}
