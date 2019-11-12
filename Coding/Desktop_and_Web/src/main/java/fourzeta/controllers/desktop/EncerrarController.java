package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import fourzeta.IElement;
import fourzeta.desktop_views.EncerrarInscricoes;
import fourzeta.desktop_views.GerenciarTorneio;
import fourzeta.models.Chave;
import fourzeta.models.Circuito;
import fourzeta.models.Dupla;
import fourzeta.models.Etapa;
import fourzeta.models.Gerente;
import fourzeta.models.Impedimento;
import fourzeta.models.Jogo;
import fourzeta.models.Torneio;
import fourzeta.repository.ChaveResource;
import fourzeta.repository.CircuitoResource;
import fourzeta.repository.DuplaResource;
import fourzeta.repository.JogoResource;
import fourzeta.repository.TorneioResource;

public class EncerrarController implements ActionListener {

	EncerrarInscricoes tela;
	GerenciarTorneio telaGerenciar;
	CircuitoResource cr;
	DuplaResource dr;
	ChaveResource chaver;
	JogoResource jr;
	Gerente gerente;
	Torneio torneio;
	private List<String> horariosQuinta = new ArrayList<String>();
	private List<String> horariosSexta = new ArrayList<String>();
	private List<String> horariosSabadoManha = new ArrayList<String>();
	private List<String> horariosSabadoTarde = new ArrayList<String>();
	private List<String> horariosSabadoNoite = new ArrayList<String>();
	private List<String> horariosDomingoManha = new ArrayList<String>();
	private List<String> horariosDomingoTarde = new ArrayList<String>();
	private List<String> horariosDomingoNoite = new ArrayList<String>();

