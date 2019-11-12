package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.text.ParseException;
import java.util.List;

import javax.validation.Valid;

<<<<<<< HEAD:Coding/Desktop_and_Web/src/main/java/fourzeta/controllers/desktop/CadastrarTorneioController.java
import fourzeta.desktop_views.CadastrarTorneio;
import fourzeta.desktop_views.SelecionarTorneio;
import fourzeta.models.Circuito;
import fourzeta.models.Gerente;
import fourzeta.models.Torneio;
import fourzeta.repository.CircuitoResource;
import fourzeta.repository.TorneioResource;
=======
import model.Circuito;
import model.Gerente;
import model.Quadra;
import model.Torneio;
import resource.CircuitoResource;
import resource.TorneioResource;
import view.CadastrarTorneio;
import view.SelecionarTorneio;
>>>>>>> master:Coding/Desktop/src/controller/CadastrarTorneioController.java

public class CadastrarTorneioController implements ActionListener {

	private CadastrarTorneio tela;
	private CircuitoResource cr;
	private TorneioResource tr;
	private Torneio torneio;
	private Circuito circuito;
	private Gerente gerente;

	public CadastrarTorneioController(Gerente gerente, CadastrarTorneio tela) {
		this.tela = tela;
		this.gerente = gerente;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		cr = new CircuitoResource();

		for (Circuito c : cr.listaCircuitos()) {
			if (c.getNome().equalsIgnoreCase(tela.getComboBoxCircuito().getSelectedItem().toString())) {
				circuito = c;
			}
		}

		if (bindTorneio(tela).getNome().isEmpty() || bindTorneio(tela).getDescricao().isEmpty()
				|| bindTorneio(tela).getDatIniJogos().isEmpty() || bindTorneio(tela).getDatFimJogos().isEmpty()) {
			tela.notifyCampoIncompleto();
		} else {
			List<Torneio> torneios = null;
			torneios.add(bindTorneio(tela));
			circuito.setTorneios((List<Torneio>) tr.registraTorneio((@Valid Torneio) torneios));

			tela.notifyCadastroRealizado();
			tela.setVisible(false);
			SelecionarTorneio gerenciar = null;
			try {
				gerenciar = new SelecionarTorneio(gerente);
			} catch (ParseException | IOException | NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gerenciar.setVisible(true);

		}

	}

	public Torneio bindTorneio(CadastrarTorneio tela) {

		Torneio torneio = new Torneio();
		torneio.setNome(tela.getTextNomeTorneio().getText());
		torneio.setDescricao(tela.getTextDescricaoTorneio().getText());
		torneio.setDatIniJogos(tela.getTextDataInicio().getText());
		torneio.setDatFimJogos(tela.getTextDataInicio().getText());
		if (!this.tela.getComboBoxQuadra1().getSelectedItem().equals("SELECIONAR")) {
			torneio.getDistribuicaoJogos()[0] = converterQuadra(
					this.tela.getComboBoxQuadra1().getSelectedItem().toString());
		}
		if (!this.tela.getComboBoxQuadra2().getSelectedItem().equals("SELECIONAR")) {
			torneio.getDistribuicaoJogos()[1] = converterQuadra(
					this.tela.getComboBoxQuadra2().getSelectedItem().toString());
		}
		if (!this.tela.getComboBoxQuadra3().getSelectedItem().equals("SELECIONAR")) {
			torneio.getDistribuicaoJogos()[2] = converterQuadra(
					this.tela.getComboBoxQuadra3().getSelectedItem().toString());
		}
		if (!this.tela.getComboBoxQuadra4().getSelectedItem().equals("SELECIONAR")) {
			torneio.getDistribuicaoJogos()[3] = converterQuadra(
					this.tela.getComboBoxQuadra4().getSelectedItem().toString());
		}
		if (!this.tela.getComboBoxQuadra5().getSelectedItem().equals("SELECIONAR")) {
			torneio.getDistribuicaoJogos()[4] = converterQuadra(
					this.tela.getComboBoxQuadra5().getSelectedItem().toString());
		}
		if (!this.tela.getComboBoxQuadra6().getSelectedItem().equals("SELECIONAR")) {
			torneio.getDistribuicaoJogos()[5] = converterQuadra(
					this.tela.getComboBoxQuadra6().getSelectedItem().toString());
		}

		return torneio;

	}

	public int converterQuadra(String itemSelecionado) {
		switch (itemSelecionado) {
		case "LARANJA":
			return 1;
		case "AZUL":
			return 2;
		case "VERDE":
			return 3;
		}
		return -1;
	}
}
