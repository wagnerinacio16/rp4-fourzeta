package com.rp4.fourzetaapp.model;

import com.google.gson.annotations.SerializedName;

public class Jogo implements Comparable<Jogo>{

    @SerializedName("id")
    private int id;
    @SerializedName("partida")
    private String partida;
    @SerializedName("etapa")
    private int etapa;
    @SerializedName("chave")
    private Chave chave;
    @SerializedName("quadra")
    private Quadra quadra;
    @SerializedName("data")
    private String data;
    @SerializedName("placar")
    private String placar;

    public Jogo() {
    }

    public Jogo(String partida) {
        this.partida = partida;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartida() {
        return partida;
    }

    public void setPartida(String partida) {
        this.partida = partida;
    }

    public Quadra getQuadra() {
        return quadra;
    }

    public void setQuadra(Quadra quadra) {
        this.quadra = quadra;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Chave getChave() {
        return chave;
    }

    public void setChave(Chave chave) {
        this.chave = chave;
    }

    public String getPlacar() {
        return placar;
    }

    public void setPlacar(String placar) {
        this.placar = placar;
    }

    public String[] getDuplas() {
        return this.partida.split("VS");
    }


    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    @Override
    public int compareTo(Jogo arg0) {
        boolean maior = arg0.getId() > this.getId();
        if (maior)
            return -1;
        else
            return 1;
    }

}
