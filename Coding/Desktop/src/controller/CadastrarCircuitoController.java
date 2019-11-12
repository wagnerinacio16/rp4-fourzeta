package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;

import model.Circuito;
import model.Gerente;
import resource.CircuitoResource;
import view.CadastrarCircuito;
import view.SelecionarTorneio;

public class CadastrarCircuitoController implements ActionListener {

	private CadastrarCircuito tela;
	private CircuitoResource cr;
	private Circuito circuito;
	private Gerente gerente;

	public CadastrarCircuitoController(Gerente gerente, CadastrarCircuito tela) {
		this.tela = tela;
		this.gerente = gerente;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (bindCircuito(tela).getNome().isEmpty() || bindCircuito(tela).getDescricao().isEmpty()) {
			tela.notifyCampoIncompleto();
		} else {
			try {
				cr = new CircuitoResource();
				cr.registraCircuito(bindCircuito(tela));
//				cr.deletaCircuito(circuito);
				// cr.registraCircuito(circuito);
				tela.notifyCadastroRealizado();
				tela.setVisible(false);
				SelecionarTorneio gerenciar = new SelecionarTorneio(gerente);
				gerenciar.setVisible(true);
			} catch (MalformedURLException | RemoteException | NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public Circuito bindCircuito(CadastrarCircuito tela) {

		Circuito circuito = new Circuito();
		circuito.setNome(tela.getTextNomeCircuito().getText());
		circuito.setDescricao(tela.getTextDescricaoCircuito().getText());

		return circuito;

	}

}
