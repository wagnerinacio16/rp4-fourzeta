package fourzeta.controllers.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fourzeta.models.Atleta;
import fourzeta.models.Chave;
import fourzeta.models.Circuito;
import fourzeta.models.Dupla;
import fourzeta.models.Torneio;
import fourzeta.repository.AtletaRepository;
import fourzeta.repository.ChaveRepository;
import fourzeta.repository.CircuitoRepository;
import fourzeta.repository.DuplaRepository;
import fourzeta.repository.TorneioRepository;

@Controller
public class TorneioController {

	@Autowired
	private CircuitoRepository cr;
	
	@Autowired
	private TorneioRepository tr;
	
	@Autowired
	private ChaveRepository chr;
	
	@Autowired
	private DuplaRepository dr;
	
	private Circuito circuito;
	
	
	@RequestMapping(value="/editarTorneio{id}", method=RequestMethod.GET)
	public ModelAndView formEditTorneio(@PathVariable("id") int id){
		Torneio torneio = tr.findById(id);
		Circuito circuito = torneio.getCircuito();
		ModelAndView mv = new ModelAndView("torneio/formEditTorneio");
		mv.addObject("circuito", circuito);
		
		this.circuito = circuito;
		
		mv.addObject("torneio", torneio);
		
		return mv;
		
	}
	
	@RequestMapping(value="/editarTorneio{id}", method=RequestMethod.POST)
	public String editarTorneio(Torneio torneio){
//		Circuito circuito = cr.findById(id);
		torneio.setCircuito(circuito);
		tr.save(torneio);
		
		
		
//		int idInt= torneio.getCircuito().getId();
		String codigo = "" + circuito.getId() ;
		return "redirect:/circuito" + codigo;
	}
	
	
	@RequestMapping(value="/cadastrarTorneioCircuito{id}", method=RequestMethod.GET)
	public ModelAndView formTorneio(@PathVariable("id") int id){
		
		Circuito circuito = cr.findById(id);
		ModelAndView mv = new ModelAndView("torneio/formTorneio");
		mv.addObject("circuito", circuito);
		
		this.circuito = circuito;
		
		return mv;
	}
	
	@RequestMapping(value="/cadastrarTorneioCircuito{id}", method=RequestMethod.POST)
	public String cadastrarTorneio(@PathVariable("id") int id,Torneio torneio){
		Circuito circuito = cr.findById(id);
		
//		circuito.getTorneios().add(torneio);
		torneio.setCircuito(circuito);
		tr.save(torneio);
//		cr.save(circuito);
		
		this.circuito = circuito;
		
		int idInt= circuito.getId();
		String codigo = "" + idInt;
		return "redirect:/circuito" + codigo;
	}
	
	@RequestMapping(value="/detalhesTorneio{id}", method=RequestMethod.GET)
	public ModelAndView detalhesTorneio(@PathVariable("id") int id){
		Torneio torneio = tr.findById(id);
		Circuito circuito = torneio.getCircuito();
		ModelAndView mv = new ModelAndView("torneio/detalhesTorneio");
		mv.addObject("circuito", circuito);
		
		mv.addObject("torneio", torneio);
		
		Iterable<Chave> chaves = chr.findByTorneio(torneio);
		mv.addObject("chaves", chaves);
		
		Iterable<Dupla> duplas = dr.findByTorneio(torneio);
		mv.addObject("duplas", duplas);
		
		
		
		
		
		return mv;
	}
	
	@RequestMapping("/deletarTorneio")
	public String deletarTorneio(int id){
		Torneio torneio = tr.findById(id);
		tr.delete(torneio);
		
		int idInt= circuito.getId();
		String codigo = "" + idInt;
		return "redirect:/circuito" + codigo;
	}
}
