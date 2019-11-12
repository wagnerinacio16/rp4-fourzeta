package com.rp4.fourzetaapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Torneio implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("nome")
    private String nome;
    @SerializedName("descricao")
    private String descricao;
    @SerializedName("qtdAtletas")
    private int qtdAtletas;
    @SerializedName("valor")
    private double valor;
    @SerializedName("duplas")
    private List<Dupla> duplas;
    @SerializedName("circuito")
    private Circuito circuito;
    @SerializedName("chaves")
    private List<Chave> chaves;
    @SerializedName("datFimInsc")
    private Date datFimInsc;
    @SerializedName("datIniJogos")
    private Date datIniJogos;
    @SerializedName("datFimJogos")
    private Date datFimJogos;

    protected Torneio(Parcel in) {
        id = in.readInt();
        nome = in.readString();
        descricao = in.readString();
        qtdAtletas = in.readInt();
        valor = in.readDouble();
        circuito = in.readParcelable(Circuito.class.getClassLoader());
    }

    public static final Creator<Torneio> CREATOR = new Creator<Torneio>() {
        @Override
        public Torneio createFromParcel(Parcel in) {
            return new Torneio(in);
        }

        @Override
        public Torneio[] newArray(int size) {
            return new Torneio[size];
        }
    };

    public List<Chave> getChavesCat(String categoria) {
        List<Chave> chavesCat = new ArrayList<Chave>();
        chavesCat = this.getChaves();

        List<Chave> cCat = new ArrayList<Chave>();

        for (Chave chave : chavesCat) {
            if (chave.getCategoria().equals(categoria)) {
                cCat.add(chave);
            }
        }
        return cCat;
    }

    public List<Chave> montarChave(List<Dupla> duplas) {
        chaves = new ArrayList<Chave>();

        int numChaves = duplas.size() / 3;

        // Cria chaves
        for (int i = 0; i < numChaves; i++) {
            Chave c = new Chave();
            c.setNome("Chave " + (i + 1));
            chaves.add(c);
        }

        // Primeiro linha
        int numDupla = 0;
        int aux = 0;
        for (int i = 0; i < numChaves; i++) {
            chaves.get(aux).setDupla1(duplas.get(numDupla));
            aux++;
            numDupla++;
        }

        // Segunda linha
        aux--;
        for (int i = 0; i < numChaves; i++) {
            chaves.get(aux).setDupla2(duplas.get(numDupla));
            aux--;
            numDupla++;
        }

        // Terceira linha
        aux++;
        for (int i = 0; i < numChaves; i++) {
            chaves.get(aux).setDupla3(duplas.get(numDupla));
            aux++;
            numDupla++;
        }
        return chaves;
    }

    public Torneio() {
        this.chaves = new ArrayList<Chave>();
        this.duplas = new ArrayList<Dupla>();
    }

    public List<Dupla> getDuplas() {
        return duplas;
    }

    public void setDuplas(List<Dupla> duplas) {
        this.duplas = duplas;
    }

    public List<Chave> getChave() {
        return chaves;
    }

    public void setChave(List<Chave> chaves) {
        this.chaves = chaves;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQtdAtletas() {
        return qtdAtletas;
    }

    public void setQtdAtletas(int qtdAtletas) {
        this.qtdAtletas = qtdAtletas;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public List<Chave> getChaves() {
        return chaves;
    }

    public void setChaves(List<Chave> chaves) {
        this.chaves = chaves;
    }

    public Date getDatFimInsc() {
        return datFimInsc;
    }

    public void setDatFimInsc(Date datFimInsc) {
        this.datFimInsc = datFimInsc;
    }

    public Date getDatIniJogos() {
        return datIniJogos;
    }

    public void setDatIniJogos(Date datIniJogos) {
        this.datIniJogos = datIniJogos;
    }

    public Date getDatFimJogos() {
        return datFimJogos;
    }

    public void setDatFimJogos(Date datFimJogos) {
        this.datFimJogos = datFimJogos;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
//        dest.writeInt(this.qtdAtletas);
//        dest.writeDouble(this.valor);
//        dest.writeList(this.duplas);
//        dest.writeValue(this.circuito);
//        dest.writeList(this.chaves);
//        dest.writeValue(this.datFimInsc);
//        dest.writeValue(this.datIniJogos);
//        dest.writeValue(this.datFimJogos);
    }
}
