package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;

import fourzeta.desktop_views.ExcluirTorneio;
import fourzeta.models.Gerente;
import fourzeta.models.Torneio;
import fourzeta.repository.TorneioResource;

public class ExcluirTorneioController implements ActionListener {

	private ExcluirTorneio tela;
	private TorneioResource tr;
	private Torneio torneio;
	private Gerente gerente;

	public ExcluirTorneioController(Gerente gerente, ExcluirTorneio tela) {
		this.tela = tela;
		this.gerente = gerente;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {
			tr = new TorneioResource();
			for (Torneio torneio : tr.listaTorneios()) {
				if (torneio.getNome().equalsIgnoreCase(tela.getComboBoxTorneio().getSelectedItem().toString())) {
					tr.deletaTorneio(torneio);
					tela.getComboBoxTorneio().removeItem(torneio.getId());
					tela.notifyExluirTorneio();
					tela.setVisible(false);
					;
					ExcluirTorneio refresh = new ExcluirTorneio(gerente);
					refresh.setVisible(true);
				}
			}

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
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