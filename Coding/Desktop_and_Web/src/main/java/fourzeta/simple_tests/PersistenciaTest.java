package fourzeta.simple_tests;

import java.util.ArrayList;
import java.util.List;

import fourzeta.models.Atleta;
import fourzeta.models.Chave;
import fourzeta.models.Circuito;
import fourzeta.models.Dupla;
import fourzeta.models.Jogo;
import fourzeta.models.Ranking;
import fourzeta.models.Torneio;
import fourzeta.repository.AtletaResource;
import fourzeta.repository.ChaveResource;
import fourzeta.repository.CircuitoResource;
import fourzeta.repository.DuplaResource;
import fourzeta.repository.JogoResource;
import fourzeta.repository.RankingResource;
import fourzeta.repository.TorneioResource;

public class PersistenciaTest {

	public static void main(String[] args) {
		
//		          Circuito
		Circuito c1 = new Circuito();
		c1.setDescricao("melhor Circuito alegrete");
		c1.setNome("Circuito dos gaucho");

//	              Atletas	
		List<Atleta> atletas = new ArrayList<Atleta>();
		ArrayList<Ranking> pontuacoes = new ArrayList<Ranking>();

		for (int i = 0; i < 24; i++) {
			Atleta atleta = new Atleta();
			Ranking pts = new Ranking();
			pts.setCategoria("Iniciante");
			pts.setPontos(i + 5);
			pts.setCircuito(c1);
			pts.setAtleta(atleta);

			atleta.setNome("Nome: " + (i + 1));
			atleta.setCpf("CPF: " + (i + 1123456));
			atleta.setRankings(List.of(pts));

			pontuacoes.add(pts);

			atletas.add(atleta);
		}

		c1.setPontuacoes(pontuacoes);

		List<Dupla> duplas = new ArrayList<Dupla>();
		for (int j = 0; j < atletas.size(); j++) {
			Dupla dupla = new Dupla();
			dupla.setAtletas(List.of(atletas.get(j), atletas.get(j + 1)));
			atletas.get(j).setDuplas(List.of(dupla));
			atletas.get(j + 1).setDuplas(List.of(dupla));
			duplas.add(dupla);
			j++;
		}

//		       Torneio
		Torneio t1 = new Torneio();
		List<Chave> chaves = new ArrayList<Chave>();
//		chaves.add(chaveA);
//		t1.setCircuito(c1);
		t1.setNome("Torneio 4 edicao alegrete");
		t1.setValor(10);
		t1.setDuplas(duplas);
		chaves = t1.montarChave(duplas);
		t1.setChaves(chaves);

		List<Torneio> torneios = new ArrayList<>();
		torneios.add(t1);
		c1.setTorneios(torneios);
//		chaveA.setTorneio(t1);

		List<Jogo> jogos = new ArrayList<Jogo>();
		int i = 0;
		for (Chave c : chaves) {
			Jogo jo = new Jogo("A / B: " + i++);
			c.setJogos(List.of(jo));
			jogos.add(jo);

		}
//		           SAVES

		DuplaResource duplaDAO = new DuplaResource();
		for (Dupla dupla : duplas) {
			duplaDAO.registraDupla(dupla);
		}

		AtletaResource atletaDAO = new AtletaResource();
		for (Atleta atleta : atletas) {
			atletaDAO.registraAtleta(atleta);
		}

		RankingResource ptsDAO = new RankingResource();
		for (Ranking pontuacao : pontuacoes) {
			ptsDAO.registraRanking(pontuacao);
		}

		JogoResource jogoDAO = new JogoResource();
		for (Jogo jogo : jogos) {
			jogoDAO.registraJogo(jogo);
		}

		ChaveResource chaveDAO = new ChaveResource();
		for (Chave chave : chaves) {
			chaveDAO.registraChave(chave);
		}

		TorneioResource tDAO = new TorneioResource();
		tDAO.registraTorneio(t1);

		CircuitoResource circuitoDAO = new CircuitoResource();
		circuitoDAO.registraCircuito(c1);

		// Consulta de todas as pessoas - FindALl
		for (Circuito a : circuitoDAO.listaCircuitos()) {
			System.out.println("Nome: " + a.getNome());
		}

		for (Ranking a : ptsDAO.listaRankings()) {
			System.out.println("Nomennnn: " + a.getAtleta().getNome());
		}
	}
}
