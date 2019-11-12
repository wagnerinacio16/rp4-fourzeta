package fourzeta.simple_tests;

public class TesteChaveamento {

	public static void main(String[] args) {
//
////		          Circuito
//		Circuito c1 = new Circuito();
//		c1.setDescricao("melhor Circuito alegrete");
//		c1.setNome("Circuito dos gaucho");
//
////	              Atletas	
//		List<Atleta> atletas = new ArrayList<Atleta>();
//		ArrayList<Ranking> pontuacoes = new ArrayList<Ranking>();
//
//		for (int i = 0; i < 24; i++) {
//			Atleta atleta = new Atleta();
//			Ranking pts = new Ranking();
//			pts.setCategoria(Categoria.INICIANTES);
//			pts.setPontos(i + 5);
//			pts.setCircuito(c1);
//			pts.setAtleta(atleta);
//
//			atleta.setNome("Nome: " + (i + 1));
//			atleta.setCpf("CPF: " + (i + 1123456));
//			atleta.setRankings(List.of(pts));
//
//			pontuacoes.add(pts);
//
//			atletas.add(atleta);
//		}
//
//		c1.setPontuacoes(pontuacoes);
//
//		List<Dupla> duplas = new ArrayList<Dupla>();
//		for (int j = 0; j < atletas.size(); j++) {
//			Dupla dupla = new Dupla();
//			dupla.setAtletas(List.of(atletas.get(j), atletas.get(j + 1)));
//			atletas.get(j).setDuplas(List.of(dupla));
//			atletas.get(j + 1).setDuplas(List.of(dupla));
//			duplas.add(dupla);
//			j++;
//		}
//
////		       Torneio
//		Torneio t1 = new Torneio();
//		List<Chave> chaves = new ArrayList<Chave>();
////		chaves.add(chaveA);
////		t1.setCircuito(c1);
//		t1.setNome("Torneio 4 edicao alegrete");
//		t1.setValor(10);
//		t1.setDuplas(duplas);
//		chaves = t1.montarChave(duplas);
//		t1.setChaves(chaves);
//
//		List<Torneio> torneios = new ArrayList<>();
//		torneios.add(t1);
//		c1.setTorneios(torneios);
////		chaveA.setTorneio(t1);
//
//		List<Jogo> jogos = new ArrayList<Jogo>();
//		int i = 0;
//		for (Chave c : chaves) {
//			Jogo jo = new Jogo("A / B: " + i++);
//			c.setJogos(List.of(jo));
//			jogos.add(jo);
//
//		}
////		           SAVES
//
//		DuplaDAO duplaDAO = new DuplaDAO();
//		for (Dupla dupla : duplas) {
//			duplaDAO.save(dupla);
//		}
//
//		AtletaDAO atletaDAO = new AtletaDAO();
//		for (Atleta atleta : atletas) {
//			atletaDAO.save(atleta);
//		}
//
//		RankingDAO ptsDAO = new RankingDAO();
//		for (Ranking pontuacao : pontuacoes) {
//			ptsDAO.save(pontuacao);
//		}
//
//		JogoDAO jogoDAO = new JogoDAO();
//		for (Jogo jogo : jogos) {
//			jogoDAO.save(jogo);
//		}
//
//		ChaveDAO chaveDAO = new ChaveDAO();
//		for (Chave chave : chaves) {
//			chaveDAO.save(chave);
//		}
//
//		TorneioDAO tDAO = new TorneioDAO();
//		tDAO.save(t1);
//
//		CircuitoDAO circuitoDAO = new CircuitoDAO();
//		circuitoDAO.save(c1);
//
//		// Consulta de todas as pessoas - FindALl
//		for (Circuito a : circuitoDAO.findAll()) {
//			System.out.println("Nome: " + a.getNome());
//		}
//
//		for (Ranking a : ptsDAO.findAll()) {
//			System.out.println("Nomennnn: " + a.getAtleta().getNome());
//		}
	}
}
