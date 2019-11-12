package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.text.ParseException;

import fourzeta.desktop_views.EncerrarInscricoes;
import fourzeta.desktop_views.GerenciarTorneio;
import fourzeta.models.Gerente;
import fourzeta.models.Torneio;

public class InicialEncerrarController implements ActionListener {

	private GerenciarTorneio tela;
	private Gerente gerente;
	private EncerrarInscricoes telaEncerrar;
	private Torneio torneio;

	public InicialEncerrarController(Gerente gerente, Torneio torneio, GerenciarTorneio tela) {
		this.tela = tela;
		this.gerente = gerente;
		this.torneio = torneio;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {
			telaEncerrar = new EncerrarInscricoes(gerente, torneio);
		} catch (ParseException | IOException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.tela.setVisible(false);
		telaEncerrar.setVisible(true);

	}
}
