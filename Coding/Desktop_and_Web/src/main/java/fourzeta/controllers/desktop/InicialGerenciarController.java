package fourzeta.controllers.desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.text.ParseException;

<<<<<<< HEAD:Coding/Desktop_and_Web/src/main/java/fourzeta/controllers/desktop/InicialGerenciarController.java
import fourzeta.desktop_views.Inicial;
import fourzeta.desktop_views.SelecionarTorneio;
import fourzeta.models.Gerente;
=======
import model.Gerente;
import view.Inicial;
import view.SelecionarTorneio;
>>>>>>> master:Coding/Desktop/src/controller/InicialGerenciarController.java

public class InicialGerenciarController implements ActionListener {
	private Inicial tela;
	private Gerente gerente;
	private SelecionarTorneio telaGerenciar;

	public InicialGerenciarController(Gerente gerente, Inicial tela) {
		this.tela = tela;
		this.gerente = gerente;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		try {
			telaGerenciar = new SelecionarTorneio(gerente);
		} catch (ParseException | IOException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.tela.setVisible(false);
		telaGerenciar.setVisible(true);

	}
}
