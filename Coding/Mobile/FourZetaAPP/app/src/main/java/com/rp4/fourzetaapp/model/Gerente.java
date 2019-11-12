package com.rp4.fourzetaapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Gerente implements Serializable{


	@SerializedName("id")
	private int id;
	@SerializedName("nome")
	private String nome;
	@SerializedName("usuario")
	private Usuario usuario;
	@SerializedName("circuitos")
	private List<Circuito> circuitos;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Circuito> getCircuitos() {
		return circuitos;
	}

	public void setCircuitos(List<Circuito> circuitos) {
		this.circuitos = circuitos;
	}

}
