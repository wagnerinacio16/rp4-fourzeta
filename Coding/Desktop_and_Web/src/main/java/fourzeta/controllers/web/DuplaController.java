package fourzeta.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fourzeta.models.Atleta;
import fourzeta.models.Circuito;
import fourzeta.models.Dupla;
import fourzeta.models.Ranking;
import fourzeta.models.Torneio;
import fourzeta.repository.AtletaRepository;
import fourzeta.repository.ChaveRepository;
import fourzeta.repository.CircuitoRepository;
import fourzeta.repository.DuplaRepository;
import fourzeta.repository.RankingRepository;
import fourzeta.repository.TorneioRepository;

@Controller
public class DuplaController {

	@Autowired
	private CircuitoRepository cr;
	
	@Autowired
	private TorneioRepository tr;
	
	@Autowired
	private ChaveRepository chr;
	
	@Autowired
	private RankingRepository rr;
	
	@Autowired
	private DuplaRepository dr;
	
	@Autowired
	private AtletaRepository ar;
	
	private Torneio torneio;
	
	private Ranking ranking1 = new Ranking();
	private Ranking ranking2 = new Ranking();
	
	
	
	@RequestMapping(value="/editarDupla{id}", method=RequestMethod.GET)
	public ModelAndView formEditDupla(@PathVariable("id") int id){
		Dupla dupla = dr.findById(id);
		Torneio torneio = dupla.getTorneio();
		Circuito circuito = torneio.getCircuito();
		ModelAndView mv = new ModelAndView("torneio/formEditDupla");
		mv.addObject("circuito", circuito);
		mv.addObject("dupla", dupla);
		
		this.torneio = torneio;
		
		mv.addObject("torneio", torneio);
		
		return mv;
		
	}
	
	@RequestMapping(value="/editarDupla{id}", method=RequestMethod.POST)
	public String editarDupla(Dupla dupla){
//		Torneio torneio = tr.findById(id);
		dupla.setTorneio(torneio);
		
		ranking1.setCategoria(dupla.getCategoria());
		ranking1.setCircuito(torneio.getCircuito());
		ranking1.setAtleta(dupla.getAtleta1());
		
		ranking2.setCategoria(dupla.getCategoria());
		ranking2.setCircuito(torneio.getCircuito());
		ranking2.setAtleta(dupla.getAtleta2());
		
		dupla.getAtleta1().getRankings().add(ranking1);
		dupla.getAtleta2().getRankings().add(ranking2);
//		Atleta a1 = dupla.getAtleta1();
//		a1.getRankings().add(new Ranking()));
		
		ar.save(dupla.getAtleta1());
		ar.save(dupla.getAtleta2());
		
		dr.save(dupla);
		
		
		System.out.println(dupla.getAtleta1().getNome());
		
		rr.save(ranking1);
		rr.save(ranking2);
		
		
		int idInt= torneio.getId();
		String codigo = "" + idInt;
		return "redirect:/detalhesTorneio" + codigo;
	}
	
	@RequestMapping(value="/inscricaoDuplaTorneio{id}", method=RequestMethod.GET)
	public ModelAndView formDupla(@PathVariable("id") int id){
		
		Torneio torneio = tr.findById(id);
		Circuito circuito = torneio.getCircuito();
		ModelAndView mv = new ModelAndView("torneio/inscricaoDupla");
		mv.addObject("circuito", circuito);
		mv.addObject("torneio", torneio);
		
//		Dupla dupla = new Dupla();
//		mv.addObject("dupla", dupla);
		
		this.torneio = torneio;
		
		return mv;
	}
	
	@RequestMapping(value="/inscricaoDuplaTorneio{id}", method=RequestMethod.POST)
	public String cadastrarDupla(@PathVariable("id") int id, Dupla dupla){
		Torneio torneio = tr.findById(id);
		dupla.setTorneio(torneio);
		ranking1.setCategoria(dupla.getCategoria());
		ranking1.setCircuito(torneio.getCircuito());
		ranking1.setAtleta(dupla.getAtleta1());
		
		ranking2.setCategoria(dupla.getCategoria());
		ranking2.setCircuito(torneio.getCircuito());
		ranking2.setAtleta(dupla.getAtleta2());
		
		dupla.getAtleta1().getRankings().add(ranking1);
		dupla.getAtleta2().getRankings().add(ranking2);
//		Atleta a1 = dupla.getAtleta1();
//		a1.getRankings().add(new Ranking()));
		
		ar.save(dupla.getAtleta1());
		ar.save(dupla.getAtleta2());
		
		dr.save(dupla);
		
		
		System.out.println(dupla.getAtleta1().getNome());
		
		rr.save(ranking1);
		rr.save(ranking2);
		
		this.torneio = torneio;
		
		int idInt= torneio.getId();
		String codigo = "" + idInt;
		return "redirect:/detalhesTorneio" + codigo;
	}
	
	
	@RequestMapping("/deletarDupla")
	public String deletarDupla(int id){
		Dupla dupla = dr.findById(id);
		dr.delete(dupla);
		
		int idInt= dupla.getTorneio().getId() ;
		String codigo = "" + idInt;
		return "redirect:/detalhesTorneio" + codigo;
	}
	
	
	
}
