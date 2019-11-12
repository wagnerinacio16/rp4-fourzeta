package fourzeta.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fourzeta.models.Circuito;
import fourzeta.models.Ranking;
import fourzeta.models.Torneio;
import fourzeta.repository.CircuitoRepository;
import fourzeta.repository.RankingRepository;
import fourzeta.repository.TorneioRepository;

@Controller
public class CircuitoController {

	@Autowired
	private CircuitoRepository cr;
	
	@Autowired
	private TorneioRepository tr;
	
	@Autowired
	private RankingRepository rr;
	
	
	@RequestMapping(value="/editarCircuito{id}", method=RequestMethod.GET)
	public ModelAndView formEdit(@PathVariable("id") int id){
		Circuito circuito = cr.findById(id);
		ModelAndView mv = new ModelAndView("circuito/formEditCircuito");
		mv.addObject("circuito", circuito);
		
		return mv;
		
	}
	
	@RequestMapping(value="/editarCircuito{id}", method=RequestMethod.POST)
	public String editarCircuito(Circuito circuito){
		cr.save(circuito);
		return "redirect:/circuitos";
	}
	
	
	@RequestMapping(value="/cadastrarCircuito", method=RequestMethod.GET)
	public String form(){
		return "circuito/formCircuito";
	}
	
	@RequestMapping(value="/cadastrarCircuito", method=RequestMethod.POST)
	public String form(Circuito circuito){
		cr.save(circuito);
		
		return "redirect:/circuitos";
	}
	
	
	@RequestMapping("/circuitos")
	public ModelAndView listaCircuitos(){
		ModelAndView mv = new ModelAndView("circuito/listaCircuitos");
		Iterable<Circuito> circuitos = cr.findAll();
		mv.addObject("circuitos", circuitos);
		return mv;
	}
	
	@RequestMapping(value="/circuito{id}", method=RequestMethod.GET)
	public ModelAndView detalhesCircuito(@PathVariable("id") int id){
		Circuito circuito = cr.findById(id);
		ModelAndView mv = new ModelAndView("circuito/detalhesCircuito");
		mv.addObject("circuito", circuito);
		
		Iterable<Torneio> torneios = tr.findByCircuito(circuito);
		mv.addObject("torneios", torneios);
		
		Iterable<Ranking> rankings = rr.findByCircuito(circuito);
		mv.addObject("rankings", rankings);
		
		return mv;
	}
	
	@RequestMapping("/deletarCircuito")
	public String deletarCircuito(int id){
		Circuito circuito = cr.findById(id);
		cr.delete(circuito);
		return "redirect:/circuitos";
	}
	
	
//	@RequestMapping(value="/{id}", method=RequestMethod.POST)
//	public String detalhesCircuitoPost(@PathVariable("id") int id){
//		if(result.hasErrors()){
//			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
//			return "redirect:/{codigo}";
//		}
//		Evento evento = er.findByCodigo(codigo);
//		convidado.setEvento(evento);
//		cr.save(convidado);
//		attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
//		return "redirect:/{codigo}";
//	}
	
	
}	
