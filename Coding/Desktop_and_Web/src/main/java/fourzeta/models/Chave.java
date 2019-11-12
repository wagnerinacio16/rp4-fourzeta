package fourzeta.models;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;

import fourzeta.IElement;
import fourzeta.resources.JogoResource;

@Entity
public class Chave implements Serializable, IElement {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private int id;
	private String nome;

//	@Enumerated(EnumType.ORDINAL)
	private String categoria;

	@OneToOne
	private Dupla dupla1;
	@OneToOne
	private Dupla dupla2;
	@OneToOne
	private Dupla dupla3;

	@OneToMany(fetch = FetchType.EAGER, targetEntity = Jogo.class, mappedBy ="chave")
	@Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
	private List<Jogo> jogos;

	@ManyToOne
	private Torneio torneio;

	public List<Jogo> getJogos() {
		return jogos;
	}

	public Chave() {
		this.jogos = new ArrayList<Jogo>();
	}

	public Chave(int id, String nome, String categoria, Dupla dupla1, Dupla dupla2, Dupla dupla3, List<Jogo> jogos) {
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.dupla1 = dupla1;
		this.dupla2 = dupla2;
		this.dupla3 = dupla3;
		this.jogos = jogos;
	}

	public List<Jogo> criarJogos(List<Chave> chaves) throws MalformedURLException, RemoteException, NotBoundException {
		JogoResource jr = new JogoResource();
		Jogo j1, j2, j3;

		List<Jogo> jogos = new ArrayList<Jogo>(); // jogos gerados

		j1 = new Jogo();
		j1.setPartida(this.getDupla1().toString() + " / " + this.getDupla2().toString());
		jogos.add(j1);
		jr.registraJogo(j1);

		j2 = new Jogo();
		j2.setPartida(this.getDupla2().toString() + " / " + this.getDupla3().toString());
		jogos.add(j2);
		jr.registraJogo(j2);

		j3 = new Jogo();
		j3.setPartida(this.getDupla1().toString() + " / " + this.getDupla3().toString());
		jogos.add(j3);
		jr.registraJogo(j3);

		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Dupla getDupla1() {
		return dupla1;
	}

	public void setDupla1(Dupla dupla1) {
		this.dupla1 = dupla1;
	}

	public Dupla getDupla2() {
		return dupla2;
	}

	public void setDupla2(Dupla dupla2) {
		this.dupla2 = dupla2;
	}

	public Dupla getDupla3() {
		return dupla3;
	}

	public void setDupla3(Dupla dupla3) {
		this.dupla3 = dupla3;
	}

}
