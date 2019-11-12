package com.rp4.fourzetaapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.rp4.fourzetaapp.util.OrderPontuacaoDecrescente;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Circuito implements Parcelable {

    @SerializedName("id")
    private int id;
    @SerializedName("nome")
    private String nome;
    @SerializedName("descricao")
    private String descricao;
    @SerializedName("pontuacoes")
    private List<Ranking> rankings;
    @SerializedName("torneios")
    private List<Torneio> torneios;

    public Circuito() {
        this.rankings = new ArrayList<Ranking>();
        this.torneios = new ArrayList<Torneio>();
    }

    public Circuito(int id, String nome, String descricao, ArrayList<Ranking> rankings, List<Torneio> torneios) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.rankings = rankings;
        this.torneios = torneios;
    }

    protected Circuito(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        descricao = in.readString();
    }

    public static final Creator<Circuito> CREATOR = new Creator<Circuito>() {
        @Override
        public Circuito createFromParcel(Parcel in) {
            return new Circuito(in);
        }

        @Override
        public Circuito[] newArray(int size) {
            return new Circuito[size];
        }
    };

    public List<Ranking> getRanksByCategoria(String txtCategoria) {
        String categoria = txtCategoria.toUpperCase();
        switch (txtCategoria) {
            case "PRIMEIRA":
                categoria = "PRIMEIRA";
                break;
            case "SEGUNDA":
                categoria = "SEGUNDA";
                break;
            case "TERCEIRA":
                categoria = "TERCEIRA";
                break;
            case "QUARTA":
                categoria = "QUARTA";
                break;
            case "QUINTA":
                categoria = "QUINTA";
                break;
            case "INICIANTE":
                categoria = "INICIANTES";
                break;
            default:
                categoria = "INICIANTES";
        }

        List<Ranking> ranksCat = new ArrayList<Ranking>();
        ranksCat = this.getPontuacoes();

        List<Ranking> pontCat = new ArrayList<Ranking>();

        for (Ranking pontuacao : ranksCat) {
            if (pontuacao.getCategoria().equals(categoria)) {
                pontCat.add(pontuacao);
            }
        }
        Collections.sort(pontCat, new OrderPontuacaoDecrescente());

        return pontCat;
    }

    public Torneio getTorneio(int id) {
        for (Torneio t : this.torneios) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public List<Ranking> getPontuacoes() {
        return rankings;
    }

    public void setPontuacoes(List<Ranking> ponts) {
        this.rankings = ponts;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Torneio> getTorneios() {
        return torneios;
    }

    public void setTorneios(List<Torneio> torneios) {
        this.torneios = torneios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString(){
        return this.getNome();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.nome);
        dest.writeString(this.descricao);
//        dest.writeList(this.rankings);
//        dest.writeList(this.torneios);
    }
}
