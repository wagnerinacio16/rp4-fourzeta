package fourzeta.controllers.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InicialController {


	@RequestMapping("/inicial")
	public String inicial(){
		return "inicial/inicial.xhtml";
	}
	
}
