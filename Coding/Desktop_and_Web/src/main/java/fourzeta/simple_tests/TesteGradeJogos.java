package fourzeta.simple_tests;

import java.text.ParseException;

import fourzeta.desktop_views.GradeJogos;
import fourzeta.models.Gerente;
import fourzeta.models.Torneio;
import fourzeta.repository.TorneioResource;


public class TesteGradeJogos {

	public static void main(String[] args) throws ParseException {
		TorneioResource tr = new TorneioResource();
		Torneio torneio = null;
	    for(Torneio t : tr.listaTorneios()) {
	    	if(t.getId() == 69) {
	    		torneio = t;
	    	}
	    }
		Gerente gerente = new Gerente();
		gerente.setNome("FOURZETA");
		GradeJogos tela = new GradeJogos(gerente, torneio);
		tela.setVisible(true);
	}

}
