package pe.edu.upeu.gth.controller.recruitment;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("request")
@RequestMapping("contrato")
public class ContratoController {

	private ModelAndView modelAndView;
	
	@RequestMapping(value = "/filtrar", method = RequestMethod.GET)
	public ModelAndView showJspContrato(ModelMap model) {
        modelAndView = new ModelAndView("contratacion/con_contratos", model);
        return modelAndView;
    }
	
}
