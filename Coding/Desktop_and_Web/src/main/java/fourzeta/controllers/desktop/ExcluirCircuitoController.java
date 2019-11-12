package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.ParseException;

import fourzeta.desktop_views.ExcluirCircuito;
import fourzeta.models.Circuito;
import fourzeta.models.Gerente;
import fourzeta.repository.CircuitoResource;

public class ExcluirCircuitoController implements ActionListener {

	private ExcluirCircuito tela;
	private CircuitoResource cr;
	private Gerente gerente;

	public ExcluirCircuitoController(Gerente gerente, ExcluirCircuito tela) {
		this.tela = tela;
		this.gerente = gerente;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {
			cr = new CircuitoResource();
			for (Circuito circuito : cr.listaCircuitos()) {
				if (circuito.getNome().equalsIgnoreCase(tela.getComboBoxCircuito().getSelectedItem().toString())) {
					cr.deletaCircuito(circuito);
					tela.getComboBoxCircuito().removeItem(circuito.getId());
					tela.notifyExluirCircuito();
					tela.setVisible(false);
					;
					ExcluirCircuito refresh = new ExcluirCircuito(gerente);
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