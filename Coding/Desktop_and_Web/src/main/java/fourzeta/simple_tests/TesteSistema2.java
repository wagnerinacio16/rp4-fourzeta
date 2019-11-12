package fourzeta.simple_tests;

import fourzeta.models.Atleta;
import fourzeta.models.Circuito;
import fourzeta.models.Dupla;
import fourzeta.models.Torneio;
import fourzeta.repository.AtletaResource;
import fourzeta.repository.CircuitoResource;
import fourzeta.repository.DuplaResource;
import fourzeta.repository.TorneioResource;

public class TesteSistema2 {
public static void main(String[] args) {
	CircuitoResource cr = new CircuitoResource();
	TorneioResource tr = new TorneioResource();
	AtletaResource ar = new AtletaResource();
	DuplaResource dr = new DuplaResource();
	Circuito c = new Circuito();
	Torneio t = new Torneio();
	Atleta a1 = new Atleta();
	Atleta a2 = new Atleta();
	Dupla d = new Dupla();

	a1.setNome("Edineia Macedo");
	a1.setCpf("023.360.001-34");
	a1.setSexo("Feminino");
	a2.setNome("Toninho");
	a2.setCpf("032.673.912-91");
	a2.setSexo("Masculino");
	c.setNome("Circuito Luterano de Teste");
	c.setDescricao("Apenas para teste de Sistema");
	t.setNome("Torneio de Teste de Sistema");
	t.getDuplas().add(d);
	c.getTorneios().add(t);
	d.getAtletas().add(a1);
	d.getAtletas().add(a2);
	a1.getDuplas().add(d);
	a2.getDuplas().add(d);
	d.setCategoria("Iniciante");
	d.setImpedimento("Nenhum");
	cr.registraCircuito(c);
	tr.registraTorneio(t);
	dr.registraDupla(d);
	ar.registraAtleta(a1);
	ar.registraAtleta(a2);



	
}
}
