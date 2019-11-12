package fourzeta.controllers.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fourzeta.models.Usuario;
import fourzeta.resources.UsuarioResource;

@Controller
public class LoginController {

	UsuarioResource ur = new UsuarioResource();

	@RequestMapping("/")
	public String login() {
		return "login/login";
	}
	
//	@RequestMapping("/inicial")
//	public String sucesso() {
//		return "inicial/inicial.xhtml";
//	}
	
	@RequestMapping("/falha")
	public String falha() {
		return "login/falha.xhtml";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String form(@Valid Usuario usuario) {
		for (Usuario u : ur.listaUsuarios()) {

			if (usuario.getNickname().equals(u.getNickname()) && usuario.getSenha().equals(u.getSenha())) {
				return "redirect:/inicial";
			}
		}
		return "redirect:/falha";
	}
}
