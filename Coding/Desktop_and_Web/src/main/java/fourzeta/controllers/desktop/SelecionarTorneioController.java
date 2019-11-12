package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import fourzeta.desktop_views.GerenciarTorneio;
import fourzeta.desktop_views.SelecionarTorneio;
import fourzeta.models.Gerente;
import fourzeta.models.Torneio;
import fourzeta.repository.TorneioResource;

public class SelecionarTorneioController implements ActionListener {

	private SelecionarTorneio tela;
	private GerenciarTorneio gerenciarTela;
	private TorneioResource tr;
	private Torneio torneio;
	private Gerente gerente;

	public SelecionarTorneioController(Gerente gerente, SelecionarTorneio tela) {
		this.tela = tela;
		this.gerente = gerente;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		tr = new TorneioResource();
		for (Torneio t : tr.listaTorneios()) {
			if (t.getNome().equalsIgnoreCase(tela.getComboTorneio().getSelectedItem().toString())) {
				torneio = t;
			}
		}
		try {
			gerenciarTela = new GerenciarTorneio(gerente, torneio);
			this.tela.setVisible(false);
			gerenciarTela.setVisible(true);
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
