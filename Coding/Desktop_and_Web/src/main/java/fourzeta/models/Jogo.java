package fourzeta.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import fourzeta.IElement;

@Entity
public class Jogo implements Comparable<Jogo>, Serializable, IElement {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private int id;
	private String partida;
	
	private int etapa;

	@ManyToOne
	private Chave chave;

	@OneToOne
	private Quadra quadra;
	
	private String Categoria;

	private String data;

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


	public String getCategoria() {
		return Categoria;
	}

	public void setCategoria(String categoria) {
		Categoria = categoria;
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