	public EncerrarController(Gerente gerente, Torneio torneio, EncerrarInscricoes tela) {
		this.tela = tela;
		this.gerente = gerente;
		this.torneio = torneio;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (this.tela.getComboCircuito().getSelectedItem().toString().equalsIgnoreCase("Selecionar")) {
			this.tela.notifyEncerrarInscricaoCircuito();
			return;
		} else if (this.tela.getComboTorneio().getSelectedItem().toString().equalsIgnoreCase("Selecionar")) {
			this.tela.notifyEncerrarInscricaoTorneio();
			return;
		}
		cr = new CircuitoResource();
		for (Circuito c : cr.listaCircuitos()) {
			if (c.getNome().equalsIgnoreCase(this.tela.getComboCircuito().getSelectedItem().toString())) {
				try {
					organizarJogos();
					telaGerenciar = new GerenciarTorneio(gerente, torneio);
					this.tela.notifyEncerramentoSucesso();

					this.tela.setVisible(false);

					telaGerenciar.setVisible(true);
				} catch (ParseException | IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void organizarJogos() throws RemoteException {

		List<Jogo> jogos = new ArrayList<Jogo>();

		for (Chave chave : torneio.getChaves()) { // Jogos 1 VS 2
			Jogo jo1 = new Jogo();
			if (chave.getDupla1() != null && chave.getDupla2() != null) {

				jo1.setPartida(chave.getDupla1().toString() + "     X     " + chave.getDupla2().toString());
			}
			jogos.add(this.distribuirHorarios(chave, chave.getDupla1(), chave.getDupla2(), jo1));
			chave.setJogos(jogos);
			jr = new JogoResource();
			jr.registraJogo(jo1);
			chaver = new ChaveResource();
			chaver.registraChave(chave);

		}
		for (Chave chave : torneio.getChaves()) { // Jogos 1 VS 3
			Jogo jo2 = new Jogo();
			if (chave.getDupla1() != null && chave.getDupla3() != null) {

				jo2.setPartida(chave.getDupla1().toString() + "     X     " + chave.getDupla2().toString());
			}
			jogos.add(this.distribuirHorarios(chave, chave.getDupla1(), chave.getDupla3(), jo2));
			chave.setJogos(jogos);
			jr = new JogoResource();
			jr.registraJogo(jo2);
			chaver = new ChaveResource();
			chaver.registraChave(chave);
		}
		for (Chave chave : torneio.getChaves()) { // Jogos 2 VS 3
			Jogo jo3 = new Jogo();
			if (chave.getDupla2() != null && chave.getDupla2() != null) {

				jo3.setPartida(chave.getDupla1().toString() + "     X     " + chave.getDupla2().toString());
			}
			jogos.add(this.distribuirHorarios(chave, chave.getDupla2(), chave.getDupla3(), jo3));
			chave.setJogos(jogos);
			jr = new JogoResource();
			jr.registraJogo(jo3);

			chaver = new ChaveResource();
			chaver.registraChave(chave);
		}
		int cat1 = 0, cat2 = 0, cat3 = 0, cat4 = 0, cat5 = 0, cat6 = 0;
		for (Chave chave : torneio.getChaves()) {
			switch (chave.getCategoria()) {
			case "PRIMEIRA":
				cat1++;
				break;
			case "SEGUNDA":
				cat2++;
				break;
			case "TERCEIRA":
				cat3++;
				break;
			case "QUARTA":
				cat4++;
				break;
			case "QUINTA":
				cat5++;
				break;
			case "SEXTA":
				cat6++;
				break;
			default:
				break;
			}
		}
		iniciarMataMata(cat1,"PRIMEIRA");
		iniciarMataMata(cat2,"SEGUNDA");
		iniciarMataMata(cat3,"TEREIRA");
		iniciarMataMata(cat4,"QUARTA");
		iniciarMataMata(cat5,"QUINTA");
		iniciarMataMata(cat6,"INICIANTE");

	}

	private void iniciarMataMata(int numChave, String cat) {
		if (numChave != 0) {
			Chave chave = new Chave();
			Jogo jogo1 = new Jogo();
			Jogo jogo2 = new Jogo();
			Jogo jogo3 = new Jogo();
			Jogo jogo4 = new Jogo();
			Jogo jogo5 = new Jogo();
			Jogo jogo6 = new Jogo();
			Jogo jogo7 = new Jogo();
			Jogo jogo8 = new Jogo();
			TorneioResource tr = new TorneioResource();
			switch (numChave) {
			case 3:
				chave = new Chave();
				chave.setNome("Chave Final");
				chave.setCategoria(cat);
				jogo1 = new Jogo();
				jogo1.setChave(chave);
				jogo1.setEtapa(Etapa.FINAL.ordinal());
				jogo1.setPartida("Vencedor da chave 1 X segundo da chave 1");
				chave.getJogos().addAll((List.of(jogo1)));
				torneio.getChaves().add(chave);
				jr = new JogoResource();
				jr.registraJogo(jogo1);
				chaver = new ChaveResource();
				chaver.registraChave(chave);
				tr = new TorneioResource();
				tr.registraTorneio(torneio);
				break;
			case 6:
				chave.setNome("Chave Semi");
				chave.setCategoria(cat);
				jogo1 = new Jogo();
				jogo1.setChave(chave);
				jogo1.setEtapa(Etapa.SEMI.ordinal());
				jogo1.setPartida("(1)-Vencedor da chave 1 X Vencedor da chave 2");
				jogo2 = new Jogo();
				jogo2.setChave(chave);
				jogo2.setPartida("(2)-Segundo da chave 1 X Segundo da chave 2");
				jogo2.setEtapa(Etapa.SEMI.ordinal());

				chave.getJogos().addAll((List.of(jogo1, jogo2)));
				torneio.getChaves().add(chave);
				jr = new JogoResource();
				jr.registraJogo(jogo1);
				jr.registraJogo(jogo2);
				chaver = new ChaveResource();
				chaver.registraChave(chave);

				chave = new Chave();
				chave.setNome("Chave Final");
				chave.setCategoria(cat);
				jogo1 = new Jogo();
				jogo1.setChave(chave);
				jogo1.setEtapa(Etapa.FINAL.ordinal());
				jogo1.setPartida("Vencedor Semi (1) X Vencedor Semi (2)");

				chave.getJogos().addAll((List.of(jogo1)));
				torneio.getChaves().add(chave);
				jr = new JogoResource();
				jr.registraJogo(jogo1);
				chaver = new ChaveResource();
				chaver.registraChave(chave);
				tr = new TorneioResource();
				tr.registraTorneio(torneio);
				break;
			case 9:
				chave.setNome("Chave Semi (1)");
				chave.setCategoria(cat);
				jogo1 = new Jogo();
				jogo1.setChave(chave);
				jogo1.setEtapa(Etapa.SEMI.ordinal());
				jogo1.setPartida("(1)-Vencedor da chave 2 X Vencedor da chave 3");
				jogo2 = new Jogo();
				jogo2.setChave(chave);
				jogo2.setPartida("(2)-Segundo da chave 2 X Segundo da chave 3");
				jogo2.setEtapa(Etapa.SEMI.ordinal());
				chave.setJogos(List.of(jogo1, jogo2));
				torneio.getChaves().add(chave);
				jr = new JogoResource();
				jr.registraJogo(jogo1);
				jr.registraJogo(jogo2);
				chaver = new ChaveResource();
				chaver.registraChave(chave);

				chave.setNome("Chave Semi (2)");
				chave.setCategoria(cat);
				jogo1 = new Jogo();
				jogo1.setChave(chave);
				jogo1.setEtapa(Etapa.SEMI.ordinal());
				jogo1.setPartida("(3)-Vencedor semi (1) X Segundo da chave 1");
				jogo2 = new Jogo();
				jogo2.setChave(chave);
				jogo2.setPartida("(4)-Vencedor semi (2) X Primeiro da chave 2");
				jogo2.setEtapa(Etapa.SEMI.ordinal());

				chave.getJogos().addAll((List.of(jogo1, jogo2)));
				torneio.getChaves().add(chave);
				jr = new JogoResource();
				jr.registraJogo(jogo1);
				jr.registraJogo(jogo2);
				chaver = new ChaveResource();
				chaver.registraChave(chave);

				chave = new Chave();
				chave.setNome("Chave Final");
				chave.setCategoria(cat);
				jogo1 = new Jogo();
				jogo1.setChave(chave);
				jogo1.setEtapa(Etapa.FINAL.ordinal());
				jogo1.setPartida("Vencedor Semi (3) X Vencedor Semi (4)");

				chave.getJogos().addAll((List.of(jogo1)));
				torneio.getChaves().add(chave);
				jr = new JogoResource();
				jr.registraJogo(jogo1);
				chaver = new ChaveResource();
				chaver.registraChave(chave);
				tr = new TorneioResource();
				tr.registraTorneio(torneio);
				break;
			case 12:

				chave.setNome("Chave Quartas");
				chave.setCategoria(cat);
				jogo1 = new Jogo();
				jogo1.setChave(chave);
				jogo1.setEtapa(Etapa.QUARTA.ordinal());
				jogo1.setPartida("(1)-Vencedor da chave 1 X Vencedor da chave 2");
				jogo2 = new Jogo();
				jogo2.setChave(chave);
				jogo2.setPartida("(2)-Segundo da chave 1 X Segundo da chave 2");
				jogo2.setEtapa(Etapa.QUARTA.ordinal());
				jogo3 = new Jogo();
				jogo3.setChave(chave);
				jogo3.setPartida("(3)-Vencedor da chave 3 X Vencedor da chave 4");
				jogo3.setEtapa(Etapa.QUARTA.ordinal());
				jogo4 = new Jogo();
				jogo4.setChave(chave);
				jogo4.setPartida("(4)-Segundo da chave 3 X Segundo da chave 4");
				jogo4.setEtapa(Etapa.QUARTA.ordinal());

				chave.getJogos().addAll((List.of(jogo1, jogo2, jogo3, jogo4)));
				torneio.getChaves().add(chave);
				jr = new JogoResource();
				jr.registraJogo(jogo1);
				jr.registraJogo(jogo2);
				jr.registraJogo(jogo3);
				jr.registraJogo(jogo4);
				chaver = new ChaveResource();
				chaver.registraChave(chave);

				chave.setNome("Chave Semi");
				chave.setCategoria(cat);
				jogo1 = new Jogo();
				jogo1.setChave(chave);
				jogo1.setEtapa(Etapa.SEMI.ordinal());
				jogo1.setPartida("(1)-Vencedor Quarta(1) X Vencedor Quarta(2)");
				jogo2 = new Jogo();
				jogo2.setChave(chave);
				jogo2.setPartida("(2)-Vencedor Quarta(3) X Vencedor Quarta(4)");
				jogo2.setEtapa(Etapa.SEMI.ordinal());

				chave.getJogos().addAll((List.of(jogo1, jogo2)));
				torneio.getChaves().add(chave);
				jr = new JogoResource();
				jr.registraJogo(jogo1);
				jr.registraJogo(jogo2);
				chaver = new ChaveResource();
				chaver.registraChave(chave);

				chave = new Chave();
				chave.setNome("Chave Final");
				chave.setCategoria(cat);
				jogo1 = new Jogo();
				jogo1.setChave(chave);
				jogo1.setEtapa(Etapa.FINAL.ordinal());
				jogo1.setPartida("Vencedor Semi (1) X Vencedor Semi (2)");
				chave.getJogos().addAll((List.of(jogo1)));
				torneio.getChaves().add(chave);
				jr = new JogoResource();
				jr.registraJogo(jogo1);
				chaver = new ChaveResource();
				chaver.registraChave(chave);
				tr = new TorneioResource();
				tr.registraTorneio(torneio);
				break;
			case 15:
				chave.setNome("Chave Quartas");
				chave.setCategoria(cat);
				jogo1 = new Jogo();
				jogo1.setChave(chave);
				jogo1.setEtapa(Etapa.QUARTA.ordinal());
				jogo1.setPartida("(1)-Vencedor da chave 2 X Vencedor da chave 3");
				jogo2 = new Jogo();
				jogo2.setChave(chave);
				jogo2.setPartida("(2)-Segundo da chave 2 X Segundo da chave 3");
				jogo2.setEtapa(Etapa.QUARTA.ordinal());
				jogo3 = new Jogo();
				jogo3.setChave(chave);
				jogo3.setPartida("(3)-Vencedor da chave 3 X Vencedor da chave 5");
				jogo3.setEtapa(Etapa.QUARTA.ordinal());
				jogo4 = new Jogo();
				jogo4.setChave(chave);
				jogo4.setPartida("(4)-Segundo da chave 4 X Segundo da chave 5");
				jogo4.setEtapa(Etapa.QUARTA.ordinal());

				chave.getJogos().addAll((List.of(jogo1, jogo2, jogo3, jogo4)));
				torneio.getChaves().add(chave);
				jr = new JogoResource();
				jr.registraJogo(jogo1);
				jr.registraJogo(jogo2);
				jr.registraJogo(jogo3);
				jr.registraJogo(jogo4);
				chaver = new ChaveResource();
				chaver.registraChave(chave);
				
				chave.setNome("Chave Semi (1)");
				chave.setCategoria(cat);
				jogo1 = new Jogo();
				jogo1.setChave(chave);
				jogo1.setEtapa(Etapa.SEMI.ordinal());
				jogo1.setPartida("(1)-Vencedor Quarta(1) X Vencedor Quarta(2)");
				jogo2 = new Jogo();
				jogo2.setChave(chave);
				jogo2.setPartida("(2)-VENCEDOR Quarta(2) X Vencedor Quarta(4)");
				jogo2.setEtapa(Etapa.SEMI.ordinal());
				chave.setJogos(List.of(jogo1, jogo2));
				torneio.getChaves().add(chave);
				jr = new JogoResource();
				jr.registraJogo(jogo1);
				jr.registraJogo(jogo2);
				chaver = new ChaveResource();
				chaver.registraChave(chave);

				chave.setNome("Chave Semi (2)");
				chave.setCategoria(cat);
				jogo1 = new Jogo();
				jogo1.setChave(chave);
				jogo1.setEtapa(Etapa.SEMI.ordinal());
				jogo1.setPartida("(3)-Vencedor Semi(1) X Vencedor Semi(2)");
				jogo2 = new Jogo();
				jogo2.setChave(chave);
				jogo2.setPartida("(4)-Vencedor da chave 1 X Segundo da chave 1");
				jogo2.setEtapa(Etapa.SEMI.ordinal());

				chave.getJogos().addAll((List.of(jogo1, jogo2)));
				torneio.getChaves().add(chave);
				jr = new JogoResource();
				jr.registraJogo(jogo1);
				jr.registraJogo(jogo2);
				chaver = new ChaveResource();
				chaver.registraChave(chave);

				chave = new Chave();
				chave.setNome("Chave Final");
				chave.setCategoria(cat);
				jogo1 = new Jogo();
				jogo1.setChave(chave);
				jogo1.setEtapa(Etapa.FINAL.ordinal());
				jogo1.setPartida("Vencedor Semi (3) X Vencedor Semi (4)");

				chave.getJogos().addAll((List.of(jogo1)));
				torneio.getChaves().add(chave);
				jr = new JogoResource();
				jr.registraJogo(jogo1);
				chaver = new ChaveResource();
				chaver.registraChave(chave);
				tr = new TorneioResource();
				tr.registraTorneio(torneio);
				break;
			case 18:
				break;
			case 21:
				break;
			case 24:

				chave.setNome("Chave Oitavas");
				chave.setCategoria(cat);
				jogo1 = new Jogo();
				jogo1.setChave(chave);
				jogo1.setEtapa(Etapa.OITAVA.ordinal());
				jogo1.setPartida("(1)-Vencedor da chave 1 X Vencedor da chave 2");
				jogo2 = new Jogo();
				jogo2.setChave(chave);
				jogo2.setPartida("(2)-Segundo da chave 1 X Segundo da chave 2");
				jogo2.setEtapa(Etapa.OITAVA.ordinal());
				jogo3 = new Jogo();
				jogo3.setChave(chave);
				jogo3.setPartida("(3)-Vencedor da chave 3 X Vencedor da chave 4");
				jogo3.setEtapa(Etapa.OITAVA.ordinal());
				jogo4 = new Jogo();
				jogo4.setChave(chave);
				jogo4.setPartida("(4)-Segundo da chave 3 X Segundo da chave 4");
				jogo4.setEtapa(Etapa.OITAVA.ordinal());
				chave.setNome("Chave Oitavas");
				chave.setCategoria(cat);
				jogo5 = new Jogo();
				jogo5.setChave(chave);
				jogo5.setEtapa(Etapa.OITAVA.ordinal());
				jogo5.setPartida("(5)-Vencedor da chave 5 X Vencedor da chave 6");
				jogo6 = new Jogo();
				jogo6.setChave(chave);
				jogo6.setPartida("(6)-Segundo da chave 5 X Segundo da chave 6");
				jogo6.setEtapa(Etapa.OITAVA.ordinal());
				jogo7 = new Jogo();
				jogo7.setChave(chave);
				jogo7.setPartida("(7)-Vencedor da chave 7 X Vencedor da chave 7");
				jogo7.setEtapa(Etapa.OITAVA.ordinal());
				jogo8 = new Jogo();
				jogo8.setChave(chave);
				jogo8.setPartida("(8)-Segundo da chave 7 X Segundo da chave 8");
				jogo8.setEtapa(Etapa.OITAVA.ordinal());

				chave.getJogos().addAll((List.of(jogo1, jogo2, jogo3, jogo4, jogo5, jogo6, jogo7, jogo8)));
				torneio.getChaves().add(chave);
				jr = new JogoResource();
				jr.registraJogo(jogo1);
				jr.registraJogo(jogo2);
				jr.registraJogo(jogo3);
				jr.registraJogo(jogo4);
				chaver = new ChaveResource();
				chaver.registraChave(chave);

				chave.setNome("Chave Quartas");
				chave.setCategoria(cat);
				jogo1 = new Jogo();
				jogo1.setChave(chave);
				jogo1.setEtapa(Etapa.QUARTA.ordinal());
				jogo1.setPartida("(1)-Vencedor Oitavas(1) X Vencedor Oitavas(2)");
				jogo2 = new Jogo();
				jogo2.setChave(chave);
				jogo2.setPartida("(1)-Vencedor Oitavas(3) X Vencedor Oitavas(4)");
				jogo2.setEtapa(Etapa.QUARTA.ordinal());
				jogo3 = new Jogo();
				jogo3.setChave(chave);
				jogo3.setPartida("(1)-Vencedor Oitavas(5) X Vencedor Oitavas(6)");
				jogo3.setEtapa(Etapa.QUARTA.ordinal());
				jogo4 = new Jogo();
				jogo4.setChave(chave);
				jogo4.setPartida("(1)-Vencedor Oitavas(7) X Vencedor Oitavas(8)");
				jogo4.setEtapa(Etapa.QUARTA.ordinal());

				chave.getJogos().addAll((List.of(jogo1, jogo2, jogo3, jogo4)));
				torneio.getChaves().add(chave);
				jr = new JogoResource();
				jr.registraJogo(jogo1);
				jr.registraJogo(jogo2);
				jr.registraJogo(jogo3);
				jr.registraJogo(jogo4);
				chaver = new ChaveResource();
				chaver.registraChave(chave);

				chave.setNome("Chave Semi");
				chave.setCategoria(cat);
				jogo1 = new Jogo();
				jogo1.setChave(chave);
				jogo1.setEtapa(Etapa.SEMI.ordinal());
				jogo1.setPartida("(1)-Vencedor Quarta(1) X Vencedor Quarta(2)");
				jogo2 = new Jogo();
				jogo2.setChave(chave);
				jogo2.setPartida("(2)-Vencedor Quarta(3) X Vencedor Quarta(4)");
				jogo2.setEtapa(Etapa.SEMI.ordinal());

				chave.getJogos().addAll((List.of(jogo1, jogo2)));
				torneio.getChaves().add(chave);
				jr = new JogoResource();
				jr.registraJogo(jogo1);
				jr.registraJogo(jogo2);
				chaver = new ChaveResource();
				chaver.registraChave(chave);

				chave = new Chave();
				chave.setNome("Chave Final");
				chave.setCategoria(cat);
				jogo1 = new Jogo();
				jogo1.setChave(chave);
				jogo1.setEtapa(Etapa.FINAL.ordinal());
				jogo1.setPartida("Vencedor Semi (1) X Vencedor Semi (2)");
				chave.getJogos().addAll((List.of(jogo1)));
				torneio.getChaves().add(chave);
				jr = new JogoResource();
				jr.registraJogo(jogo1);
				chaver = new ChaveResource();
				chaver.registraChave(chave);
				tr = new TorneioResource();
				tr.registraTorneio(torneio);

				break;
			default:
				break;
			}
		}
	}

	public List<Chave> gerarListaInscritos()
			throws ParseException, RemoteException, MalformedURLException, NotBoundException {
		dr = new DuplaResource();
		List<Dupla> duplas = (List<Dupla>) dr.listaDuplas();
		Torneio torneio = new Torneio();

		while (duplas.size() % 3 != 0) {
			duplas.remove(duplas.size() - 1);
		}
		List<Dupla> listaChaveamento = new ArrayList<Dupla>();
		for (IElement element : duplas) {
			Dupla d = (Dupla) element;
			listaChaveamento.add(d);
		}

		return torneio.montarChave(listaChaveamento);

	}

	public Jogo distribuirHorarios(Chave chave, Dupla d1, Dupla d2, Jogo jogo) {
		List<List<String>> hrs = this.getHorarios();
		if (jogo.getEtapa() == Etapa.MATA_MATA.ordinal()) {
			if (!d1.getImpedimento().equals(Impedimento.NENHUM.name())) {
				switch (d1.getImpedimento().toString()) {
				case "QUINTA":
					hrs.remove(horariosQuinta);
					break;
				case "SEXTA":
					hrs.remove(horariosSexta);
					break;
				case "SABADO":
					hrs.remove(horariosSabadoManha);
					break;
				}
			}

			if (!d2.getImpedimento().equals(Impedimento.NENHUM.name())) {
				switch (d2.getImpedimento().toString()) {
				case "QUINTA":
					hrs.remove(horariosQuinta);
					break;
				case "SEXTA":
					hrs.remove(horariosSexta);
					break;
				case "SABADO":
					hrs.remove(horariosSabadoManha);
					break;
				}
			}
		}

		jogo.setData(hrs.get(0).remove(0));

		return jogo;
	}

	public List<List<String>> getHorarios() {
		List<List<String>> horarios = new ArrayList<List<String>>();
		horariosQuinta.add("QUINTA-FEIRA 18:00");
		horariosQuinta.add("QUINTA-FEIRA 18:50");
		horariosQuinta.add("QUINTA-FEIRA 19:40");
		horariosQuinta.add("QUINTA-FEIRA 20:30");
		horariosQuinta.add("QUINTA-FEIRA 21:20");
		horariosQuinta.add("QUINTA-FEIRA 22:10");
		horariosQuinta.add("QUINTA-FEIRA 23:00");
		horariosSexta.add("SEXTA-FEIRA 18:00");
		horariosSexta.add("SEXTA-FEIRA 18:50");
		horariosSexta.add("SEXTA-FEIRA 19:40");
		horariosSexta.add("SEXTA-FEIRA 20:30");
		horariosSexta.add("SEXTA-FEIRA 21:20");
		horariosSexta.add("SEXTA-FEIRA 22:10");
		horariosSexta.add("SEXTA-FEIRA 23:00");
		horariosSabadoManha.add("SÁBADO 08:00");
		horariosSabadoManha.add("SÁBADO 08:50");
		horariosSabadoManha.add("SÁBADO 09:40");
		horariosSabadoManha.add("SÁBADO 10:30");
		horariosSabadoManha.add("SÁBADO 11:20");
		horariosSabadoTarde.add("SÁBADO 13:50");
		horariosSabadoTarde.add("SÁBADO 14:40");
		horariosSabadoTarde.add("SÁBADO 15:30");
		horariosSabadoTarde.add("SÁBADO 16:20");
		horariosSabadoTarde.add("SÁBADO 17:10");
		horariosSabadoNoite.add("SÁBADO 18:00");
		horariosSabadoNoite.add("SÁBADO 18:50");
		horariosSabadoNoite.add("SÁBADO 19:40");
		horariosSabadoNoite.add("SÁBADO 20:30");
		horariosSabadoNoite.add("SÁBADO 21:20");
		horariosSabadoNoite.add("SÁBADO 22:10");
		horariosSabadoNoite.add("SÁBADO 23:00");
		horariosDomingoManha.add("DOMINGO 08:00");
		horariosDomingoManha.add("DOMINGO 08:50");
		horariosDomingoManha.add("DOMINGO 09:40");
		horariosDomingoManha.add("DOMINGO 10:30");
		horariosDomingoManha.add("DOMINGO 11:20");
		horariosDomingoTarde.add("DOMINGO 13:50");
		horariosDomingoTarde.add("DOMINGO 14:40");
		horariosDomingoTarde.add("DOMINGO 15:30");
		horariosDomingoTarde.add("DOMINGO 16:20");
		horariosDomingoTarde.add("DOMINGO 17:10");
		horariosDomingoNoite.add("DOMINGO 18:00");
		horariosDomingoNoite.add("DOMINGO 18:50");
		horariosDomingoNoite.add("DOMINGO 19:40");
		horariosDomingoNoite.add("DOMINGO 20:30");
		horariosDomingoNoite.add("DOMINGO 21:20");
		horariosDomingoNoite.add("DOMINGO 22:10");
		horariosDomingoNoite.add("DOMINGO 23:00");
		horarios.add(horariosQuinta);
		horarios.add(horariosSexta);
		horarios.add(horariosSabadoManha);
		horarios.add(horariosSabadoTarde);
		horarios.add(horariosSabadoNoite);
		horarios.add(horariosDomingoManha);
		horarios.add(horariosDomingoTarde);
		horarios.add(horariosDomingoNoite);
		return horarios;
	}

}
